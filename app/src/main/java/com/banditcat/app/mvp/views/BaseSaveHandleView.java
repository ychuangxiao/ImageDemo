package com.banditcat.app.mvp.views;

/**
 * 文件名称：{@link BaseSaveHandleView}
 * <br/>
 * 功能描述：操作视图
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/18 13:52
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：16/6/18 13:52
 * <br/>
 * 修改备注：
 */
public interface BaseSaveHandleView<T> extends BaseView {


    /**
     * 通知提交成功
     *
     * @param model
     */
    void noticeSuccess(T model);

}
