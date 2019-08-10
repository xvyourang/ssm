package cn.xyr.ssm.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器，状态拦截。
 * 如果用户没用登录，无法访问页面
 *
 * @author XYR
 * @time 2018/11/21 22:18
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 验证失败返回路径
     */
    private static final String LOGIN_URL = "/login";
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);


    /**
     * 具体拦截规则
     *
     * @return 是否拦截
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
//        String url = request.getRequestURI();
//        UserVO user = (UserVO) request.getSession().getAttribute(StringConst.USER_SESSION_SING);
//        if (user != null) {
//            /* 当前sessionId */
//            String sessionId = StringUtils.trim(request.getSession().getId());
//            String userSessionKey = StringConst.SESSION_ID_KEY_PREFIX + user.getId();
//            //获取已登录用户的sessionId
//            String userSessionId = (String) request.getSession().getServletContext().getAttribute(userSessionKey);
////            logger.info("userSessionId=" + userSessionId);
//
//            /* 判断当前session和redis里的session是否一致，如果不一致则销毁当前session跳转到登录页 */
//            if (!StringUtils.equals(sessionId, userSessionId)) {
//                request.getSession().invalidate();
//                //Ajax 请求另作处理
//                String type = request.getHeader("X-Requested-With");
//
//                if (StringUtils.equals(type, "XMLHttpRequest")) {
//
//                    response.setHeader("redirectUrl", LOGIN_URL);
//                } else {
//                    response.sendRedirect(LOGIN_URL);
//                }
//                return false;
//            } else {
//                //sessionId一样说明是同一个用户在登录
//                request.getSession().setMaxInactiveInterval(30 * 60);
//                return true;
//            }
//        } else {
//            //Ajax 请求另作处理
//            String type = request.getHeader("X-Requested-With");
//
//            if (StringUtils.equals(type, "XMLHttpRequest")) {
//
//                response.setHeader("redirectUrl", LOGIN_URL);
//            } else {
//                response.sendRedirect(LOGIN_URL);
//            }
//        }
//        return false;
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
}
