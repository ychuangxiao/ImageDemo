package com.sb.app;

import android.app.Activity;
import android.app.Application;

import com.ilogie.android.library.common.util.ArrayUtils;
import com.ilogie.android.library.common.util.LogUtils;
import com.sb.app.base.Migration;
import com.sb.app.di.components.ApplicationComponent;
import com.sb.app.di.components.DaggerApplicationComponent;
import com.sb.app.di.modules.ApplicationModule;
import com.sb.app.utils.CrashHandler;
import com.sb.app.utils.SharedPreferencesUtils;

import java.util.HashSet;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * 文件名称：{@link AndroidApplication}
 * <br/>
 * 功能描述：Main Application
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：2017/4/18 16:02
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：2017/4/18 16:02
 * <br/>
 * 修改备注：
 */
public class AndroidApplication extends Application {

    private ApplicationComponent mApplicationComponent;//应用程序组件
    public SharedPreferencesUtils sharedpreferences;//共享参数工具
    private HashSet<Activity> activities;//活动集合
    private RealmConfiguration realmConfiguration;//数据库配置


    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(this);
        this.init();
        this.initRealm();

    }

    /**
     * 初始化注释器
     */
    private void initializeInjector() {

        this.mApplicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    /**
     * 初始化基本信息
     */
    private void init() {
        sharedpreferences = new SharedPreferencesUtils(this);
        activities = new HashSet<>();
        CrashHandler.getInstance(this).init();
    }

    /**
     * 获得应用程序组件
     *
     * @return
     */
    public ApplicationComponent getApplicationComponent() {
        return this.mApplicationComponent;
    }


    /**
     * 初始化数据库配置信息
     */
    private void initRealm() {
        //Realm数据配置相关
        realmConfiguration = new RealmConfiguration.Builder().name(getString(R.string.name_database)).schemaVersion(2).migration(new Migration())
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);// Make this Realm the default
    }

    /**
     * exit app
     */
    public void exit() {
        finishAll();
        android.os.Process.killProcess(android.os.Process.myPid());
    }


    /**
     * 添加活动视图
     *
     * @param activity 具体活动视图
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * remove activity
     */
    public void finishAll() {

        if (ArrayUtils.isNotEmpty(activities)) {

            LogUtils.e("e", "当前共有" + String.valueOf(activities.size()));
            for (Activity activity : activities) {
                activity.finish();
            }
            activities.clear();

        }


    }

    public void clearShared()
    {
        if (sharedpreferences == null)
        {
            return;
        }

        sharedpreferences.clear();
    }

    /**
     * 用户登录
     */
    public void login() {

        finishAll();


    }
}
