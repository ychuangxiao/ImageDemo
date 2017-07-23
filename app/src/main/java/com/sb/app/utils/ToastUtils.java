package com.sb.app.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 文件名称：{@link ToastUtils}
 * <br/>
 * 功能描述：Toast's tool
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：11/9/16 13:18
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：11/9/16 13:18
 * <br/>
 * 修改备注：
 */
public class ToastUtils {


    /**
     * 提示框
     *
     * @param context
     * @param message
     */
    public static void alert(Context context, String message) {



        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
