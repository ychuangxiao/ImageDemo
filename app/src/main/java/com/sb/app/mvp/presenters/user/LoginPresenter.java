package com.sb.app.mvp.presenters.user;


import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.AndroidApplication;
import com.sb.app.di.PerActivity;
import com.sb.app.mvp.presenters.base.Presenter;
import com.sb.app.mvp.views.LoginView;
import com.sb.app.utils.ThrowableUtils;
import com.sb.data.entitys.rep.DeviceEntity;
import com.sb.data.entitys.rep.LogonEntity;
import com.sb.data.entitys.resp.LoginResEntity;
import com.sb.domain.interactor.UserLoginUseCase;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * 文件名称：IBaseListView
 * <p/>
 * 功能描述：用户登录中间者
 * <p/>
 * 创建作者：administrator
 * <p/>
 * 创建时间：15/11/17 下午3:05
 * <p/>
 * 修改作者：administrator
 * <p/>
 * 修改时间：15/11/17 下午3:05
 * <p/>
 * 修改备注：
 */
@PerActivity
public class LoginPresenter implements Presenter<LoginView> {

    private UserLoginUseCase mUserLoginUseCase;//登录用例
    private AndroidApplication mAndroidApplication;
    private LoginView mLoginView;//用户登录视图
    private String userName;//登录名
    private String password;//密码或者短信验证码
    private String account;//账套号
    private String imei;//唯一码

    /**
     * 用户登录构造函数
     *
     * @param userLoginUseCase   用户登录用例
     * @param androidApplication android main application
     */
    @Inject
    public LoginPresenter(UserLoginUseCase userLoginUseCase, AndroidApplication androidApplication) {
        this.mUserLoginUseCase = userLoginUseCase;
        mAndroidApplication = androidApplication;
    }


    public void initLoginInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;

    }

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    @Override
    public void resume() {

    }

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    @Override
    public void pause() {

    }

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    @Override
    public void destroy() {

    }

    @Override
    public void attachView(LoginView baseView) {

        this.mLoginView = baseView;
    }

    /**
     * 用户登录
     */
    public void login() {
        this.mLoginView.showLoading();


        DeviceEntity deviceEntity = new DeviceEntity();


        deviceEntity.setImei(imei);
        deviceEntity.setModel(android.os.Build.MODEL);
        deviceEntity.setOsVer(android.os.Build.VERSION.RELEASE);
        LogonEntity logonEntity = new LogonEntity(userName, password);
        logonEntity.setUrl(mAndroidApplication.sharedpreferences.ApkAddress().get());

        mUserLoginUseCase.setLogonEntity(logonEntity);


        mUserLoginUseCase.execute().subscribe(new Consumer<LoginResEntity>() {
            @Override
            public void accept(LoginResEntity loginResEntity) throws Exception {
                mAndroidApplication.sharedpreferences.Authorization().put(loginResEntity.getAuthCode());//设置授权码
                mAndroidApplication.sharedpreferences.UserId().put(loginResEntity.getUserCode());//用户代码
                mAndroidApplication.sharedpreferences.Watermark().put((StringUtils.isNotEmpty(loginResEntity
                        .getIsactive()) && loginResEntity.getIsactive().compareTo("Y") == 0) ? false : true);
                mLoginView.hideLoading();
                mLoginView.navigateToHome();
            }
        }, new ThrowableUtils(mLoginView, mAndroidApplication));


    }


}
