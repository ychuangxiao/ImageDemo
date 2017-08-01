package com.sb.app.utils;


import android.content.Context;

import com.sb.app.AndroidApplication;
import com.sb.app.di.PerCrash;
import com.sb.app.di.components.DaggerCrashComponent;
import com.sb.app.model.CrashModel;
import com.sb.app.mvp.presenters.CrashPresenter;
import com.sb.app.mvp.views.BaseHandleView;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.inject.Inject;

/**
 * 文件名称：{@link CrashHandler}
 * <br/>
 * 功能描述：异常收集
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/20 14:19
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/20 14:19
 * <br/>
 * 修改备注：
 */
@PerCrash
public class CrashHandler implements Thread.UncaughtExceptionHandler, BaseHandleView {


    private AndroidApplication mContext;

    private CrashHandler(AndroidApplication context) {
        mContext = context;

    }


    public static CrashHandler getInstance(AndroidApplication context) {
        return new CrashHandler(context);
    }


    public static final String TAG = "CrashHandler";


    @Inject
    CrashPresenter mCrashPresenter;//崩溃日志中间者

    @Inject
    AndroidApplication mAndroidApplication;


    /**
     * 系统默认的UncaughtException处理类
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    /**
     * CrashHandler实例
     */
    private static CrashHandler INSTANCE;

    private static final String APP_DEVICE_FORMAT = "Model:%s,Version:,FingerPrint:%s";


    /**
     * 初始化,注册Context对象,
     * 获取系统默认的UncaughtException处理器,
     * 设置该CrashHandler为程序的默认处理器
     */
    public void init() {


        DaggerCrashComponent.builder().applicationComponent((mContext).getApplicationComponent()).build().inject(this);
        mCrashPresenter.attachView(this);
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (null != ex ) {

            final Writer result = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(result);
            //获取跟踪的栈信息，除了系统栈信息，还把手机型号、系统版本、编译版本的唯一标示
            StackTraceElement[] trace = ex.getStackTrace();
            StackTraceElement[] trace2 = new StackTraceElement[trace.length + 3];
            System.arraycopy(trace, 0, trace2, 0, trace.length);
            trace2[trace.length + 0] = new StackTraceElement("Android", "MODEL", android.os.Build.MODEL, -1);
            trace2[trace.length + 1] = new StackTraceElement("Android", "VERSION", android.os.Build.VERSION.RELEASE, -1);
            trace2[trace.length + 2] = new StackTraceElement("Android", "FINGERPRINT", android.os.Build.FINGERPRINT, -1);
            //追加信息，因为后面会回调默认的处理方法
            ex.setStackTrace(trace2);
            ex.printStackTrace(printWriter);
            //把上面获取的堆栈信息转为字符串，打印出来
            String stacktrace = result.toString();
            printWriter.close();


            LogUtils.e(TAG, stacktrace);

            try {
                mCrashPresenter.save(new CrashModel(android.os.Build.MODEL, android.os.Build.VERSION.RELEASE, stacktrace));
            } catch (Exception aa) {
                aa.printStackTrace();
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mAndroidApplication.exit();


        } else {
            mAndroidApplication.exit();
        }
    }


    @Override
    public void noticeSuccess() {
        mAndroidApplication.exit();
    }

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    @Override
    public void showLoading() {

    }

    /**
     * Hide a loading view.
     */
    @Override
    public void hideLoading() {

    }

    /**
     * Show a retry view in case of an error when retrieving data.
     */
    @Override
    public void showRetry() {

    }

    /**
     * Hide a retry view shown if there was an error when retrieving data.
     */
    @Override
    public void hideRetry() {

    }

    /**
     * Show an error message
     *
     * @param message A string representing an error.
     */
    @Override
    public void showError(String message) {

    }

    /**
     * 导航至用户登录
     */
    @Override
    public void navigateLogin() {

    }

    /**
     * Get a {@link Context}.
     */
    @Override
    public Context context() {
        return mContext;
    }


}
