package com.sb.app.di.components;


import com.sb.app.AndroidApplication;
import com.sb.app.di.modules.ApplicationModule;
import com.sb.app.views.base.BaseActivity;
import com.sb.data.repository.BaseRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import io.reactivex.Scheduler;

/**
 * 文件名称：{@link ActivityComponent}
 * <br/>
 * 功能描述：A component whose lifetime is the life of the application.
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
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);

    //Exposed to sub-graphs.
    AndroidApplication context();

    @Named("ui_thread")
    Scheduler uiThread();

    @Named("executor_thread")
    Scheduler executorThread();

    BaseRepository baseRepository();

}
