package cn.xyr.ssm.common.utils.enumtype;

/**
 * @author XYR
 * @time 2018/12/6 13:38
 */
public enum ResCodeEnum {
    /**
     * 请求成功
     */
    SUCCESS("000000", "成功"),
    /**
     * 请求失败
     */
    ERROR("900001","失败"),
    /**
     * 系统异常
     */
    SYSTEM_EXCEPTION("9999999", "系统异常，请联系管理员");
    /**
     * 错误码
     */
    private String resCode;
    /**
     * 错误信息
     */
    private String resMessage;

    ResCodeEnum(String resCode, String resMessage) {
        this.resCode = resCode;
        this.resMessage = resMessage;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }
}
