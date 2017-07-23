 package com.sb.app.di.modules;


 import android.support.v7.app.AppCompatActivity;

 import com.sb.app.di.PerActivity;

 import dagger.Module;
 import dagger.Provides;

/**
 * 文件名称：{@link ActivityModule}
 * <br/>
 * 功能描述：A module to wrap the Activity state and expose it to the graph.
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
public class ActivityModule {
    private final AppCompatActivity mAppCompatActivity;

    public ActivityModule(AppCompatActivity appCompatActivity) {
        mAppCompatActivity = appCompatActivity;

    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerActivity
    AppCompatActivity activity() {
        return this.mAppCompatActivity;
    }
}
