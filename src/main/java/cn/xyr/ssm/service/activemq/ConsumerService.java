package cn.xyr.ssm.service.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.*;

/**
 * 消息消费者
 *
 * @author xyr
 * @time 2019年8月3日 15:14:26
 */
@Component
public class ConsumerService {


    /**
     * 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
     *
     * @param text 接收到的消息
     */
    @JmsListener(destination = "allInfo")
    public void receiveQueueAll(String text) {
        System.out.println("allInfo收到的报文为:" + text);
        /*
         *业务逻辑
         */
    }

    @JmsListener(destination = "subscribeInfo")
    public void receiveQueueSubscribe(String text) {
        System.out.println("subscribeInfo收到的报文为:" + text);
    }

    @JmsListener(destination = "InfoMap")
    public void receiveQueueMap(MapMessage map) {
        try {
            System.out.println("allInfo收到的报文为:" + map.getString("String"));

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    /**
     * 处理各种类型消息
     *
     * @param message 消息
     */
    @JmsListener(destination = "info")
    public void receiveQueue(Message message) {
        try {

            if (message instanceof TextMessage) {
                //接收文本消息
                TextMessage textMessage = (TextMessage) message;
                System.out.println(textMessage.getText());
            } else if (message instanceof MapMessage) {
                //接收键值对消息
                MapMessage mapMessage = (MapMessage) message;
                System.out.println(mapMessage.getString("name"));
                System.out.println(mapMessage.toString());
            } else if (message instanceof StreamMessage) {
                //接收流消息
                StreamMessage streamMessage = (StreamMessage) message;
                System.out.println(streamMessage.readString());
                System.out.println(streamMessage.readLong());
            } else if (message instanceof BytesMessage) {
                //接收字节消息
                byte[] b = new byte[1024];
                int len;
                BytesMessage bytesMessage = (BytesMessage) message;
                while ((len = bytesMessage.readBytes(b)) != -1) {
                    System.out.println(new String(b, 0, len));
                }
            } else if (message instanceof ObjectMessage) {
                //接收对象消息
                ObjectMessage objectMessage = (ObjectMessage) message;
                System.out.println(objectMessage.toString());
            } else {
                System.out.println(message);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


}