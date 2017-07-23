package com.sb.domain.interactor;


import com.sb.data.entitys.base.BaseRespEntity;
import com.sb.data.entitys.rep.base.BaseCrashReqEntity;
import com.sb.data.repository.BaseRepository;
import com.sb.domain.utils.RetrofitVoidUtils;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * 文件名称：{@link CrashHandlerUseCase}
 * <br/>
 * 功能描述： 崩溃收集用例
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
public class CrashHandlerUseCase extends BaseCase<BaseRespEntity> {

    private final BaseRepository mBaseRepository;
    private final Scheduler mUiThread;
    private final Scheduler mExecutorThread;


    private BaseCrashReqEntity mBaseCrashReqEntity;//崩溃实体

    @Inject
    public CrashHandlerUseCase(BaseRepository userRepository, @Named("ui_thread") Scheduler uiThread, @Named("executor_thread") Scheduler
            executorThread) {
        mBaseRepository = userRepository;

        mUiThread = uiThread;
        mExecutorThread = executorThread;

    }


    public void setBaseCrashReqEntity(BaseCrashReqEntity baseCrashReqEntity) {
        mBaseCrashReqEntity = baseCrashReqEntity;
    }

    @Override
    public Observable<BaseRespEntity> buildObservable() {

        return mBaseRepository.saveCrashLog(mBaseCrashReqEntity)
                .observeOn(mUiThread)
                .subscribeOn(mExecutorThread)
                .flatMap(new RetrofitVoidUtils());

    }


}
