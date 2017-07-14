package com.banditcat.app.model.base;

import com.banditcat.data.entitys.rep.base.BaseReqEntity;
import com.ilogie.android.transformer.annotation.Filed;
import com.ilogie.android.transformer.annotation.MappedClass;


/**
 * 文件名称：{@link BaseReqModel}
 * <br/>
 * 功能描述：请求基类
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
@MappedClass(with = BaseReqEntity.class)
public class BaseReqModel {
    @Filed
    String action;//操作类型

    @Filed
    String url;//链接地址
    @Filed
    String authorization;//认证

    boolean checkSn;//是否检测条码

    Integer handlePage;
    Integer handleTotalPage;

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


    public Integer getHandlePage() {
        return handlePage;
    }

    public void setHandlePage(Integer handlePage) {
        this.handlePage = handlePage;
    }

    public Integer getHandleTotalPage() {
        return handleTotalPage;
    }

    public void setHandleTotalPage(Integer handleTotalPage) {
        this.handleTotalPage = handleTotalPage;
    }

    public boolean isCheckSn() {
        return checkSn;
    }

    public void setCheckSn(boolean checkSn) {
        this.checkSn = checkSn;
    }
}
