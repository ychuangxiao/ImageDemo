package com.banditcat.app.utils;


import android.content.Intent;
import android.os.Bundle;

import com.banditcat.app.AndroidApplication;
import com.banditcat.app.mvp.views.BaseView;
import com.banditcat.data.constant.TextConstant;
import com.banditcat.data.rest.exceptions.BaseErrorException;

import io.reactivex.functions.Consumer;

/**
 * 文件名称：{@link ThrowableUtils}
 * <br/>
 * 功能描述：异常工具类
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：9/13/16 09:51
 * <br/>
 * 修改作者：banditcat
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
                case "401":
                    mBaseView.navigateLogin();

                    break;
                case TextConstant.APP_VERSION_SERVER:

                    if (!mAndroidApplication.getApplicationComponent().context().sharedpreferences.ApkUpdated().get())
                    {
                        return;
                    }

                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();

                    bundle.putString(TextConstant.UPDATE_APP_ADDRESS_EXTRA, throwable.getMessage());

                    //intent.setClass(mBaseView.getContext(), DownApkActivity.class);
                    intent.putExtras(bundle);

                    mBaseView.getContext().startActivity(intent);
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
