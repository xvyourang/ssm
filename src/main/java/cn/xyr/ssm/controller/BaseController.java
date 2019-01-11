package cn.xyr.ssm.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * BaseController
 *
 * @author weixiang.wu
 * @date 2018 -04-01 13:16
 */
@Controller
public class BaseController {

    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    /**
     * The com.zhuoan.constant IPV4_LOCAL.
     */
    private static final String IPV4_LOCAL = "127.0.0.1";
    /**
     * The com.zhuoan.constant IPV6_LOCAL.
     */
    private static final String IPV6_LOCAL = "0:0:0:0:0:0:0:1";
    /**
     * The com.zhuoan.constant IP_UNKNOWN.
     */
    private static final String IP_UNKNOWN = "unknown";

    /**
     * Gets the ip addr. 获取访问者真实IP地址
     *
     * @param request the request
     * @return the ip addr
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || IP_UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals(IPV4_LOCAL) || ip.equals(IPV6_LOCAL)) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.error("未知主机异常:", e);
                }
                if (inet != null) {
                    ip = inet.getHostAddress();
                }
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (StringUtils.isEmpty(ip) && ip.contains(",")) {
            ip = ip.substring(0, ip.indexOf(','));
        }
        return ip;
    }


//    /**
//     * Object to json string.
//     *
//     * @param <T> the type parameter
//     * @param t   the t
//     * @return the string
//     */
//    protected <T> String toJson(T t) {
//        try {
//            String response = JSONObject.toJSONString(t);
//            log.info("接口响应的数据response=[" + response + "]");
//            return response;
//        } catch (Exception e) {
//            log.info("接口返回数据转为json格式错误:", e);
//            return null;
//        }
//    }

    /**
     * 日期格式转换过滤
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {//日期格式转换过滤
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyyMMdd"), true
        ));
    }


}
