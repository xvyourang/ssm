package cn.xyr.ssm;

import cn.xyr.ssm.service.web.JedisService;
import cn.xyr.ssm.service.activemq.ProducerService;
import cn.xyr.ssm.common.utils.HttpUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SsmApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SsmApplicationTests {
    @Resource
    ProducerService producerService;
    @Resource
    JedisService jedisBiz;

    public static void main(String[] args) {
        HttpUtils.postWithParamsForString("http://localhost:80",null);
    }
    @Test
    public void teesttt(){
       jedisBiz.set("test999","sadaf4asda");

    }
}
