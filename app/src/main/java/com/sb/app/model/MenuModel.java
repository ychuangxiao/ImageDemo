package com.sb.app.model;


import com.sb.common.fontawesom.typeface.IIcon;

/**
 * 文件名称：{@link MenuModel}
 * <br/>
 * 功能描述：菜单模型
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：2017/4/19 17:17
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：2017/4/19 17:17
 * <br/>
 * 修改备注：
 */
public class MenuModel {
    IIcon mIcon;
    String title;


    Integer id;

    IIcon mRightIcon;

    public MenuModel(IIcon icon, IIcon rightIcon, String title, Integer id) {
        mIcon = icon;
        this.title = title;
        this.id = id;
        this.mRightIcon = rightIcon;
    }

    public IIcon getIcon() {
        return mIcon;
    }

    public void setIcon(IIcon icon) {
        mIcon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public IIcon getRightIcon() {
        return mRightIcon;
    }

    public void setRightIcon(IIcon rightIcon) {
        mRightIcon = rightIcon;
    }
}
