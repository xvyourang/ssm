package cn.xyr.ssm.handler;

import cn.xyr.ssm.model.dto.WebDTO;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 接口请求日志打印切面
 * 使用@Profile注解来指定只有test模式时打印
 *
 * @author XYR
 * @time 2018/12/18 14:52
 */

@Profile("test")
@Component
@Aspect
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    //    @Pointcut("execution(* cn.lsf_homestay.controller..*.*(..))")
//    public void aop() {
//
//    }

    /**
     * 接口响应前切面，打印响应数据。
     */
    @AfterReturning(value = "within(cn.xyr.ssm.controller..*)", returning = "dto")
    public void after(JoinPoint joinPoint, Object dto) {
        if (dto instanceof WebDTO) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            log.debug("接口[{}.{}],响应数据为[{}]", method.getDeclaringClass().getName(), method.getName(), JSONObject.toJSONString(dto));
        }
    }

    /**
     * 接口进入时切面，打印请求数据
     */
    @Before(value = "within(cn.xyr.ssm.controller..*)")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        log.debug("接口[{}.{}],请求数据为[{}]", method.getDeclaringClass().getName(), method.getName(), JSONObject.toJSONString(joinPoint.getArgs()));
    }
}
