package com.banditcat.app.di.components;


import android.support.v7.app.AppCompatActivity;

import com.banditcat.app.di.PerActivity;
import com.banditcat.app.di.modules.ActivityModule;

import dagger.Component;

/**
 * 文件名称：{@link ActivityComponent}
 * <br/>
 * 功能描述： 活动组件
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/5 12:37
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：16/6/5 12:37
 * <br/>
 * 修改备注：
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    //Exposed to sub-graphs.
    AppCompatActivity activity();


}
