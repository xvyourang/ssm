package cn.xyr.ssm.common.config;

import cn.xyr.ssm.interceptor.LoginInterceptor;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * mvc视图解析器配置
 *
 * @author XYR
 * @time 2018/11/21 11:45
 */
@Configuration
public class HtmlInterceptor extends WebMvcConfigurationSupport {
    /**
     * 过滤器白名单列表
     */
    private static List<String> excludePathList = new ArrayList<>();

    static {
        excludePathList.add("/**");
        excludePathList.add("/login");
        excludePathList.add("/static/**");
        excludePathList.add("/user/login");
        excludePathList.add("/user/mobileLogin");
        excludePathList.add("/favicon.ico");
        excludePathList.add("/register.html");
        excludePathList.add("/register");
        excludePathList.add("/user/register");
        excludePathList.add("/user/noteCode");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(excludePathList);//例外
        super.addInterceptors(registry);
    }

    /**
     * 1、 extends WebMvcConfigurationSupport
     * 2、重写下面方法;
     * setUseSuffixPatternMatch : 设置是否是后缀模式匹配，如“/user”是否匹配/user.*，默认true即匹配；
     * setUseTrailingSlashMatch : 设置是否自动后缀路径模式匹配，如“/user”是否匹配“/user/”，默认true即匹配；
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(true)
                .setUseTrailingSlashMatch(true);


    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**") // 静态资源访问路径
                .addResourceLocations("classpath:/static/"); // 静态资源映射路径 映射虚拟路径时使用file:D:/static
        super.addResourceHandlers(registry);
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        //创建fastJson消息转换器
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //修改配置返回内容的过滤
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        MediaType jsonUtf8 = MediaType.APPLICATION_JSON_UTF8;
        List<MediaType> medias = new ArrayList<>();
        medias.add(jsonUtf8);
        fastConverter.setSupportedMediaTypes(medias);
        //将fastjson添加到视图消息转换器列表内
        converters.add(fastConverter);
    }

//    /**
//     * 容器注入 webSocket要使用这种方式注入
//     * 使用@Resource 注入会为null
//     */
//    @Autowired
//    public void setMessageService(ChatBiz chatBiz, OnlineListBiz onlineListBiz) {
//        WebSocketServer.chatBiz = chatBiz;
//        WebSocketServer.onlineListBiz = onlineListBiz;
//    }

}
