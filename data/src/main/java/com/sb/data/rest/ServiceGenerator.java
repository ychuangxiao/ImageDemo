package com.sb.data.rest;


import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.base.AppEntity;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 文件名称：{@link ServiceGenerator}
 * <br/>
 * 功能描述： 标准服务
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/5 18:49
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/5 18:49
 * <br/>
 * 修改备注：
 */
public class ServiceGenerator {


    public static OkHttpClient.Builder createOkHttpClient(final AppEntity appEntity, boolean onFailure) {


        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .header(TextConstant.HEADER_NAME_ONE, appEntity.getAppVersion().toString())
                        .header(TextConstant.HEADER_ACCEPT, TextConstant.HEADER_APPLICATION_JSON_VALUE)
                        .header(TextConstant.HEADER_CONTENT_TYPE,
                        TextConstant.HEADER_APPLICATION_JSON_VALUE).build();


                return chain.proceed(request);
            }
        });


        //主要是为了区分查询和操作处理,一般查询可以重连,但是操作不能重连
        if (onFailure) {
            httpClient.connectTimeout(15, TimeUnit.SECONDS);
            httpClient.retryOnConnectionFailure(true);
        } else {
            httpClient.connectTimeout(60, TimeUnit.SECONDS).retryOnConnectionFailure(false).readTimeout(60, TimeUnit.SECONDS).writeTimeout(60,
                    TimeUnit.SECONDS);

        }


        HttpLoggingInterceptor logginInterceptor = new HttpLoggingInterceptor();
        logginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(logginInterceptor);


        return httpClient;
    }

    /**
     * 创建服务
     *
     * @param serviceClass 服务类
     * @param baseUrl      api  地址,必须以/结束
     * @return
     */
    public static <S> S createService(OkHttpClient.Builder httpClient, Class<S> serviceClass, String baseUrl) {


        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory
                (RxJava2CallAdapterFactory.create()).client(httpClient.build()).build();


        return retrofit.create(serviceClass);
    }

}
