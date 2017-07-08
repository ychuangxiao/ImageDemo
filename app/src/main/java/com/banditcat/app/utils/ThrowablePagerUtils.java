package com.banditcat.app.utils;


import io.reactivex.functions.Consumer;

/**
 * 文件名称：{@link ThrowablePagerUtils}
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
public class ThrowablePagerUtils implements Consumer<Throwable> {
    /**
     * Consume the given value.
     *
     * @param throwable the value
     * @throws Exception on error
     */
    @Override
    public void accept(Throwable throwable) throws Exception {

    }

   /* public ThrowablePagerUtils(PagerView baseView, BaseQueryReqModel baseQueryReqModel, AndroidApplication androidApplication) {
        mBaseView = baseView;
        mAndroidApplication = androidApplication;
        mBaseQueryReqModel = baseQueryReqModel;
    }

    private AndroidApplication mAndroidApplication;
    private PagerView mBaseView;
    private BaseQueryReqModel mBaseQueryReqModel;

    *//**
     * Consume the given value.
     *
     * @param throwable the value
     * @throws Exception on error
     *//*
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
                    intent.setClass(mBaseView.getContext(), DownApkActivity.class);
                    intent.putExtras(bundle);
                    mBaseView.getContext().startActivity(intent);
                    break;
                default:
                    mBaseView.noticePagerError(mBaseQueryReqModel.getPage() - 1, baseErrorException.getMessage());
                    break;
            }

        } else {
            mBaseView.noticePagerError(mBaseQueryReqModel.getPage() - 1, throwable.getMessage());

        }
    }*/
}
