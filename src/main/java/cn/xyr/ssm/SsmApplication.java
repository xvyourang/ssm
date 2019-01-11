package cn.xyr.ssm;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//如果mybatis中service实现类中加入事务注解，需要此处添加该注解
@EnableTransactionManagement
//扫描的是mapper.xml中namespace指向值的包位置
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@MapperScan(value = {"cn.xyr.ssm.common.dao.mapper"})
public class SsmApplication {
    private final static Logger log = LoggerFactory.getLogger(SsmApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(SsmApplication.class, args);
        Environment env = application.getEnvironment();
        String port = env.getProperty("server.port");
        log.info("http://localhost:" + port);
    }
}
