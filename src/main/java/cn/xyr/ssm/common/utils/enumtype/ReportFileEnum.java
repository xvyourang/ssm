package cn.xyr.ssm.common.utils.enumtype;

/**
 * ReportFileEnum(表名，列名，参数名枚举类)
 * 此枚举用于导出Execl表
 *
 * @author A
 */
public enum ReportFileEnum {

    /**
     * 用户
     */
    USER("用户", "用户名|密码|电话|年龄|生日", "userName|password|tel|age|birthdayStr");


    /**
     * The Filename.
     */
    private String filename;
    /**
     * The Title names.
     */
    private String[] titleNames;
    /**
     * The Param names;
     */
    private String[] params;

    /**
     * Instantiates a new Report file enum.
     *
     * @param filename   the filename
     * @param titleNames the title names
     * @param params     the param names
     */
    ReportFileEnum(String filename, String titleNames, String params) {
        this.filename = filename;
        this.titleNames = titleNames.split("\\|");
        this.params = params.split("\\|");
    }

    /**
     * Gets filename.
     *
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Get title names.
     *
     * @return the string [ ]
     */
    public String[] getTitleNames() {
        return titleNames;
    }

    public String[] getParams() {
        return params;
    }
}
