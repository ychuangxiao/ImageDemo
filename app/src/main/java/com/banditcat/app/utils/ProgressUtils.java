package com.banditcat.app.utils;


import android.content.Context;
import android.os.Environment;

import com.banditcat.app.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * 文件名称：{@link ProgressUtils}
 * <br/>
 * 功能描述：文件下载进度
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/22 14:17
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：16/6/22 14:17
 * <br/>
 * 修改备注：
 */
public class ProgressUtils {


    private Context mContext;
    private ProgressListener mProgressListener;
    private ProgressOverListener mProgressOverListener;

    public void setProgressOverListener(ProgressOverListener progressOverListener) {
        mProgressOverListener = progressOverListener;
    }

    public void setProgressListener(ProgressListener progressListener) {
        mProgressListener = progressListener;
    }

    /**
     * 下载附件
     *
     * @param url     地址
     * @param context 上下文
     * @throws Exception
     */
    public void downLoad(String url, Context context) {

        mContext = context;
        Request request = new Request.Builder().url(url).build();


        OkHttpClient client = new OkHttpClient
                .Builder()
                .retryOnConnectionFailure(true)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response originalResponse = chain.proceed(chain.request());


                        return originalResponse.newBuilder().body(new ProgressResponseBody(originalResponse.body(),
                                mProgressListener)).build();
                    }
                }).build();


        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mProgressOverListener.onError();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    mProgressOverListener.onError();
                }

                writeResponseBodyToDisk(response.body());
            }
        });


    }


    private static class ProgressResponseBody extends ResponseBody {

        private final ResponseBody responseBody;
        private final ProgressListener progressListener;
        private BufferedSource bufferedSource;

        public ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
            this.responseBody = responseBody;
            this.progressListener = progressListener;
        }

        @Override
        public MediaType contentType() {
            return responseBody.contentType();
        }

        @Override
        public long contentLength() {
            return responseBody.contentLength();
        }

        @Override
        public BufferedSource source() {
            if (bufferedSource == null) {
                bufferedSource = Okio.buffer(source(responseBody.source()));
            }
            return bufferedSource;
        }

        private Source source(Source source) {
            return new ForwardingSource(source) {
                long totalBytesRead = 0L;

                @Override
                public long read(Buffer sink, long byteCount) throws IOException {
                    long bytesRead = super.read(sink, byteCount);
                    // read() returns the number of bytes read, or -1 if this source is exhausted.
                    totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                    progressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1);

                    LogUtils.e("Push", String.valueOf("获取到" + bytesRead));

                    return bytesRead;
                }
            };
        }
    }

    public interface ProgressOverListener {
        void onFailure(File file);

        void onError();
    }

    public interface ProgressListener {
        void update(long bytesRead, long contentLength, boolean done);
    }

    private File futureStudioIconFile;

    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {

            String fileName = mContext.getString(R.string.name_apk);
            futureStudioIconFile = new File(Environment.getExternalStorageDirectory(), fileName);


            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {


                if (futureStudioIconFile.exists()) {
                    futureStudioIconFile.delete();
                }


                byte[] fileReader = new byte[1024];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {

                        if (null != mProgressOverListener) {
                            mProgressOverListener.onFailure(futureStudioIconFile);
                        }

                        break;
                    }


                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;
                    LogUtils.e("File", "file download: " + fileSizeDownloaded + " of " + fileSize + ":比例" + fileSizeDownloaded * 1024 / fileSize);
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
