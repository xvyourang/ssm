package cn.xyr.ssm.webSocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * ServerEndpoint注解指定webSocket连接接口名
 *
 * @author xyr
 * @time 2018/10/24 11:04
 */
@ServerEndpoint(value = "/webSocket")
@Component
public class WebSocketServer {

    private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);
//
//    public static ChatBiz chatBiz;
//
//    public static OnlineListBiz onlineListBiz;

    /**
     * 连接建立成功调用的方法
     * 有会话id 那么可以通过去拿用户的会话
     */
    @OnOpen
    public void onOpen(Session session) {
        WebSocketConstant.add(session.getId(),session);
        System.out.println("有新连接加入！当前在线人数为" + (WebSocketConstant.size() + 1));
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        WebSocketConstant.remove(session.getId());  //从set中删除
        String id = session.getId();
        System.out.println("有一连接关闭！当前在线人数为" + WebSocketConstant.size() + ",id=[" + id + "]");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
//        System.out.println("来自客户端的消息:" + message);
//        try {
//            Map<String, String> map = new HashMap<>();
//            String[] strings = message.split(StringUtils.SEPARATOR);
//            for (String s : strings) {
//                int index = s.indexOf('=');
//                map.put(s.substring(0, index), s.substring(index + 1));
//            }
//            if (joinOrOut(message, session, map)) {
//                return;
//            }
//            int userId = Integer.parseInt(map.get("userId"));
//            int friendId = Integer.parseInt(map.get("friendId"));
//            check(userId, friendId, session);
//            String info = map.get("info");
//            String res = chatBiz.chat(userId, friendId, info);
//            if (res != null) {
//                sendMessage(info, WebSocketConstant.get(friendId + "-" + userId));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("参数错误");
//            log.error("参数错误");
//        }
//        String res = "服务器返回[" + message + "]";
//        sendMessage(res, session);
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message, Session session) throws IOException {
        session.getBasicRemote().sendText(message);//同步
        //session.getAsyncRemote().sendText(message);//异步
    }

}