package com.banditcat.data.rest;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.banditcat.data.constant.TextConstant;
import com.banditcat.data.entitys.base.AppEntity;
import com.banditcat.data.entitys.base.BaseRespEntity;
import com.banditcat.data.entitys.realm.UserRealm;
import com.banditcat.data.entitys.rep.LogonEntity;
import com.banditcat.data.entitys.rep.LogoutReqEntity;
import com.banditcat.data.entitys.rep.base.BaseCrashReqEntity;
import com.banditcat.data.entitys.rep.base.BaseReqEntity;
import com.banditcat.data.entitys.resp.LoginResEntity;
import com.banditcat.data.repository.BaseRepository;
import com.banditcat.data.utils.HttpRespExceptionUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.exceptions.RealmException;
import okhttp3.ResponseBody;

/**
 * 文件名称：{@link RestDataSource}
 * <br/>
 * 功能描述：Rest 数据源
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：2017/4/18 15:51
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：2017/4/18 15:51
 * <br/>
 * 修改备注：
 */
@Singleton
public class RestDataSource implements BaseRepository {
    private RestApi mRestApi;

    private RestApi mUnFailureRestApi;

    private Context mContext;

    @Inject
    public RestDataSource(AppEntity appEntity, Endpoint endpoint) {

        mContext = appEntity.getContext();
        mRestApi = ServiceGenerator.createService(ServiceGenerator.createOkHttpClient(appEntity, true), RestApi.class, endpoint.getEndpoint());
        mUnFailureRestApi = ServiceGenerator.createService(ServiceGenerator.createOkHttpClient(appEntity, false), RestApi.class, endpoint
                .getEndpoint());

    }


    /**
     * 保存崩溃日志信息
     *
     * @param crashReqEntity 崩溃日志实体
     * @return
     */
    @Override
    public Observable<BaseRespEntity> saveCrashLog(BaseCrashReqEntity crashReqEntity) {


        return mRestApi.saveCrashLog(TextConstant.API_KEY, crashReqEntity).onErrorResumeNext(new HttpRespExceptionUtils<BaseRespEntity>());


    }

    /**
     * 自动登录
     * <p>
     * 按照登录时间倒序，同时认证信息不为空的
     *
     * @return
     */
    public Observable<UserRealm> autoLogin() {
        Realm realm = Realm.getDefaultInstance();

        RealmResults<UserRealm> userRealms = realm.where(UserRealm.class).isNotNull(TextConstant.COLUMN_NAME_FOR_PASSWORD).findAllSorted
                (TextConstant.COLUMN_NAME_FOR_LATESTDATE, Sort.DESCENDING);


        return (userRealms != null && !userRealms.isEmpty()) ? Observable.just(userRealms.first()) : Observable.just(new UserRealm());

    }


    /**
     * 检查更新
     *
     * @return
     */
    @Override
    public Observable<BaseRespEntity> checkUpdate(BaseReqEntity baseReqEntity) {

        return mRestApi.checkUpdate(TextConstant.API_KEY).onErrorResumeNext(new HttpRespExceptionUtils<BaseRespEntity>());

    }

    /**
     * 下载文件
     *
     * @return
     */
    @Override
    public Observable<ResponseBody> downFile(BaseReqEntity baseReqEntity) {


        return mRestApi.downFile(baseReqEntity.getAuthorization(), baseReqEntity.getUrl());
    }


    /**
     * 用户登录
     *
     * @param logonEntity 登录实体
     * @return
     */
    @Override
    public Observable<BaseRespEntity<LoginResEntity>> login(final LogonEntity logonEntity) {

        return mRestApi.login(TextConstant.API_KEY, logonEntity).doOnNext(new Consumer<BaseRespEntity<LoginResEntity>>() {
            @Override
            public void accept(BaseRespEntity<LoginResEntity> loginResEntity) throws Exception {
                if (null == loginResEntity.getData()) {
                    return;
                }


                saveUserToDb(loginResEntity.getData(), logonEntity);
            }
        }).onErrorResumeNext(new HttpRespExceptionUtils<BaseRespEntity<LoginResEntity>>());
    }

    /**
     * 用户退出
     *
     * @param logoutReqEntity
     * @return
     */
    @Override
    public Observable<BaseRespEntity> logout(final LogoutReqEntity logoutReqEntity) {

        delUserToDb(logoutReqEntity.getAuthorization(), logoutReqEntity.getUserId());
        return mRestApi.logout(logoutReqEntity.getAuthorization()).onErrorResumeNext(new HttpRespExceptionUtils<BaseRespEntity>());
    }


    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isNoThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager = (ConnectivityManager) this.mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return !isConnected;
    }

    /**
     * save user info
     *
     * @param respEntity  User login response entity
     * @param logonEntity User login name
     * @return true or false
     */
    private boolean saveUserToDb(final LoginResEntity respEntity, final LogonEntity logonEntity) {
        try {


            //删除前一个用户的信息，然后添加当前用户的信息

            Realm realm = Realm.getDefaultInstance();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.where(UserRealm.class).findAll().deleteAllFromRealm();//删除用户信息


                    UserRealm user = realm.createObject(UserRealm.class);
                    user.setUserId(respEntity.getUserCode());
                    user.setUserUid(logonEntity.getUsername());
                    user.setPassword(respEntity.getAuthCode());
                    user.setLatestDate(System.currentTimeMillis());


                }
            });

            realm.close();

            return true;

        } catch (final RealmException ex) {
            return true;
        }

    }


    /**
     * delete user info
     */
    void delUserToDb(final String authorization, final String userId) {
        Realm userRealm = Realm.getDefaultInstance();

        userRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                RealmResults<UserRealm> users = realm.where(UserRealm.class).equalTo("password", authorization, Case.INSENSITIVE).findAll();

                if (null != users && users.size() >= 1) {
                    users.get(0).setPassword(null); // indirectly delete object
                }
            }
        });

        userRealm.close();
    }
}