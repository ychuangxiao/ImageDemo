package com.banditcat.app.mvp.views;

import android.content.Context;


/**
 * 文件名称：{@link BaseView}
 * <br/>
 * 功能描述：基类视图
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
public interface BaseView {

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    void showLoading();

    /**
     * Hide a loading view.
     */
    void hideLoading();

    /**
     * Show a retry view in case of an error when retrieving data.
     */
    void showRetry();

    /**
     * Hide a retry view shown if there was an error when retrieving data.
     */
    void hideRetry();

    /**
     * Show an error message
     *
     * @param message A string representing an error.
     */
    void showError(String message);

    /**
     * 导航至用户登录
     */
    void navigateLogin();

    /**
     * Get a {@link Context}.
     */
    Context context();
}
