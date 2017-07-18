package com.banditcat.app.mvp.presenters.user;


import com.banditcat.app.AndroidApplication;
import com.banditcat.app.di.PerActivity;
import com.banditcat.app.mvp.presenters.base.Presenter;
import com.banditcat.app.mvp.views.AutoLoginHandleView;
import com.banditcat.data.entitys.realm.UserRealm;
import com.banditcat.domain.interactor.AutoLoginUseCase;
import com.ilogie.android.library.common.util.StringUtils;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 文件名称：IBaseListView
 * <p/>
 * 功能描述：用户自动登录中间者
 * <p/>
 * 创建作者：banditcat
 * <p/>
 * 创建时间：15/11/17 下午3:05
 * <p/>
 * 修改作者：banditcat
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
                if (userRealm != null && StringUtils.isNotEmpty(userRealm.getUserUid())) {
                    mAndroidApplication.sharedpreferences.Authorization().put(userRealm.getPassword());//设置授权码
                    mAndroidApplication.sharedpreferences.UserId().put(userRealm.getUserId());//设置司机编号
                    mAndroidApplication.sharedpreferences.Watermark().put(userRealm.getActive() == 20L);

                    mAutoLoginHandleView.navigateToHome();
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
