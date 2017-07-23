package com.sb.app.mvp.views;


/**
 * 文件名称：{@link BaseListView}
 * <br/>
 * 功能描述： 列表集合视图基类接口
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/5 12:39
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/5 12:39
 * <br/>
 * 修改备注：
 */
public interface BaseListView<T> extends BaseView {

    void renderList(T data);
}
