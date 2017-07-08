package com.banditcat.data.entitys.rep;


import com.banditcat.data.entitys.rep.base.BaseReqEntity;

/**
 * 文件名称：{@link LogonEntity}
 * <br/>
 * 功能描述：登录实体
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/5 18:56
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：16/6/5 18:56
 * <br/>
 * 修改备注：
 */
public class LogonEntity extends BaseReqEntity {


    private String corp;//账套号
    private String username;//登录名
    private String password;//登录密码
    private DeviceEntity deviceInfo;//设备信息

    public LogonEntity(String corp, String username, String password) {
        this.corp = corp;
        this.username = username;
        this.password = password;
    }

    public LogonEntity(String corp, String username, String password, DeviceEntity deviceInfo) {
        this.corp = corp;
        this.username = username;
        this.password = password;
        this.deviceInfo = deviceInfo;
    }


    public DeviceEntity getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(DeviceEntity deviceInfo) {
        this.deviceInfo = deviceInfo;
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

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }
}
