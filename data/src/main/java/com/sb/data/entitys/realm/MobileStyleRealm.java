package com.sb.data.entitys.realm;

import com.sb.data.constant.TextConstant;

import io.realm.RealmObject;

/**
 * Created by Administrator on 2017/8/4.
 */

public class MobileStyleRealm extends RealmObject {


    private Integer topStatusColor = 0;//状态栏颜色
    private Integer topToolStyle = TextConstant.TOOL_STYLE_CUSTOMER;//工具栏样式 默认自定义
    private Integer mobileVersion = TextConstant.MOBILE_VERSION_ANDROID_4;//手机类型
    private Integer networkType = TextConstant.NETWORK_TYPE_WIFI;//网络类型
    private Integer networkSignal = TextConstant.NETWORK_SIGNAL_5;//信号强度
    private String mobileCarrier = "中国移动";//手机运营商
    private Long topTime = System.currentTimeMillis();//顶部时间
    private Boolean date24TimeStyle = false;//是否24小时
    private Boolean isLocation = false;//是否显示定位
    private Boolean isBlueTeeth = false;//是否显示蓝牙
    private Boolean isBatteryNum = false;//是否显示百分比
    private Boolean isBatteryAdd = false;//是否充电中
    private Boolean isDir = false;//是否旋转
    private Integer batteryNumBar = 80;


    public int getTopStatusColor() {
        return topStatusColor;
    }

    public void setTopStatusColor(int topStatusColor) {
        this.topStatusColor = topStatusColor;
    }

    public int getTopToolStyle() {
        return topToolStyle;
    }

    public void setTopToolStyle(int topToolStyle) {
        this.topToolStyle = topToolStyle;
    }

    public int getMobileVersion() {
        return mobileVersion;
    }

    public void setMobileVersion(int mobileVersion) {
        this.mobileVersion = mobileVersion;
    }

    public int getNetworkType() {
        return networkType;
    }

    public void setNetworkType(int networkType) {
        this.networkType = networkType;
    }

    public int getNetworkSignal() {
        return networkSignal;
    }

    public void setNetworkSignal(int networkSignal) {
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

    public Boolean getDate24TimeStyle() {
        return date24TimeStyle;
    }

    public void setDate24TimeStyle(Boolean date24TimeStyle) {
        this.date24TimeStyle = date24TimeStyle;
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

    public int getBatteryNumBar() {
        return batteryNumBar;
    }

    public void setBatteryNumBar(int batteryNumBar) {
        this.batteryNumBar = batteryNumBar;
    }
}
