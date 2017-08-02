package com.sb.app.di.components;


import com.sb.app.di.PerActivity;
import com.sb.app.di.modules.ActivityModule;
import com.sb.app.di.modules.BizModule;
import com.sb.app.views.activitys.DownApkActivity;
import com.sb.app.views.activitys.MainActivity;
import com.sb.app.views.activitys.tencent.WeChatMessageActivityFragment;
import com.sb.app.views.activitys.user.RegActivity;
import com.sb.app.views.fragment.BottomSheetUserFragment;
import com.sb.app.views.fragment.ContactFragment;
import com.sb.app.views.fragment.LoginFragment;
import com.sb.app.views.fragment.WeChatFragment;

import dagger.Component;

/**
 * 文件名称：{@link ActivityComponent}
 * <br/>
 * 功能描述：业务组件
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
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, BizModule.class})
public interface BizComponent extends ActivityComponent {

    void inject(LoginFragment fragment);//用户登录

    void inject(ContactFragment fragment);
    void inject(WeChatFragment fragment);
    void inject(BottomSheetUserFragment fragment);
    void inject(WeChatMessageActivityFragment fragment);

    void inject(RegActivity regActivity);

    void inject(MainActivity activity);

    void inject(DownApkActivity activity);
}
