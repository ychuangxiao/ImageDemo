package com.sb.app.mvp.presenters;


import com.sb.app.AndroidApplication;
import com.sb.app.di.PerActivity;
import com.sb.app.mvp.presenters.base.Presenter;
import com.sb.app.mvp.views.BaseView;
import com.sb.app.utils.ThrowableUtils;
import com.sb.data.entitys.base.BaseRespEntity;
import com.sb.data.entitys.rep.base.BaseReqEntity;
import com.sb.domain.interactor.CheckUpdateUseCase;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 文件名称：IBaseListView
 * <p/>
 * 功能描述：检查更新-提交中间者
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
public class CheckUpdatePresenter implements Presenter<BaseView> {
    private Disposable mDisposable;//订阅

    private CheckUpdateUseCase mCheckUpdateUseCase;//检查更新-提交用例
    private AndroidApplication mAndroidApplication;

    BaseView mBaseView;

    /**
     * 用户登录构造函数
     *
     * @param androidApplication android main application
     */
    @Inject
    public CheckUpdatePresenter(CheckUpdateUseCase checkUpdateUseCase, AndroidApplication androidApplication) {
        this.mCheckUpdateUseCase = checkUpdateUseCase;
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
    public void attachView(BaseView baseView) {

        mBaseView = baseView;
    }

    /**
     * 入库详情搜索
     *
     */
    public void check() {

        BaseReqEntity baseReqEntity = new BaseReqEntity();


        baseReqEntity.setUrl(mAndroidApplication.sharedpreferences.ApkAddress().get());
        mCheckUpdateUseCase.setBaseReqEntity(baseReqEntity);
        mDisposable = mCheckUpdateUseCase.execute().subscribe(new Consumer<BaseRespEntity>() {
            @Override
            public void accept(BaseRespEntity baseResEntity) throws Exception {

            }
        }, new ThrowableUtils(mBaseView,mAndroidApplication));

    }


}
