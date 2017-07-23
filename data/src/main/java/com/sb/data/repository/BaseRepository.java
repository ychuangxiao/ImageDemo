package com.sb.data.repository;

import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.base.BaseRespEntity;
import com.sb.data.entitys.realm.UserRealm;
import com.sb.data.entitys.rep.LogonEntity;
import com.sb.data.entitys.rep.LogoutReqEntity;
import com.sb.data.entitys.rep.RegReqEntity;
import com.sb.data.entitys.rep.base.BaseCrashReqEntity;
import com.sb.data.entitys.rep.base.BaseReqEntity;
import com.sb.data.entitys.resp.LoginResEntity;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;


/**
 * 文件名称：{@link BaseRepository}
 * <br/>
 * 功能描述：基类仓储
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：2017/4/18 15:49
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：2017/4/18 15:49
 * <br/>
 * 修改备注：
 */
public interface BaseRepository {

    /**
     * 保存崩溃日志信息
     *
     * @param crashReqEntity 崩溃日志实体
     * @return
     */
    Observable<BaseRespEntity> saveCrashLog(BaseCrashReqEntity crashReqEntity);

    /**
     * 用户登录
     *
     * @param logonEntity 登录实体
     * @return
     */
    Observable<BaseRespEntity<LoginResEntity>> login(LogonEntity logonEntity);

    /**
     * 用户退出
     *
     * @return
     */
    Observable<BaseRespEntity> logout(LogoutReqEntity logoutReqEntity);


    /**
     * 自动登录
     * <p>
     * 按照登录时间倒序，同时认证信息不为空的
     *
     * @return
     */
    Observable<UserRealm> autoLogin();


    /**
     * 检查更新
     *
     * @return
     */
    Observable<BaseRespEntity> checkUpdate(BaseReqEntity baseReqEntity);


    /**
     * 下载文件
     *
     * @return
     */
    Observable<ResponseBody> downFile(BaseReqEntity baseReqEntity);

    /**
     * 用户注册
     *
     * @param entity        请求实体
     * @return
     */
    Observable<BaseRespEntity> register( RegReqEntity
            entity);


    Observable<BaseRespEntity> checkToken(BaseReqEntity baseReqEntity);


    Observable<BaseRespEntity> checkVer(BaseReqEntity baseReqEntity);
}
