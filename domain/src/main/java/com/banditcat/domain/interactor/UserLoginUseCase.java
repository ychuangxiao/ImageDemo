package com.banditcat.domain.interactor;


import com.banditcat.data.entitys.rep.LogonEntity;
import com.banditcat.data.entitys.resp.LoginResEntity;
import com.banditcat.data.repository.BaseRepository;
import com.banditcat.domain.utils.RetrofitUtils;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * 文件名称：{@link UserLoginUseCase}
 * <br/>
 * 功能描述： 用户登录用例
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
public class UserLoginUseCase extends BaseCase<LoginResEntity> {

    private final BaseRepository mBaseRepository;
    private final Scheduler mUiThread;
    private final Scheduler mExecutorThread;
    private LogonEntity mLogonEntity;

    @Inject
    public UserLoginUseCase(
            BaseRepository baseRepository, @Named("ui_thread") Scheduler uiThread
            , @Named("executor_thread") Scheduler executorThread
    ) {
        mBaseRepository = baseRepository;

        mUiThread = uiThread;
        mExecutorThread = executorThread;

    }

    public void setLogonEntity(LogonEntity logonEntity) {
        mLogonEntity = logonEntity;
    }

    @Override
    public Observable<LoginResEntity> buildObservable() {

        return mBaseRepository.login(mLogonEntity)
                .observeOn(mUiThread)
                .subscribeOn(mExecutorThread)
                .flatMap(new RetrofitUtils<LoginResEntity>());
    }


}
