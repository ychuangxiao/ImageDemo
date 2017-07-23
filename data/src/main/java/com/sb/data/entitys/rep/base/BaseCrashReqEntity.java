package com.sb.data.entitys.rep.base;


/**
 * 文件名称：{@link BaseCrashReqEntity}
 * <br/>
 * 功能描述：异常基类实体
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：2017/4/18 16:27
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：2017/4/18 16:27
 * <br/>
 * 修改备注：
 */
public class BaseCrashReqEntity extends BaseReqEntity {

    private String erModel;//手机机型
    private String erVersion;//手机版本
    private String erDescr;//异常描述


    public String getErModel() {
        return erModel;
    }

    public void setErModel(String erModel) {
        this.erModel = erModel;
    }

    public String getErVersion() {
        return erVersion;
    }

    public void setErVersion(String erVersion) {
        this.erVersion = erVersion;
    }

    public String getErDescr() {
        return erDescr;
    }

    public void setErDescr(String erDescr) {
        this.erDescr = erDescr;
    }


}
