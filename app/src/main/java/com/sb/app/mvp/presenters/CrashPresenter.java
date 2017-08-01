package com.sb.app.mvp.presenters;


import com.sb.app.AndroidApplication;
import com.sb.app.di.PerCrash;
import com.sb.app.model.CrashModel;
import com.sb.app.model.CrashModelMapper;
import com.sb.app.mvp.presenters.base.Presenter;
import com.sb.app.mvp.views.BaseHandleView;
import com.sb.data.entitys.base.BaseRespEntity;
import com.sb.domain.interactor.CrashHandlerUseCase;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * 文件名称：{@link CrashPresenter}
 * <p/>
 * 功能描述：崩溃日志中间者
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
@PerCrash
public class CrashPresenter implements Presenter<BaseHandleView> {

    private CrashHandlerUseCase mCrashHandlerUseCase;//崩溃日志用例
    private AndroidApplication mAndroidApplication;
    private BaseHandleView mBaseHandleView;//操作视图


    /**
     * 用户登录构造函数
     *
     * @param crashHandlerUseCase 崩溃日志用例
     * @param androidApplication  android main application
     */
    @Inject
    public CrashPresenter(CrashHandlerUseCase crashHandlerUseCase, AndroidApplication androidApplication) {
        this.mCrashHandlerUseCase = crashHandlerUseCase;
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
        /*if (null != mSubscription && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }*/
    }

    @Override
    public void attachView(BaseHandleView baseView) {

        this.mBaseHandleView = baseView;
    }

    /**
     * 日志
     *
     * @param crashModel 崩溃日志视图模型
     */
    public void save(CrashModel crashModel) {


        crashModel.setUrl(mAndroidApplication.sharedpreferences.ApkAddress().get());

        mCrashHandlerUseCase.setBaseCrashReqEntity(CrashModelMapper.getInstance().transformer(crashModel));


        mCrashHandlerUseCase.execute().subscribe(new Consumer<BaseRespEntity>() {
            @Override
            public void accept(BaseRespEntity baseRespEntity) throws Exception {

                mBaseHandleView.noticeSuccess();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

                mBaseHandleView.noticeSuccess();
            }
        });


    }

}
