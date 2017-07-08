package com.banditcat.data.entitys.rep;

/**
 * 文件名称：{@link DeviceEntity}
 * <br/>
 * 功能描述：设备信息
 * <br/>
 * 创建作者：banditcat-pc
 * <br/>
 * 创建时间：2017/6/27 15:47
 * <br/>
 * 修改作者：banditcat-pc
 * <br/>
 * 修改时间：2017/6/27 15:47
 * <br/>
 * 修改备注：
 */
public class DeviceEntity {

    private String imei;//移动设备唯一码
    private String model;//机型
    private String osVer;//操作系统版本

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOsVer() {
        return osVer;
    }

    public void setOsVer(String osVer) {
        this.osVer = osVer;
    }
}
