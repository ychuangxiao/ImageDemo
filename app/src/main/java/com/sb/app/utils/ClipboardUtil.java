package com.sb.app.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by Administrator on 2017/7/25.
 */

public class ClipboardUtil {

    /**
     * 实现文本复制功能
     *
     * @param content
     */
    public static void copy(String content, Context context) {
        android.content.ClipboardManager c = (android.content.ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        c.setPrimaryClip(ClipData.newPlainText("银鲨网络", content));
    }
}
