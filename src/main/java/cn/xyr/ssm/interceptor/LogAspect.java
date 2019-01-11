package cn.xyr.ssm.interceptor;

import cn.xyr.ssm.common.model.dto.WebDTO;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author XYR
 * @time 2018/12/18 14:52
 */
@Component
@Aspect
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    //    @Pointcut("execution(* cn.lsf_homestay.controller..*.*(..))")
//    public void aop() {
//
//    }

    /**
     * 增加响应切面，打印响应数据。
     */
    @AfterReturning(value = "within(cn.xyr.ssm.controller..*)", returning = "dto")
    public void after(JoinPoint joinPoint, Object dto) {
        if (dto instanceof WebDTO) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            log.info("接口" + method.getDeclaringClass().getName() + "." + method.getName() + "响应数据为【" + JSONObject.toJSONString(dto) + "】");
        }
    }

}
