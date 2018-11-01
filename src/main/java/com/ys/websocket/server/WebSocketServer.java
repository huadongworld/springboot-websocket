package com.ys.websocket.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author HD
 * @date 2018/10/21 21:20
 */
@Component
@ServerEndpoint("/webSocket/{userId}")
public class WebSocketServer {

    private Session session;
    private static Map<String, Session> sessionPool = new HashMap<>();
    private static Map<String, String> sessionIds = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    /**
     * 用户连接时触发
     *
     * @param session
     * @param userId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userId") String userId) {

        this.session = session;
        sessionPool.put(userId, session);
        sessionIds.put(session.getId(), userId);

        logger.info("【websocket消息】有新的连接: {}, 总连接数:{}", userId, WebSocketServer.getOnlineNum());
    }

    /**
     * 收到信息时触发
     *
     * @param message
     */
    @OnMessage
    public void onMessage(String message) {
        logger.info("【websocket消息】收到客户端发来的消息:{}", message);
    }

    /**
     * 连接关闭触发
     */
    @OnClose
    public void onClose() {

        logger.info("【websocket消息】有连接断开: {}, 总连接数:{}", sessionIds.get(session.getId()), WebSocketServer.getOnlineNum() - 1);

        sessionPool.remove(sessionIds.get(session.getId()));
        sessionIds.remove(session.getId());
    }

    /**
     * 发生错误时触发
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.info("【websocket消息】出现错误信息: {}", error.getMessage());
        error.printStackTrace();
    }

    /**
     * 消息发送
     *
     * @param message
     * @param userId
     */
    public static void sendMessage(String message, String userId) {
        Session session = sessionPool.get(userId);
        if (null != session) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取当前连接数
     * @return
     */
    public static int getOnlineNum(){
        return sessionPool.size();
    }

    /**
     * 获取在线用户名以逗号隔开
     *
     * @return
     */
    public static String getOnlineUsers() {

        StringBuilder users = new StringBuilder();
        for (String key : sessionIds.keySet()) {
            users.append(sessionIds.get(key) + ",");
        }
        return users.toString();
    }


    /**
     * 消息群发
     *
     * @param msg
     */
    public static void sendAll(String msg) {
        for (String key : sessionIds.keySet()) {
            sendMessage(msg, sessionIds.get(key));
        }
    }

    /**
     * 发送消息给指定的几个用户
     *
     * @param msg
     * @param persons
     */
    public static void sendMany(String msg, String[] persons) {
        for (String openid : persons) {
            sendMessage(msg, openid);
        }
    }
}
