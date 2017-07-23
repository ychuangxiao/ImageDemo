package com.sb.app.di.modules;


import com.sb.app.di.PerCrash;
import com.sb.data.repository.BaseRepository;
import com.sb.domain.interactor.CrashHandlerUseCase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * 文件名称：{@link CrashHandlerModule}
 * <br/>
 * 功能描述：崩溃信息收集
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/17 17:45
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/17 17:45
 * <br/>
 * 修改备注：
 */
@Module
public class CrashHandlerModule {

    @Provides
    @PerCrash
    CrashHandlerUseCase provideCrashHandlerUseCase(
            BaseRepository repository,
            @Named("ui_thread") Scheduler uiThread,
            @Named("executor_thread") Scheduler executorThread) {

        return new CrashHandlerUseCase(repository, uiThread
                , executorThread);
    }

}
