package com.banditcat.data.entitys.rep;


import com.banditcat.data.entitys.rep.base.BaseReqEntity;

/**
 * 文件名称：{@link RegReqEntity}
 * <br/>
 * 功能描述：注册请求实体
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：2017/4/28 13:34
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：2017/4/28 13:34
 * <br/>
 * 修改备注：
 */
public class RegReqEntity extends BaseReqEntity {



    private String name;
    private String pwd;
    private String qq;
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
