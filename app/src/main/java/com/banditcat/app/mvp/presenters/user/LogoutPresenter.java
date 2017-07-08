package com.banditcat.app.mvp.presenters.user;


import com.banditcat.app.AndroidApplication;
import com.banditcat.app.di.PerActivity;
import com.banditcat.app.mvp.presenters.base.Presenter;
import com.banditcat.app.mvp.views.BaseHandleView;
import com.banditcat.app.utils.LoginUtils;
import com.banditcat.data.entitys.base.BaseRespEntity;
import com.banditcat.data.entitys.rep.LogoutReqEntity;
import com.banditcat.domain.interactor.LogoutUseCase;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 文件名称：{@link LogoutPresenter}
 * <br/>
 * 功能描述：退出中间者
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：2017/4/28 13:29
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：2017/4/28 13:29
 * <br/>
 * 修改备注：
 */
@PerActivity
public class LogoutPresenter implements Presenter<BaseHandleView> {

    private LogoutUseCase mLogoutUseCase;//退出用例
    private AndroidApplication mAndroidApplication;
    private BaseHandleView mBaseView;//用户自动登录视图
    private Disposable mDisposable;//订阅

    /**
     * 用户登录构造函数
     *
     * @param logoutUseCase      退出用例
     * @param androidApplication android main application
     */
    @Inject
    public LogoutPresenter(LogoutUseCase logoutUseCase, AndroidApplication androidApplication) {
        this.mLogoutUseCase = logoutUseCase;
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
    public void attachView(BaseHandleView baseView) {

        this.mBaseView = baseView;
    }

    /**
     * 退出
     */
    public void logout() {
        this.mBaseView.showLoading();

        LogoutReqEntity logoutReqEntity = new LogoutReqEntity();
        logoutReqEntity.setUrl(mAndroidApplication.sharedpreferences.ApkAddress().get());
        logoutReqEntity.setUserId(mAndroidApplication.sharedpreferences.UserId().get());
        logoutReqEntity.setAuthorization(mAndroidApplication.sharedpreferences.Authorization().get());

        mLogoutUseCase.setLogoutReqEntity(logoutReqEntity);

        mDisposable = mLogoutUseCase.execute().subscribe(new Consumer<BaseRespEntity>() {
            @Override
            public void accept(BaseRespEntity baseResEntity) throws Exception {
                mBaseView.hideLoading();
                mBaseView.noticeSuccess();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mBaseView.hideLoading();

                if (!LoginUtils.isLogin(throwable)) {
                    mBaseView.navigateLogin();
                    return;
                }

                if (LoginUtils.isUpdateApk(throwable, mBaseView.getContext(), mAndroidApplication)) {

                    return;
                }

                mBaseView.noticeSuccess();

            }
        });

    }


}
