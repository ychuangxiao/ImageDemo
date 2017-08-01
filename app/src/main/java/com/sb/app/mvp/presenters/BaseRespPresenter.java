package com.sb.app.mvp.presenters;


import com.sb.app.AndroidApplication;
import com.sb.app.di.PerActivity;
import com.sb.app.model.RegModel;
import com.sb.app.model.RegModelMapper;
import com.sb.app.model.base.BaseReqModel;
import com.sb.app.model.base.BaseReqModelMapper;
import com.sb.app.mvp.presenters.base.Presenter;
import com.sb.app.mvp.views.BaseHandleView;
import com.sb.app.utils.ThrowableUtils;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.base.BaseRespEntity;
import com.sb.data.entitys.rep.LogoutReqEntity;
import com.sb.domain.interactor.BaseRespUseCase;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 文件名称：{@link BaseRespPresenter}
 * <br/>
 * 功能描述：退出中间者
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：2017/4/28 13:29
 * <br/>
 * 修改作者：administrator
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


    public void checkVer() {

        this.mBaseView.showLoading();
        BaseReqModel baseReqModel = new BaseReqModel();
        baseReqModel.setAction(TextConstant.ACTION_CHECKVER);
        baseReqModel.setAuthorization(mAndroidApplication.sharedpreferences.Authorization().get());
        mBaseRespUseCase.setBaseReqEntity(BaseReqModelMapper.getInstance().transformer(baseReqModel));
        mDisposable = mBaseRespUseCase.execute().subscribe(new Consumer<BaseRespEntity>() {
            @Override
            public void accept(BaseRespEntity baseResEntity) throws Exception {
                mBaseView.hideLoading();

            }
        }, new ThrowableUtils(mBaseView, mAndroidApplication));
    }


    public void checkToken() {

        mAndroidApplication.sharedpreferences.Watermark().put(true);
        this.mBaseView.showLoading();
        BaseReqModel baseReqModel = new BaseReqModel();
        baseReqModel.setAction(TextConstant.ACTION_CHECKTOKEN);
        baseReqModel.setAuthorization(mAndroidApplication.sharedpreferences.Authorization().get());
        mBaseRespUseCase.setBaseReqEntity(BaseReqModelMapper.getInstance().transformer(baseReqModel));
        mDisposable = mBaseRespUseCase.execute().subscribe(new Consumer<BaseRespEntity>() {
            @Override
            public void accept(BaseRespEntity baseResEntity) throws Exception {

                mAndroidApplication.sharedpreferences.Watermark().put(false);
                mBaseView.hideLoading();
                mBaseView.noticeSuccess();
            }
        }, new ThrowableUtils(mBaseView, mAndroidApplication));
    }
}
