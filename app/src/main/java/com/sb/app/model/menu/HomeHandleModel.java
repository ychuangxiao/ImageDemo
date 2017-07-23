package com.sb.app.model.menu;


import com.sb.common.fontawesom.typeface.IIcon;

/**
 * 文件名称：{@link HomeHandleModel}
 * <br/>
 * 功能描述：首页操作菜单视图模型
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：1/9/17 14:05
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：1/9/17 14:05
 * <br/>
 * 修改备注：
 */
public class HomeHandleModel {

    private String handleText;//操作文本
    private int color;//图标颜色
    private IIcon icon;//字体图标

    private Integer handleIndex;//操作索引

    private Boolean enable=false;//是否启用


    public String getHandleText() {
        return handleText;
    }

    public void setHandleText(String handleText) {
        this.handleText = handleText;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public IIcon getIcon() {
        return icon;
    }

    public void setIcon(IIcon icon) {
        this.icon = icon;
    }

    public Integer getHandleIndex() {
        return handleIndex;
    }

    public void setHandleIndex(Integer handleIndex) {
        this.handleIndex = handleIndex;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public HomeHandleModel(String handleText, int color, IIcon icon, Integer handleIndex, Boolean enable) {
        this.handleText = handleText;
        this.color = color;
        this.icon = icon;
        this.handleIndex = handleIndex;
        this.enable = enable;
    }
}
