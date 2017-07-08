package com.banditcat.app.mvp.views;


/**
 * 文件名称：{@link PagerView}
 * <br/>
 * 功能描述： 分页视图
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
public interface PagerView<T> extends BaseView {

    void renderList(T data);

    void noticePagerError(Integer page, String error);
}
