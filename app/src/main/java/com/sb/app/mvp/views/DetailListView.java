package com.sb.app.mvp.views;


/**
 * 文件名称：{@link DetailListView}
 * <br/>
 * 功能描述：详情集合视图
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
public interface DetailListView<T> extends BaseView {

    void renderList(T data);

    /**
     * 搜索异常
     *
     * @param errMsg 异常消息
     */
    void detailsError(String errMsg);
}
