package cn.xyr.ssm.service.webSocket;

import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author XYR
 * @time 2018/11/21 16:02
 */
public class WebSocketConstant {


    /**
     * 接入的所有用户连接session
     */
    private static final ConcurrentHashMap<String, Session> userMap = new ConcurrentHashMap<>();


    public static int size() {
        return userMap.size();
    }

    public static void add(String id, Session session) {
        userMap.put(id, session);
    }

    public static boolean remove(String id) {
        return userMap.remove(id) != null;
    }

    public static Session get(String id) {
        return userMap.get(id);
    }


    /**
     * 清除所有连接
     * 并没有断开连接
     */
    public static void clear() {
        userMap.clear();
    }

    /**
     * 关闭连接
     */
    public void close(Session session) throws IOException {
        session.close();
    }

    public static boolean isOnline(String id) {
        return userMap.containsKey(id);
    }
}
