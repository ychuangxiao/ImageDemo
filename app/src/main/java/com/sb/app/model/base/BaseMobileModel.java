package com.sb.app.model.base;

import com.sb.app.constant.AppConstant;

import java.io.Serializable;

/**
 * 文件名称：{@link BaseMobileModel}
 * <br/>
 * 功能描述：手机外观
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：2017/7/14 14:32
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：2017/7/14 14:32
 * <br/>
 * 修改备注：
 */
public class BaseMobileModel implements Serializable {


    /**
     * 10:黑色 20 白色
     */
    private Integer toolbarType= AppConstant.ACTION_10;//主要是 黑色 还是 白色
    private Integer topToolStyle;//工具栏样式
    private Integer mobileType;//手机类型
    private Integer networkType;//网络类型
    private Integer networkSignal;//信号强度
    private String mobileCarrier;//手机运营商


    private Long topTime;//顶部时间


    private Boolean dateTimeStyle = false;//是否24小时
    private Boolean isLocation = false;//是否显示定位
    private Boolean isBlueTeeth = false;//是否显示蓝牙
    private Boolean isBatteryNum = false;//是否显示百分比
    private Boolean isBatteryAdd = false;//是否充电中
    private Boolean isDir = false;//是否旋转
    private Integer batteryNumBar = 80;

    public Integer getTopToolStyle() {
        return topToolStyle;
    }

    public void setTopToolStyle(Integer topToolStyle) {
        this.topToolStyle = topToolStyle;
    }

    public Integer getMobileType() {
        return mobileType;
    }

    public void setMobileType(Integer mobileType) {
        this.mobileType = mobileType;
    }

    public Integer getNetworkType() {
        return networkType;
    }

    public void setNetworkType(Integer networkType) {
        this.networkType = networkType;
    }

    public Integer getNetworkSignal() {
        return networkSignal;
    }

    public void setNetworkSignal(Integer networkSignal) {
        this.networkSignal = networkSignal;
    }

    public String getMobileCarrier() {
        return mobileCarrier;
    }

    public void setMobileCarrier(String mobileCarrier) {
        this.mobileCarrier = mobileCarrier;
    }

    public Long getTopTime() {
        return topTime;
    }

    public void setTopTime(Long topTime) {
        this.topTime = topTime;
    }

    public Boolean getDateTimeStyle() {
        return dateTimeStyle;
    }

    public void setDateTimeStyle(Boolean dateTimeStyle) {
        this.dateTimeStyle = dateTimeStyle;
    }

    public Boolean getLocation() {
        return isLocation;
    }

    public void setLocation(Boolean location) {
        isLocation = location;
    }

    public Boolean getBlueTeeth() {
        return isBlueTeeth;
    }

    public void setBlueTeeth(Boolean blueTeeth) {
        isBlueTeeth = blueTeeth;
    }

    public Boolean getBatteryNum() {
        return isBatteryNum;
    }

    public void setBatteryNum(Boolean batteryNum) {
        isBatteryNum = batteryNum;
    }

    public Boolean getBatteryAdd() {
        return isBatteryAdd;
    }

    public void setBatteryAdd(Boolean batteryAdd) {
        isBatteryAdd = batteryAdd;
    }

    public Boolean getDir() {
        return isDir;
    }

    public void setDir(Boolean dir) {
        isDir = dir;
    }

    public Integer getBatteryNumBar() {
        return batteryNumBar;
    }

    public void setBatteryNumBar(Integer batteryNumBar) {
        this.batteryNumBar = batteryNumBar;
    }

    public Integer getToolbarType() {
        return toolbarType;
    }

    public void setToolbarType(Integer toolbarType) {
        this.toolbarType = toolbarType;
    }
}
