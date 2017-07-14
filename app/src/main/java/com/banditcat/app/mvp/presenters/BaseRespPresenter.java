package com.banditcat.app.mvp.presenters;


import com.banditcat.app.AndroidApplication;
import com.banditcat.app.di.PerActivity;
import com.banditcat.app.model.RegModel;
import com.banditcat.app.model.RegModelMapper;
import com.banditcat.app.mvp.presenters.base.Presenter;
import com.banditcat.app.mvp.views.BaseHandleView;
import com.banditcat.app.utils.ThrowableUtils;
import com.banditcat.data.constant.TextConstant;
import com.banditcat.data.entitys.base.BaseRespEntity;
import com.banditcat.data.entitys.rep.LogoutReqEntity;
import com.banditcat.domain.interactor.BaseRespUseCase;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 文件名称：{@link BaseRespPresenter}
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
public class BaseRespPresenter implements Presenter<BaseHandleView> {

    private BaseRespUseCase mBaseRespUseCase;//统一处理用例
    private AndroidApplication mAndroidApplication;
    private BaseHandleView mBaseView;//统一处理视图
    private Disposable mDisposable;//订阅

    /**
     * 用户登录构造函数
     *
     * @param baseRespUseCase    统一相应用例
     * @param androidApplication android main application
     */
    @Inject
    public BaseRespPresenter(BaseRespUseCase baseRespUseCase, AndroidApplication androidApplication) {
        this.mBaseRespUseCase = baseRespUseCase;
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

        logoutReqEntity.setAction(TextConstant.ACTION_LOGOUT);
        logoutReqEntity.setUrl(mAndroidApplication.sharedpreferences.ApkAddress().get());
        logoutReqEntity.setUserId(mAndroidApplication.sharedpreferences.UserId().get());
        logoutReqEntity.setAuthorization(mAndroidApplication.sharedpreferences.Authorization().get());

        mBaseRespUseCase.setLogoutReqEntity(logoutReqEntity);

        mDisposable = mBaseRespUseCase.execute().subscribe(new Consumer<BaseRespEntity>() {
            @Override
            public void accept(BaseRespEntity baseResEntity) throws Exception {


                mAndroidApplication.clearShared();
                mBaseView.hideLoading();
                mBaseView.noticeSuccess();


            }
        }, new ThrowableUtils(mBaseView, mAndroidApplication));

    }


    /**
     * 用户注册
     *
     * @param regModel
     */
    public void register(RegModel regModel) {

        this.mBaseView.showLoading();

        regModel.setAction(TextConstant.ACTION_REG);


        mBaseRespUseCase.setRegReqEntity(RegModelMapper.getInstance().transformer(regModel));


        mDisposable = mBaseRespUseCase.execute().subscribe(new Consumer<BaseRespEntity>() {
            @Override
            public void accept(BaseRespEntity baseResEntity) throws Exception {
                mBaseView.hideLoading();
                mBaseView.noticeSuccess();
            }
        }, new ThrowableUtils(mBaseView, mAndroidApplication));
    }
}
