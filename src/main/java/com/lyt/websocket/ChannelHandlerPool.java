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
    public static Channel getChannel(String clientID) {
        Channel sc = clientMap.get(clientID);
        return sc;
    }

    /**
     * 获取连接id
     */
    public static String getClientid(Channel conn) {
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
    public static synchronized void addClient(String clientid, Channel conn) {
        if (clientMap.get(clientid) != null) {//重复连接
            log.info("重复连接: " + clientid);
            removeClient(clientid);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }

        log.info("设备连接: " + clientid + ", session: " + conn.id().asShortText());
        if (conn != null) {
            clientMap.put(clientid, conn);    //添加连接
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
    public static void onRemoveClient(String clientid) {
        log.info("设备已断开连接: " + clientid);
        if (StringUtils.isEmpty(clientid)) {
            return;
        }
        Channel conn = clientMap.get(clientid);
        clientMap.remove(clientid);  //移除连接
        onLineCount.decrementAndGet();
        log.info("当前设备数：" + onLineCount.intValue());
    }

    /**
     * 移除连接池中的连接
     */
    public static boolean removeClient(String clientid) {
        if (StringUtils.isEmpty(clientid)) {
            return false;
        }
        Channel conn = clientMap.get(clientid);
        log.info("服务器主动断开连接: " + clientid + ", session: " + conn.id().asShortText());
        if (conn != null) {
            if (conn != null) {
                conn.close();
            }
            return true;
        } else {
            return false;
        }
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
        sendMessageToClient(getClientid(conn), data);
    }

    /**
     * 向特定的用户发送数据
     */
    public static void sendMessageToClient(String clientId, Object data) {
        if (data == null) {
            return;
        }
        log.debug("向clientId[{}]发送消息: {}", clientId, JSON.toJSONString(data));
        if (clientMap.containsKey(clientId)) {
            sendMessage(clientMap.get(clientId), data);
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