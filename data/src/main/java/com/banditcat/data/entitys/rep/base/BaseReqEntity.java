package com.banditcat.data.entitys.rep.base;

/**
 * 文件名称：{@link BaseReqEntity}
 * <br/>
 * 功能描述：
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：2017/4/18 16:44
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：2017/4/18 16:44
 * <br/>
 * 修改备注：
 */
public class BaseReqEntity {
    transient String action;//操作类型
    transient String url;//链接地址
    transient String authorization;//认证

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
