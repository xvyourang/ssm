package cn.xyr.ssm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * web容器中进行部署
 */
//@SpringBootApplication
//@ServletComponentScan
public class SsmServletInitializer extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SsmApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return application.sources(SsmApplication.class);
    }
}
