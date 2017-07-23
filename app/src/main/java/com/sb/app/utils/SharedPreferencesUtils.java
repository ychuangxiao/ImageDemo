package com.sb.app.utils;


import android.content.Context;
import android.content.SharedPreferences;

import com.sb.app.utils.sharedpreferences.BooleanPrefField;
import com.sb.app.utils.sharedpreferences.EditorHelper;
import com.sb.app.utils.sharedpreferences.SharedPreferencesHelper;
import com.sb.app.utils.sharedpreferences.StringPrefEditorField;
import com.sb.app.utils.sharedpreferences.StringPrefField;
import com.sb.data.constant.TextConstant;

/**
 * 文件名称：{@link SharedPreferencesUtils}
 * <br/>
 * 功能描述：存储工具类
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/3 13:46
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/3 13:46
 * <br/>
 * 修改备注：
 */
public final class SharedPreferencesUtils extends SharedPreferencesHelper {

    public SharedPreferencesUtils(Context context) {
        super(context.getSharedPreferences((getLocalClassName(context) + "_ISharedpreferences"), 0));
    }

    public ISharedpreferencesEditor edit() {
        return new ISharedpreferencesEditor(getSharedPreferences());
    }


    private static String getLocalClassName(Context context) {
        String packageName = context.getPackageName();
        String className = context.getClass().getName();
        int packageLen = packageName.length();
        if (((!className.startsWith(packageName)) || (className.length() <= packageLen)) || (className.charAt(packageLen) != '.')) {
            return className;
        }
        return className.substring((packageLen + 1));
    }

    public final static class ISharedpreferencesEditor extends EditorHelper<ISharedpreferencesEditor> {


        ISharedpreferencesEditor(SharedPreferences sharedPreferences) {
            super(sharedPreferences);
        }

        public StringPrefEditorField<ISharedpreferencesEditor> Authorization() {
            return stringField(TextConstant.AUTHOR_NAME);
        }

        public StringPrefEditorField<ISharedpreferencesEditor> UserId() {
            return stringField("UserId");
        }

        public StringPrefEditorField<ISharedpreferencesEditor> Station() {
            return stringField("Station");
        }

        public StringPrefEditorField<ISharedpreferencesEditor> StationNick() {
            return stringField("stationNick");
        }

        public StringPrefEditorField<ISharedpreferencesEditor> ApkAddress() {
            return stringField("ApkAddress");
        }


        public StringPrefEditorField<ISharedpreferencesEditor> UserNick() {
            return stringField("UserNick");
        }


    }


    /**
     * 授权码
     *
     * @return
     */
    public StringPrefField Authorization() {
        return stringField(TextConstant.AUTHOR_NAME, "");
    }

    /**
     * 用户编号
     *
     * @return
     */
    public StringPrefField UserId() {
        return stringField("UserId", "");
    }

    /**
     * 司机棋点
     *
     * @return
     */
    public StringPrefField Station() {
        return stringField("Station", "");
    }


    /**
     * 司机棋点昵称
     *
     * @return
     */
    public StringPrefField StationNick() {
        return stringField("stationNick", "");
    }


    /**
     * apk地址
     *
     * @return
     */
    public StringPrefField ApkAddress() {
        return stringField("ApkAddress", "");
    }

    /**
     * 用户昵称
     *
     * @return
     */
    public StringPrefField UserNick() {
        return stringField("UserNick", "");
    }


    /**
     * apk更新状态
     *
     * @return
     */
    public BooleanPrefField ApkUpdated() {
        return booleanField("ApkUpdated", false);
    }


    /**
     * 是否同步
     *
     * @return
     */
    public BooleanPrefField Synced() {
        return booleanField("Synced", false);
    }


    /**
     * 是否水印
     *
     * @return
     */
    public BooleanPrefField Watermark() {
        return booleanField("Watermark", true);
    }

}
