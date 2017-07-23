package com.sb.app.views.activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;

import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.di.components.DaggerBizComponent;
import com.sb.app.di.modules.ActivityModule;
import com.sb.app.mvp.presenters.DownloadFilePresenter;
import com.sb.app.mvp.views.DownloadFileView;
import com.sb.app.utils.ProgressUtils;
import com.sb.app.views.base.BaseDaggerActivity;
import com.sb.data.constant.TextConstant;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * 文件名称：{@link DownApkActivity}
 * <br/>
 * 功能描述： apk自动更新
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：2017/5/6 13:23
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：2017/5/6 13:23
 * <br/>
 * 修改备注：
 */
public class DownApkActivity extends BaseDaggerActivity implements DownloadFileView {


    @Inject
    DownloadFilePresenter mDownloadFilePresenter;

    private Disposable mSubscription;

    private String mApkAddress;

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_down_apk;
    }


    /**
     * 初始化参数
     */
    private void injectExtras() {
        Bundle extras_ = getIntent().getExtras();
        if (extras_ == null) {

            finish();
            return;
        }

        if (!extras_.containsKey(TextConstant.UPDATE_APP_ADDRESS_EXTRA)) {
            finish();
            return;
        }

        mApkAddress = extras_.getString(TextConstant.UPDATE_APP_ADDRESS_EXTRA);


        if (StringUtils.isEmpty(mApkAddress)) {
            alertMsg("下载地址有误！");
            finish();
        }
    }

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {
        initUi();

        injectExtras();

        


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage(getResources().getString(R.string.title_download_confirm_title)).setPositiveButton
                (getResources().getString(R.string
                        .title_setting_confirm_yes), new DialogInterface.OnClickListener() {// 退出按钮
                    public void onClick(DialogInterface dialog, int i) {


                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        Uri content_url = Uri.parse(mApkAddress);
                        intent.setData(content_url);
                        startActivity(intent);


                        finish();


                    }
                }).setNegativeButton(getResources().getString(R.string.title_setting_confirm_no), new DialogInterface
                .OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        }).show
                ();// 显示对话框




      /*  mDownloadFilePresenter.attachView(this);
        mProgressDialog.setProgressNumberFormat("已下载%1d KB/共%2d KB");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setMessage(getString(R.string.title_download_confirm));
        mProgressDialog.setIndeterminate(false);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);


        setDownLoadInfo();*/
    }

    private void setDownLoadInfo() {

        ProgressUtils progressUtils = new ProgressUtils();

        progressUtils.setProgressOverListener(new ProgressUtils.ProgressOverListener() {
            @Override
            public void onFailure(File file) {
                mProgressDialog.dismiss();
                downLoadSuccess(file);

            }

            @Override
            public void onError() {

                mProgressDialog.dismiss();
                finish();
            }
        });
        progressUtils.setProgressListener(new ProgressUtils.ProgressListener() {
            @Override
            public void update(long bytesRead, long contentLength, boolean done) {
                mProgressDialog.setMax((int) (contentLength / 1024));
                mProgressDialog.setProgress((int) (bytesRead / 1024));

            }
        });


        mProgressDialog.show();
        try {
            progressUtils.downLoad(mApkAddress, this);
        } catch (Exception e) {

            getApplicationComponent().context().sharedpreferences.ApkUpdated().put(true);
            mProgressDialog.dismiss();
            finish();
        }
    }

    /**
     * 初始化UI
     */
    void initUi() {
        setToolTitle(getString(R.string.title_download_confirm_title)).setToolTitleGravity(Gravity.CENTER);

    }

    /**
     * 初始化用户登录注射器
     */
    @Override
    public void initInjector() {
        DaggerBizComponent.builder().applicationComponent(getApplicationComponent()).activityModule
                (getActivityModule()).build().inject(this);
    }

    /**
     * 初始化用户登录中间者
     */
    @Override
    public void initPresenter() {


    }


    /**
     * 下载成功成功
     */
    @Override
    public void downLoadSuccess(File file) {

        mProgressDialog.dismiss();

        
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
        finish();
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

        mProgressDialog.dismiss();

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

    @Override
    public Context context() {
        return this;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

    private void destroy() {
        if (null != mSubscription && !mSubscription.isDisposed()) {
            mSubscription.dispose();
        }

        mDownloadFilePresenter.destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        destroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case AppConstant.ACTION_10: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    setDownLoadInfo();
                } else {
                    // 权限被用户拒绝了，洗洗睡吧。
                }
                return;
            }
        }
    }
}
