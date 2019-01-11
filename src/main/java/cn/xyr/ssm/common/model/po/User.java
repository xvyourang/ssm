package cn.xyr.ssm.common.model.po;

import java.util.Date;

public class User {
    private Integer id;

    private String userName;

    private String password;

    private String salt;

    private String headImg;

    private String mobile;

    private Boolean isLandlord;

    private String notecode;

    private Date codeTime;

    private Date createTime;

    private Date updateTime;

    private Boolean isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Boolean getIsLandlord() {
        return isLandlord;
    }

    public void setIsLandlord(Boolean isLandlord) {
        this.isLandlord = isLandlord;
    }

    public String getNotecode() {
        return notecode;
    }

    public void setNotecode(String notecode) {
        this.notecode = notecode == null ? null : notecode.trim();
    }

    public Date getCodeTime() {
        return codeTime;
    }

    public void setCodeTime(Date codeTime) {
        this.codeTime = codeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}