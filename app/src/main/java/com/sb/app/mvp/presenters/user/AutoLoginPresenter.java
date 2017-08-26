package com.sb.app.mvp.presenters.user;


import com.sb.app.AndroidApplication;
import com.sb.app.di.PerActivity;
import com.sb.app.mvp.presenters.base.Presenter;
import com.sb.app.mvp.views.AutoLoginHandleView;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.UserRealm;
import com.sb.domain.interactor.AutoLoginUseCase;
import com.sb.common.utils.StringUtils;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 文件名称：IBaseListView
 * <p/>
 * 功能描述：用户自动登录中间者
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
public class AutoLoginPresenter implements Presenter<AutoLoginHandleView> {

    private AutoLoginUseCase mAutoLoginUseCase;//登录自动登录用例
    private AndroidApplication mAndroidApplication;
    private AutoLoginHandleView mAutoLoginHandleView;//用户自动登录视图
    private Disposable mDisposable;//订阅

    /**
     * 用户登录构造函数
     *
     * @param autoLoginUseCase   用户登录用例
     * @param androidApplication android main application
     */
    @Inject
    public AutoLoginPresenter(AutoLoginUseCase autoLoginUseCase, AndroidApplication androidApplication) {
        this.mAutoLoginUseCase = autoLoginUseCase;
        mAndroidApplication = androidApplication;
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

        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void attachView(AutoLoginHandleView baseView) {

        this.mAutoLoginHandleView = baseView;
    }

    /**
     * 用户自动登录登录
     */
    public void autoLogin() {
        this.mAutoLoginHandleView.showLoading();

        mDisposable = mAutoLoginUseCase.execute().subscribe(new Consumer<UserRealm>() {
            @Override
            public void accept(UserRealm userRealm) throws Exception {
                mAutoLoginHandleView.hideLoading();
                if (userRealm != null && StringUtils.isNotEmpty(userRealm.getPassword())) {
                    mAndroidApplication.sharedpreferences.Authorization().put(userRealm.getPassword());//设置授权码
                    mAndroidApplication.sharedpreferences.UserId().put(userRealm.getUserId());//设置司机编号
                    mAndroidApplication.sharedpreferences.Watermark().put(!(userRealm.getActive() == TextConstant
                            .APP_ACTIVE_20));//是否需要水印  == 20 就是不需要

                    mAutoLoginHandleView.navigateToHome();
                } else {
                    mAutoLoginHandleView.navigateToLogin();
                }

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mAutoLoginHandleView.hideLoading();
                mAutoLoginHandleView.navigateToLogin();
            }
        });

    }


}
