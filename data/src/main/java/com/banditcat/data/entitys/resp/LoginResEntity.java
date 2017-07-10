package com.banditcat.data.entitys.resp;


/**
 * 文件名称：{@link LoginResEntity}
 * <br/>
 * 功能描述：用户登录响应实体
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/5 18:55
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：16/6/5 18:55
 * <br/>
 * 修改备注：
 */
public class LoginResEntity {



    private String authCode;
    private String isactive;
    private String userCode;

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
