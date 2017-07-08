package com.banditcat.domain.interactor;


import com.banditcat.data.entitys.base.BaseRespEntity;
import com.banditcat.data.entitys.rep.LogoutReqEntity;
import com.banditcat.data.repository.BaseRepository;
import com.banditcat.domain.utils.RetrofitVoidUtils;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * 文件名称：{@link LogoutUseCase}
 * <br/>
 * 功能描述： 用户退出用例
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/5 18:48
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：16/6/5 18:48
 * <br/>
 * 修改备注：
 */
public class LogoutUseCase extends BaseCase<BaseRespEntity> {

    private final BaseRepository mBaseRepository;
    private final Scheduler mUiThread;
    private final Scheduler mExecutorThread;
    private LogoutReqEntity mLogoutReqEntity;

    public void setLogoutReqEntity(LogoutReqEntity logoutReqEntity) {
        mLogoutReqEntity = logoutReqEntity;
    }

    @Inject
    public LogoutUseCase(BaseRepository baseRepository, @Named("ui_thread") Scheduler uiThread, @Named("executor_thread") Scheduler executorThread) {
        mBaseRepository = baseRepository;

        mUiThread = uiThread;
        mExecutorThread = executorThread;

    }


    @Override
    public Observable<BaseRespEntity> buildObservable() {

        return mBaseRepository.logout(mLogoutReqEntity).observeOn(mUiThread).subscribeOn(mExecutorThread).flatMap(new RetrofitVoidUtils());
    }


}
