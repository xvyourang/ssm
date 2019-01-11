package cn.xyr.ssm.common.service.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.MapMessage;

@Service("producer")
public class ProducerService {
    @Autowired // 也可以注入JmsTemplate，JmsMessagingTemplate对JmsTemplate进行了封装
    private JmsTemplate jmsTemplate;

    // 发送消息，destination是发送到的队列，message是待发送的消息
    public void sendTextMessage(Destination destination, final String message) {
        System.out.println("producer发出" + message);
        jmsTemplate.convertAndSend(destination, message);
    }

    // 发送消息，destination是发送到的队列，message是待发送的消息
    public void sendMapMessage(Destination destination, final MapMessage mapMessage) {
        System.out.println("producer发出mapMessage [" + mapMessage + "]");
        jmsTemplate.convertAndSend(destination, mapMessage);
    }
}