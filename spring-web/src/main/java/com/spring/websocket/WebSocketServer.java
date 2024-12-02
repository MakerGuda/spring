package com.spring.websocket;

import cn.hutool.core.date.DateUtil;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
@Component
@ServerEndpoint("/websocket/{identity}")
public class WebSocketServer {

    private static final AtomicInteger onlineCount = new AtomicInteger(0);

    private static final Map<String, Session> sessions = new ConcurrentHashMap<>();

    private static AtomicBoolean running = new AtomicBoolean(false);

    /**
     * 建立连接
     *
     * @param session  与客户端的会话，通过session给客户端发送消息
     * @param identity 客户端传过来的自定义标识
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("identity") String identity) {
        log.info("当前websocket建立连接中 =======> session_id:{}, identity:{}", session.getId(), identity);
        onlineCount.incrementAndGet();
        sessions.put(identity, session);
        log.info("当前websocket建立连接成功 =======> 当前总的连接数为:{}, session_id:{}, identity:{}", onlineCount.get(), session.getId(), identity);
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose(Session session, @PathParam("identity") String identity) {
        log.info("当前websocket关闭连接中 =======> session_id:{}, identity:{}", session.getId(), identity);
        Session pre = sessions.remove(identity);
        if (pre != null) {
            onlineCount.decrementAndGet();
        }
        log.info("当前websocket关闭连接成功 =======> 当前总的连接数为:{}, 关闭的连接信息为session_id:{}, identity:{}", onlineCount.get(), session.getId(), identity);
    }

    /**
     * 发生和接收到信息
     */
    @OnMessage
    public void onMessage(Session session, String message) {
        log.info("websocket接收消息，message:{}, session_id:{}", message, session.getId());
    }

    /**
     * 发生错误
     */
    @OnError
    public void onError(Session session, Throwable e) {
        log.error("websocket发生错误，错误信息为{}", e.getMessage(), e);
    }

    public void sendToAll() {
        boolean success = running.compareAndSet(false, true);
        while (success && onlineCount.get() > 0) {
            sessions.values().forEach(session -> {
                try {
                    session.getBasicRemote().sendText("websocket服务端向客户端发送消息， 当前时间为:" + DateUtil.format(new Date(), "yyyy-MM-dd hh:mm:ss:SSS"));
                } catch (IOException e) {
                    log.error("websocket发送消息异常, {}", e.getMessage(), e);
                }
            });
        }
    }

}
