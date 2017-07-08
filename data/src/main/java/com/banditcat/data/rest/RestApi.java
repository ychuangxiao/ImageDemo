package com.banditcat.data.rest;


import com.banditcat.data.constant.TextConstant;
import com.banditcat.data.entitys.base.BaseRespEntity;
import com.banditcat.data.entitys.rep.LogonEntity;
import com.banditcat.data.entitys.rep.base.BaseCrashReqEntity;
import com.banditcat.data.entitys.resp.LoginResEntity;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 文件名称：{@link RestApi}
 * <br/>
 * 功能描述：Rest Api
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：2017/4/18 15:50
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：2017/4/18 15:50
 * <br/>
 * 修改备注：
 */
public interface RestApi {

    /**
     * 保存崩溃日志信息
     *
     * @param authorization  认证信息
     * @param crashReqEntity 崩溃日志实体
     * @return
     */
    @POST("er/upload")
    Observable<BaseRespEntity> saveCrashLog(@Header(TextConstant.AUTHOR_NAME) String authorization, @Body BaseCrashReqEntity crashReqEntity);


    /**
     * 用户登录
     *
     * @param logonEntity 登录实体
     * @return
     */
    @POST("sec/login/")
    Observable<BaseRespEntity<LoginResEntity>> login(@Header(TextConstant.AUTHOR_NAME) String authorization, @Body LogonEntity logonEntity);


    /**
     * 用户退出
     *
     * @return
     */
    @GET("sec/logout")
    Observable<BaseRespEntity> logout(@Header(TextConstant.AUTHOR_NAME) String authorization);

 

    /**
     * 检查更新
     *
     * @param authorization 认证信息
     * @return
     */
    @GET("sec/checkver")
    Observable<BaseRespEntity> checkUpdate(@Header(TextConstant.AUTHOR_NAME) String authorization);


    @Streaming
    @GET
    Observable<ResponseBody> downFile(@Header(TextConstant.AUTHOR_NAME) String authorization, @Url String fileUrl);
 

}
