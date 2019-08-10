package cn.xyr.ssm;

import cn.xyr.ssm.service.activemq.ProducerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.jms.Destination;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SsmApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ActivemqTest {
    @Resource
    private ProducerService producerService;

    @Test
    public void test() {
        // 往info队列中发送消息
        Destination destination = new ActiveMQQueue("info");
        producerService.sendTextMessage(destination, "测试消息");
    }
}

