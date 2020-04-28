package com.lyt.websocket;

import com.alibaba.fastjson.JSON;
import com.lyt.exception.MsgException;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * channel池，管理所有websocket连接
 */
@Component
@Slf4j
public class ChannelHandlerPool {
    private ChannelHandlerPool() {
    }

    //会话集合
    private static final ConcurrentSkipListMap<String, Channel> clientMap = new ConcurrentSkipListMap<>();

    //静态变量，用来记录当前在线连接数。（原子类、线程安全）
    public static volatile AtomicInteger onLineCount = new AtomicInteger();

    /**
     * 获取连接
     */
    public static Channel getChannel(String devCode) {
        Channel sc = clientMap.get(devCode);
        return sc;
    }

    /**
     * 获取连接id
     */
    public static String getDevCode(Channel conn) {
        for (ConcurrentSkipListMap.Entry<String, Channel> entry : clientMap.entrySet()) {
            if (entry.getValue().id().asShortText().equals(conn.id().asShortText())) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * 向连接池中添加连接
     */
    public synchronized static void addClient(String devCode, Channel conn) {
        removeClient(devCode);
        log.info("设备连接: " + devCode + ", session: " + conn.id().asShortText());
        if (conn != null) {
            clientMap.put(devCode, conn);    //添加连接
            onLineCount.incrementAndGet();
            log.info("当前设备数：" + onLineCount.intValue());
        }
    }

    /**
     * 获取所有的在线用户
     *
     * @return
     */
    public static Collection<String> getOnlineClient() {
        Set<String> setUsers = clientMap.keySet();
        return setUsers;
    }

    /**
     * 移除连接池中的连接
     */
    public static void onRemoveClient(String devCode) {
        log.info("设备已断开连接: " + devCode);
        if (StringUtils.isEmpty(devCode)) {
            return;
        }
        clientMap.remove(devCode);  //移除连接
        onLineCount.decrementAndGet();
        log.info("当前设备数：" + onLineCount.intValue());
    }

    /**
     * 移除连接池中的连接
     */
    public static boolean removeClient(String devCode) {
        Channel conn = clientMap.get(devCode);
        if (conn == null) {
            return false;
        }
        log.info("服务器主动断开连接: " + devCode + ", session: " + conn.id().asShortText());
        onRemoveClient(devCode);
        conn.close();
        return true;
    }

    /**
     * 向特定的用户发送数据
     */
    private static void sendMessage(Channel conn, Object data) {
        if (conn != null && !"".equals(data)) {
            if (data instanceof String) {
                conn.writeAndFlush(new TextWebSocketFrame((String) data));
            } else {
                conn.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(data)));
            }
        }
    }


    public static void sendMessageToClient(Channel conn, Object data) {
        sendMessageToClient(getDevCode(conn), data);
    }

    /**
     * 向特定的用户发送数据
     */
    public static void sendMessageToClient(String devCode, Object data) {
        if (data == null) {
            return;
        }
        log.debug("向devCode[{}]发送消息: {}", devCode, JSON.toJSONString(data));
        if (clientMap.containsKey(devCode)) {
            sendMessage(clientMap.get(devCode), data);
        } else {
            throw new MsgException("设备未连接");
        }
    }

    /**
     * 向所有的用户发送消息
     *
     * @param data
     */
    public static void sendMessageToAll(Object data) {
        if (data == null) {
            return;
        }
        log.debug("向所有的用户发送消息:{}", JSON.toJSONString(data));
        Collection<Channel> cs = clientMap.values();
        synchronized (cs) {
            for (Channel conn : cs) {
                if (conn != null) {
                    sendMessage(conn, data);
                }
            }
        }
    }
}