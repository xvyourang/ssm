package cn.xyr.ssm;

import cn.xyr.ssm.common.biz.JedisBiz;
import cn.xyr.ssm.common.service.activemq.ProducerService;
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
    JedisBiz jedisBiz;

    public static void main(String[] args) {
        HttpUtils.postWithParamsForString("http://localhost:80",null);
    }
    @Test
    public void teesttt(){
       jedisBiz.set("test999","sadaf4asda");

    }
}
