package com.banditcat.app.utils;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.banditcat.app.AndroidApplication;
import com.banditcat.data.constant.TextConstant;
import com.banditcat.data.rest.exceptions.BaseErrorException;

/**
 * 文件名称：{@link LoginUtils}
 * <br/>
 * 功能描述：登录工具
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/20 11:57
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：16/6/20 11:57
 * <br/>
 * 修改备注：
 */
public class LoginUtils {

    /**
     * 是否登录
     *
     * @param throwable
     * @return
     */
    public static boolean isLogin(Throwable throwable) {

        if (throwable instanceof BaseErrorException) {
            BaseErrorException baseErrorException = (BaseErrorException) throwable;


            switch (baseErrorException.getErrorCode()) {
                case "401":

                    Log.e("e", throwable.getMessage());

                    return false;
            }

        }
        return true;
    }

    /**
     * 是否更新APK
     *
     * @param throwable
     * @return
     */
    public static boolean isUpdateApk(Throwable throwable, Context context, AndroidApplication androidApplication) {

        if (throwable instanceof BaseErrorException) {
            BaseErrorException baseErrorException = (BaseErrorException) throwable;



            switch (baseErrorException.getErrorCode()) {
                case TextConstant.APP_VERSION_SERVER:

                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();

                    bundle.putString(TextConstant.UPDATE_APP_ADDRESS_EXTRA, throwable.getMessage());

                    //intent.setClass(androidApplication, DownApkActivity.class);
                    intent.putExtras(bundle);

                    context.startActivity(intent);
                    return true;
            }

        }
        return false;
    }


    /**
     * 是否更新APK
     *
     * @param url 下载地址
     * @return
     */
    public static void updateApk(String url, Context context, AndroidApplication androidApplication) {
/*
        Intent intent = new Intent(context, DownApkActivity.class);


        context.startActivity(intent);*/
    }
}
