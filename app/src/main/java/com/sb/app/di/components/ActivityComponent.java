package com.sb.app.di.components;


import android.support.v7.app.AppCompatActivity;

import com.sb.app.di.PerActivity;
import com.sb.app.di.modules.ActivityModule;

import dagger.Component;

/**
 * 文件名称：{@link ActivityComponent}
 * <br/>
 * 功能描述： 活动组件
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/5 12:37
 * <br/>
 * 修改作者：administrator
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
