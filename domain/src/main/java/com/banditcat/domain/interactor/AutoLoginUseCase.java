package com.banditcat.domain.interactor;


import com.banditcat.data.entitys.realm.UserRealm;
import com.banditcat.data.repository.BaseRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * 文件名称：{@link AutoLoginUseCase}
 * <br/>
 * 功能描述： 用户自动登录登录用例
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
public class AutoLoginUseCase extends BaseCase<UserRealm> {

    private final BaseRepository mBaseRepository;
    private final Scheduler mUiThread;
    private final Scheduler mExecutorThread;

    @Inject
    public AutoLoginUseCase(BaseRepository baseRepository, @Named("ui_thread") Scheduler uiThread, @Named("executor_thread") Scheduler
            executorThread) {
        mBaseRepository = baseRepository;

        mUiThread = uiThread;
        mExecutorThread = executorThread;

    }


    @Override
    public Observable<UserRealm> buildObservable() {
        return mBaseRepository.autoLogin();
    }
}
