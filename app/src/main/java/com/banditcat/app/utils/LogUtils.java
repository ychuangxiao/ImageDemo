package com.banditcat.app.utils;


import android.util.Log;

import com.banditcat.app.BuildConfig;


/**
 * 文件名称：LogUtils
 * <br/>
 * 功能描述：日志工具类
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：15/11/17 下午3:36
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：15/11/17 下午3:36
 * <br/>
 * 修改备注：
 */
public class LogUtils {


    public static void e(String tag, String msg) {


        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }

    }


    public static void w(String tag, Throwable tr) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, tr);
        }
    }

    public static void w(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, msg);
        }

    }

    public static void w(String tag, String msg, Throwable tr) {
        if (BuildConfig.DEBUG) {
            Log.w(tag, msg, tr);
        }
    }


    public static void d(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }


    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg);
        }
    }


    public static void v(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, msg);
        }
    }

}
