package com.banditcat.app.mvp.views;


/**
 * 文件名称：{@link QueryPagerView}
 * <br/>
 * 功能描述： 查询带结果视图
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/5 12:39
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：16/6/5 12:39
 * <br/>
 * 修改备注：
 */
public interface QueryPagerView<T> extends BaseView {

    void renderQuery(T data);

    void noticeQueryError(String error);
}
