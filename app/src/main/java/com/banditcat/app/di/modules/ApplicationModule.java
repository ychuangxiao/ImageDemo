package com.banditcat.app.di.modules;


import com.banditcat.app.AndroidApplication;
import com.banditcat.app.BuildConfig;
import com.banditcat.data.entitys.base.AppEntity;
import com.banditcat.data.repository.BaseRepository;
import com.banditcat.data.rest.Endpoint;
import com.banditcat.data.rest.RestDataSource;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 文件名称：{@link ApplicationModule}
 * <br/>
 * 功能描述：Dagger module that provides objects which will live during the application lifecycle.
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/1 14:52
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：16/6/1 14:52
 * <br/>
 * 修改备注：
 */
@Module
public class ApplicationModule {
    private final AndroidApplication mAndroidApplication;

    public ApplicationModule(AndroidApplication androidApplication) {
        mAndroidApplication = androidApplication;

    }

    @Provides
    @Singleton
    AppEntity provideAppEntity() {

        return new AppEntity(this.mAndroidApplication, BuildConfig.VERSION_CODE, "android");
    }

    @Provides
    @Singleton
    AndroidApplication provideAndroidApplicationContext() {
        return mAndroidApplication;
    }


    @Provides
    @Singleton
    Endpoint provideEndpoint() {
        return new Endpoint(BuildConfig.API_BASE_URL);
    }


    @Provides
    @Singleton
    @Named("executor_thread")
    Scheduler provideExecutorThread() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    @Named("ui_thread")
    Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    BaseRepository provideBaseRepository(RestDataSource restDataSource) {
        return restDataSource;
    }

}
