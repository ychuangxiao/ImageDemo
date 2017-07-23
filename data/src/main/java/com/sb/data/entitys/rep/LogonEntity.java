package com.sb.data.entitys.rep;


import com.sb.data.entitys.rep.base.BaseReqEntity;

/**
 * 文件名称：{@link LogonEntity}
 * <br/>
 * 功能描述：登录实体
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/5 18:56
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/5 18:56
 * <br/>
 * 修改备注：
 */
public class LogonEntity extends BaseReqEntity {


    private String username;//用户名
    private String password;//密码

    public LogonEntity() {
    }

    public LogonEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
