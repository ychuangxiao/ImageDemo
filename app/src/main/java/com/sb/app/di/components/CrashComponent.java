
package com.sb.app.di.components;


import com.sb.app.di.PerCrash;
import com.sb.app.di.modules.CrashHandlerModule;
import com.sb.app.utils.CrashHandler;

import dagger.Component;

/**
 * 文件名称：{@link CrashComponent}
 * <br/>
 * 功能描述：崩溃日志组件
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
@PerCrash
@Component(dependencies = ApplicationComponent.class
        , modules = {CrashHandlerModule.class})
public interface CrashComponent {

    void inject(CrashHandler crashHandler);//崩溃日志


}
