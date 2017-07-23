package com.sb.domain.interactor;


import com.sb.data.entitys.base.BaseRespEntity;
import com.sb.data.entitys.rep.base.BaseReqEntity;
import com.sb.data.repository.BaseRepository;
import com.sb.domain.utils.RetrofitVoidUtils;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * 文件名称：{@link CheckUpdateUseCase}
 * <br/>
 * 功能描述：检查更新
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/5 18:48
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/5 18:48
 * <br/>
 * 修改备注：
 */
public class CheckUpdateUseCase extends BaseCase<BaseRespEntity> {

    private final BaseRepository mBaseRepository;//用户仓储
    private final Scheduler mUiThread;//UI线程
    private final Scheduler mExecutorThread;//执行线程


    BaseReqEntity mBaseReqEntity;

    public void setBaseReqEntity(BaseReqEntity baseReqEntity) {
        mBaseReqEntity = baseReqEntity;
    }

    @Inject
    public CheckUpdateUseCase(BaseRepository baseRepository, @Named("ui_thread") Scheduler uiThread, @Named("executor_thread") Scheduler executorThread) {
        mBaseRepository = baseRepository;

        mUiThread = uiThread;
        mExecutorThread = executorThread;

    }




    @Override
    public Observable<BaseRespEntity> buildObservable() {

        return mBaseRepository.checkUpdate(mBaseReqEntity).observeOn(mUiThread).subscribeOn(mExecutorThread).flatMap(new RetrofitVoidUtils());
    }
}
