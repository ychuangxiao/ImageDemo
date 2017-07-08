package com.banditcat.app.mvp.views;

/**
 * 文件名称：{@link AutoLoginHandleView}
 * <br/>
 * 功能描述：用户自动登录视图
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
public interface AutoLoginHandleView extends BaseView {

    /**
     * 自动登录成功，跳转至首页
     */
    void navigateToHome();

    /**
     * 自动登录失败，跳转至登录
     */
    void navigateToLogin();
}
