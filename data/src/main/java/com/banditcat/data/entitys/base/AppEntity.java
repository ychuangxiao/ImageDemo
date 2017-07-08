package com.banditcat.data.entitys.base;

import android.content.Context;

/**
 * 文件名称：{@link AppEntity}
 * <br/>
 * 功能描述：App实体
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：2017/4/18 15:52
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：2017/4/18 15:52
 * <br/>
 * 修改备注：
 */
public class AppEntity {

    private Context mContext;

    public AppEntity(Integer appVersion, String appClientName) {
        this.appVersion = appVersion;
        this.appClientName = appClientName;
    }

    public AppEntity(Context context, Integer appVersion, String appClientName) {
        mContext = context;
        this.appVersion = appVersion;
        this.appClientName = appClientName;
    }

    private Integer appVersion;
    private String appClientName;

    public Integer getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(Integer appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppClientName() {
        return appClientName;
    }

    public void setAppClientName(String appClientName) {
        this.appClientName = appClientName;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }
}
