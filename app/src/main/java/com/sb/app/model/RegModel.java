package com.sb.app.model;


import com.sb.app.model.base.BaseReqModel;
import com.sb.data.entitys.rep.RegReqEntity;
import com.ilogie.android.transformer.annotation.Filed;
import com.ilogie.android.transformer.annotation.MappedClass;

/**
 * 文件名称：{@link RegModel}
 * <br/>
 * 功能描述：注册请求实体
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：2017/4/28 13:34
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：2017/4/28 13:34
 * <br/>
 * 修改备注：
 */
@MappedClass(with = RegReqEntity.class)
public class RegModel extends BaseReqModel {

    @Filed
    private String name;
    @Filed
    private String pwd;
    @Filed
    private String qq;
    @Filed
    private String activeCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }
}
