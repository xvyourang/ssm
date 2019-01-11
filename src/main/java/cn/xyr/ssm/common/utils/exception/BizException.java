package cn.xyr.ssm.common.utils.exception;


import cn.xyr.ssm.common.utils.enumtype.ResCodeEnum;

import java.util.Map;

/**
 * 公共异常类
 */
public class BizException extends RuntimeException {

    private Map<String, String> mesageMap;
    private String message = ResCodeEnum.ERROR.getResMessage();
    private String code = ResCodeEnum.ERROR.getResCode();

    public BizException(String code, String message) {
        this.message = message;
        this.code = code;
    }


    public BizException(Map<String, String> messageMap, String message, String code) {
        this(message, code);
        this.mesageMap = messageMap;
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
        return mesageMap;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BizException{");
        sb.append("message='").append(message).append('\'');
        sb.append(", code='").append(code).append('\'');
        sb.append('}');
        return sb.toString();
    }
}