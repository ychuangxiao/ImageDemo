package com.banditcat.data.entitys.realm;


import io.realm.RealmObject;

/**
 * 文件名称：{@link UserRealm}
 * <br/>
 * 功能描述：用户登录信息实体(本地数据库)
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/3 11:56
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：16/6/3 11:56
 * <br/>
 * 修改备注：
 */
public class UserRealm extends RealmObject {



    private String userUid;//用户账号
    private String password;//认证信息
    private long latestDate;//最后登录时间戳
    private String userId;//用户登录编号
    private long active=10L;



    public UserRealm() {
    }


    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getLatestDate() {
        return latestDate;
    }

    public void setLatestDate(long latestDate) {
        this.latestDate = latestDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getActive() {
        return active;
    }

    public void setActive(long active) {
        this.active = active;
    }
}
