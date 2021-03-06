package com.sb.app.di.modules;


import com.sb.app.di.PerActivity;
import com.sb.data.repository.BaseRepository;
import com.sb.domain.interactor.AutoLoginUseCase;
import com.sb.domain.interactor.UserLoginUseCase;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * 文件名称：{@link BizModule}
 * <br/>
 * 功能描述：Dagger module that provides user related collaborators.
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/1 14:52
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/1 14:52
 * <br/>
 * 修改备注：
 */
@Module
public class BizModule {


    public BizModule() {
    }

    @Provides
    @PerActivity
    UserLoginUseCase provideUserLoginUseCase(BaseRepository repository, @Named("ui_thread") Scheduler uiThread, @Named("executor_thread") Scheduler
            executorThread) {

        return new UserLoginUseCase(repository, uiThread, executorThread);
    }


    @Provides
    @PerActivity
    AutoLoginUseCase provideAutoLoginUseCase(BaseRepository repository, @Named("ui_thread") Scheduler uiThread, @Named("executor_thread") Scheduler
            executorThread) {

        return new AutoLoginUseCase(repository, uiThread, executorThread);
    }
}