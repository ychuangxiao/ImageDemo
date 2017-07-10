package com.banditcat.app.di.components;


import com.banditcat.app.di.PerActivity;
import com.banditcat.app.di.modules.ActivityModule;
import com.banditcat.app.di.modules.UserModule;

import dagger.Component;

/**
 * 文件名称：{@link ActivityComponent}
 * <br/>
 * 功能描述：用户登录组件
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
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, UserModule.class})
public interface LoginComponent extends ActivityComponent {




}