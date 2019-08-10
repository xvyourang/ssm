package cn.xyr.ssm.common.utils.exception;


import cn.xyr.ssm.common.utils.enumtype.ResCodeEnum;

import java.util.Map;

/**
 * 公共异常类
 *
 * @author xyr
 * @time 2019年8月10日 16:43:35
 */
public class BizException extends RuntimeException {

    private Map<String, String> messageMap;
    private String message = ResCodeEnum.ERROR.getResMessage();
    private String code = ResCodeEnum.ERROR.getResCode();

    public BizException(String code, String message) {
        this.message = message;
        this.code = code;
    }


    public BizException(Map<String, String> messageMap, String message, String code) {
        this(message, code);
        this.messageMap = messageMap;
    }


    public BizException(String message) {
        this.message = message;
    }


    public BizException(Exception e) {
        super(e);
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets message map.
     *
     * @return the message map
     */
    public Map<String, String> getMessageMap() {
        return messageMap;
    }


    @Override
    public String toString() {
        return "BizException{" +
                "messageMap=" + messageMap +
                ", message='" + message + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}