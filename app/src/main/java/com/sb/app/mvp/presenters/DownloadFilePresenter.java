package com.sb.app.mvp.presenters;


import android.os.Environment;

import com.sb.app.AndroidApplication;
import com.sb.app.R;
import com.sb.app.di.PerActivity;
import com.sb.app.mvp.presenters.base.Presenter;
import com.sb.app.mvp.views.DownloadFileView;
import com.sb.app.utils.LogUtils;
import com.sb.app.utils.ThrowableUtils;
import com.sb.data.entitys.rep.base.BaseReqEntity;
import com.sb.domain.interactor.DownloadFileUseCase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

/**
 * 文件名称：IBaseListView
 * <p/>
 * 功能描述：检查更新-提交中间者
 * <p/>
 * 创建作者：administrator
 * <p/>
 * 创建时间：15/11/17 下午3:05
 * <p/>
 * 修改作者：administrator
 * <p/>
 * 修改时间：15/11/17 下午3:05
 * <p/>
 * 修改备注：
 */
@PerActivity
public class DownloadFilePresenter implements Presenter<DownloadFileView> {
    private Disposable mDisposable;//订阅

    private DownloadFileUseCase mDownloadFileUseCase;//检查更新-提交用例
    private AndroidApplication mAndroidApplication;

    DownloadFileView mBaseView;

    /**
     * 用户登录构造函数
     *
     * @param androidApplication android main application
     */
    @Inject
    public DownloadFilePresenter(DownloadFileUseCase checkUpdateUseCase, AndroidApplication androidApplication) {
        this.mDownloadFileUseCase = checkUpdateUseCase;
        mAndroidApplication = androidApplication;
    }


    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    @Override
    public void resume() {

    }

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    @Override
    public void pause() {

    }

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    @Override
    public void destroy() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }



    @Override
    public void attachView(DownloadFileView baseView) {

        mBaseView = baseView;
    }

    /**
     * 入库详情搜索
     *
     */
    public void check() {

        BaseReqEntity baseReqEntity = new BaseReqEntity();

        mBaseView.showLoading();
        baseReqEntity.setUrl(mAndroidApplication.sharedpreferences.ApkAddress().get());
        mDownloadFileUseCase.setBaseReqEntity(baseReqEntity);
        mDisposable = mDownloadFileUseCase.execute().subscribe(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody responseBody) throws Exception {
                if (writeResponseBodyToDisk(responseBody)) {
                    mBaseView.hideLoading();
                    mBaseView.downLoadSuccess(futureStudioIconFile);
                } else {
                    mBaseView.hideLoading();
                    mBaseView.showError("更新失败!");
                }
            }
        }, new ThrowableUtils(mBaseView,mAndroidApplication));

    }
    private File futureStudioIconFile;

    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {

            String fileName = mAndroidApplication.getResources().getString(R.string.name_apk);
            futureStudioIconFile = new File(
                    Environment.getExternalStorageDirectory(),
                    fileName);


            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {


                if (futureStudioIconFile.exists()) {
                    futureStudioIconFile.delete();
                }


                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;
                    LogUtils.e("File", "file download: " + fileSizeDownloaded + " of " + fileSize +":比例" + fileSizeDownloaded*1024/fileSize);
                }

                outputStream.flush();


                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

}
