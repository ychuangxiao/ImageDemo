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

    private String type="ERROR"; //错误日志或其他日志  目前只写ERROR
    private String model; // 记录机型
    private String msg;
    private String modelVer; // 记录机型版本


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getModelVer() {
        return modelVer;
    }

    public void setModelVer(String modelVer) {
        this.modelVer = modelVer;
    }
}
