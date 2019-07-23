package cn.xyr.ssm.common.model.constant;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author XYR
 * @Create time: @2018/8/7} 22:17
 */
public class StringConst {

    /**
     * 将User保存到session中的属性名称
     */
    public static final String USER_SESSION_SING = "user";

    /**
     * 缓存session
     */
    public static final String SESSION_CACHE_NAME = "session";
    /**
     * 单点登录
     */
    public static final String SESSION_ID_KEY_PREFIX = "session_id_service_";

    public static final Set<String> imgSet = new HashSet<>();


    static {
        imgSet.add(".jpg");
        imgSet.add(".jpeg");
        imgSet.add(".png");
        imgSet.add(".pcd");
        imgSet.add(".psd");
        imgSet.add(".dxf");
        imgSet.add(".tiff");
        imgSet.add(".pcx");
    }

}
