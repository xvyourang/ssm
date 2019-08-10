package cn.xyr.ssm.handler;

import cn.xyr.ssm.model.dto.WebDTO;
import cn.xyr.ssm.common.utils.enumtype.ResCodeEnum;
import cn.xyr.ssm.common.utils.exception.BizException;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常拦截器
 *
 * @author XYR
 * @time 2018/11/21 22:18
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public String handlerException(Throwable throwable) {
        String result = null;
        // map中的内容将存放在放回的json字符串中
        Map<String, String> map = new HashMap<String, String>();
        if(throwable instanceof BizException){
            BizException biz=(BizException)throwable;
            log.error("业务异常", biz);
            //重置result 返回业务异常
            if (biz.getMessageMap() != null) {
                // 如果bizException包含map则直接使用
                map = biz.getMessageMap();
            }
            map.put(WebDTO.RES_CODE, biz.getCode());
            map.put(WebDTO.RES_MSG, biz.getMessage());
            try {
                result = JSONObject.toJSONString(map);
            } catch (Exception e) {
                log.info("接口返回数据转为json格式错误:", e);
            }
        }else {

            log.error("系统异常", throwable);
            //重置result 返回系统异常
            map.put(WebDTO.RES_CODE, ResCodeEnum.SYSTEM_EXCEPTION.getResCode());
            map.put(WebDTO.RES_MSG, ResCodeEnum.SYSTEM_EXCEPTION.getResMessage());
            try {
                result = JSONObject.toJSONString(map);
            } catch (Exception ex) {
                log.info("接口返回数据转为json格式错误:", ex);
            }
        }
        return result;
    }
}
