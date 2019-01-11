package cn.xyr.ssm.common.utils.enumtype;

/**
 * @author XYR
 * @time 2018/12/6 13:38
 */
public enum ResCodeEnum {
    SUCCESS("000000", "成功"),
    ERROR("900001","失败");

    private String resCode;
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