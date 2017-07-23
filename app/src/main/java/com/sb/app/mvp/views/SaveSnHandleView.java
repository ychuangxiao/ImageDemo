package com.sb.app.mvp.views;

/**
 * 文件名称：{@link SaveSnHandleView}
 * <br/>
 * 功能描述：操作SN保存视图
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/18 13:52
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/18 13:52
 * <br/>
 * 修改备注：
 */
public interface SaveSnHandleView<T> extends BaseView {
    /**
     * 成功调用通知
     */
    void noticeOverSuccess(T t);

    void noticeSuccess(T t);
}
