package com.sb.app.model;

import com.ilogie.android.transformer.annotation.Filed;
import com.ilogie.android.transformer.annotation.MappedClass;
import com.sb.app.model.base.BaseReqModel;
import com.sb.data.entitys.rep.base.BaseCrashReqEntity;

/**
 * Created by admin on 2017/8/1.
 */

@MappedClass(with = BaseCrashReqEntity.class)
public class CrashModel extends BaseReqModel {

    @Filed(toField = "model")
    private String erModel;//手机机型
    @Filed(toField = "modelVer")
    private String erVersion;//手机版本
    @Filed(toField = "msg")
    private String erDescr;//异常描述

    public CrashModel() {
    }

    public CrashModel(String erModel, String erVersion, String erDescr) {
        this.erModel = erModel;
        this.erVersion = erVersion;
        this.erDescr = erDescr;
    }

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