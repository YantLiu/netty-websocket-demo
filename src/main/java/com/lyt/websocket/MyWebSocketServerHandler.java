package com.lyt.websocket;

import com.alibaba.fastjson.JSONObject;
import com.lyt.dto.BaseMessage;
import com.lyt.enums.MessageWork;
import com.lyt.messageconsumer.BaseMessageConsumer;
import com.lyt.utils.SpringContextHolder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * channel控制器
 */
@Slf4j
public class MyWebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;

    /**
     * @Description: 更新柜机在线状态
     * @Param: [cabinetCode, onelie]
     * @return: void
     * @Author: lyt
     * @Date: 2020/3/26
     */
    private void setClientStatus(String clientid, boolean online) {
        if (clientid == null) {//防止重复触发
            return;
        }
        //离线, 设备池中移除设备
        if (!online) {
            ChannelHandlerPool.onRemoveClient(clientid);
        }
    }


    /**
     * channel 通道 action 活跃的 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
    }

    /**
     * channel 通道 Inactive 不活跃的 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端关闭了通信通道并且不可以传输数据
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("MyWebSocketServerHandler.channelInactive");
        this.setClientStatus(ChannelHandlerPool.getClientid(ctx.channel()), false);
    }

    /**
     * exception 异常 Caught 抓住 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("MyWebSocketServerHandler.exceptionCaught", cause);
        /*
        String clientid = ChannelHandlerPool.getClientid(ctx.channel());
        this.updatePbansCabinetOnline(clientid, false);
        //移除设备
        ChannelHandlerPool.onRemoveClient(ChannelHandlerPool.getClientid(ctx.channel()));
         */
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            if (((IdleStateEvent) evt).state().equals(IdleState.READER_IDLE)) {
                log.debug("MyWebSocketServerHandler.READER_IDLE");
                ctx.close();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    /**
     * 接收客户端发送的消息 channel 通道 Read 读 简而言之就是从通道中读取数据，也就是服务端接收客户端发来的数据。但是这个数据在不进行解码时它是ByteBuf类型的
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {//传统的HTTP接入
            handleHttpRequest(ctx, ((FullHttpRequest) msg));
        } else if (msg instanceof WebSocketFrame) {// WebSocket接入
            //log.debug(handshaker.uri());
            handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }

    /**
     * channel 通道 Read 读取 Complete 完成 在通道读取完成后会在这个方法里通知，对应可以做刷新操作 ctx.flush()
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            log.debug("handlerWebSocketFrame.close");
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否ping消息
        if (frame instanceof PingWebSocketFrame) {
            log.debug("handlerWebSocketFrame.ping");
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 本例程仅支持文本消息，不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
            log.warn("本例程仅支持文本消息，不支持二进制消息");
            throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
        }
        // 返回应答消息
        String message = ((TextWebSocketFrame) frame).text();
        log.debug("服务端收到消息：" + message);
        BaseMessage<String> baseMessage = JSONObject.parseObject(message, BaseMessage.class);
        //根据MessageWork取到消费者类型, 调用消费者
        MessageWork messageWork = MessageWork.get(baseMessage.getWork(), baseMessage.getType());
        if (messageWork.getClz() != null) {
            BaseMessageConsumer consumer = SpringContextHolder.getBean(messageWork.getClz());
            consumer.run(ChannelHandlerPool.getClientid(ctx.channel()), baseMessage);
        }
    }

    private String getClientid(String uri) {
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri);
        Map<String, List<String>> parameters = queryStringDecoder.parameters();
        if (CollectionUtils.isEmpty(parameters.get("clientid")) || StringUtils.isEmpty(parameters.get("clientid").get(0))) {
            return null;
        }
        return parameters.get("clientid").get(0);
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        // 如果HTTP解码失败，返回HHTP异常
        if (!req.decoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        //获取url后置参数
        HttpMethod method = req.method();
        String uri = req.getUri();
        //取clientid
        String clientid = getClientid(uri);
        if (clientid == null) {
            log.warn("clientid为空, 连接断开");
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            ctx.close();
            return;
        }
        //构造握手响应返回，本机测试
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://" + req.headers().get(HttpHeaders.Names.HOST) + uri, null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
        //握手成功, 添加设备
        ChannelHandlerPool.addClient(clientid, ctx.channel());
        this.setClientStatus(clientid, true);
    }

    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }
}