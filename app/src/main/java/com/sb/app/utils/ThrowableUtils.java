package com.sb.app.utils;


import android.content.Intent;
import android.os.Bundle;

import com.sb.app.AndroidApplication;
import com.sb.app.mvp.views.BaseView;
import com.sb.app.views.activitys.DownApkActivity;
import com.sb.data.constant.TextConstant;
import com.sb.data.rest.exceptions.BaseErrorException;

import io.reactivex.functions.Consumer;

/**
 * 文件名称：{@link ThrowableUtils}
 * <br/>
 * 功能描述：异常工具类
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：9/13/16 09:51
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：9/13/16 09:51
 * <br/>
 * 修改备注：
 */
public class ThrowableUtils implements Consumer<Throwable> {

    public ThrowableUtils(BaseView baseView, AndroidApplication androidApplication) {
        mBaseView = baseView;
        mAndroidApplication = androidApplication;
    }

    private AndroidApplication mAndroidApplication;
    private BaseView mBaseView;


    /**
     * Consume the given value.
     *
     * @param throwable the value
     * @throws Exception on error
     */
    @Override
    public void accept(Throwable throwable) throws Exception {
        mBaseView.hideLoading();

        if (throwable instanceof BaseErrorException) {


            BaseErrorException baseErrorException = (BaseErrorException) throwable;

            switch (baseErrorException.getErrorCode()) {
                case TextConstant.APP_VERSION_9998:
                    mBaseView.navigateLogin();
                    mAndroidApplication.getApplicationComponent().context().clearShared();
                    break;
                case TextConstant.APP_VERSION_9999:


                    mAndroidApplication.getApplicationComponent().context().sharedpreferences.ApkUpdated().put(false);
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString(TextConstant.UPDATE_APP_ADDRESS_EXTRA, baseErrorException.getMessage());
                    bundle.putString(TextConstant.UPDATE_APP_DETAILS_EXTRA, baseErrorException.getErrorDetails());
                    intent.setClass(mBaseView.context(), DownApkActivity.class);
                    intent.putExtras(bundle);
                    mBaseView.context().startActivity(intent);
                    break;
                default:
                    mBaseView.showError(throwable.getMessage());
                    break;
            }

        } else {

            mBaseView.showError(throwable.getMessage());
        }
    }
}
