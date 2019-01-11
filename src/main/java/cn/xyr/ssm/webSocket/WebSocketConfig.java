package cn.xyr.ssm.webSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 首先要注入ServerEndpointExporter，这个bean会自动注册使用了@ServerEndpoint注解声明的Websocket endpoint。
 * 要注意，如果使用独立的servlet容器，而不是直接使用springboot的内置容器，就不要注入ServerEndpointExporter，
 * 因为它将由容器自己提供和管理。
 *
 *
 * SpringBoot+WebSocket 对每个客户端连接都会创建一个 WebSocketServer（@ServerEndpoint 注解对应的）
 * 对象，Bean 注入操作会被直接略过，因而手动注入一个全局变量
 */
//@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /**
     * 容器注入 webSocket要使用这种方式注入
     * 使用@Resource 注入会为null
     */
//    @Autowired
//    public void setMessageService(ChatBiz chatBiz, OnlineListBiz onlineListBiz) {
//        WebSocketServer.chatBiz = chatBiz;
//        WebSocketServer.onlineListBiz=onlineListBiz;
//    }

}