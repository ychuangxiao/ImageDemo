package com.banditcat.domain.interactor;


import com.banditcat.data.constant.TextConstant;
import com.banditcat.data.entitys.base.BaseRespEntity;
import com.banditcat.data.entitys.rep.LogoutReqEntity;
import com.banditcat.data.entitys.rep.RegReqEntity;
import com.banditcat.data.entitys.rep.base.BaseReqEntity;
import com.banditcat.data.repository.BaseRepository;
import com.banditcat.data.rest.exceptions.BaseErrorException;
import com.banditcat.domain.utils.RetrofitVoidUtils;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * 文件名称：{@link BaseRespUseCase}
 * <br/>
 * 功能描述： 统一返回相应实体用例
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/5 18:48
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：16/6/5 18:48
 * <br/>
 * 修改备注：
 */
public class BaseRespUseCase extends BaseCase<BaseRespEntity> {

    private final BaseRepository mBaseRepository;
    private final Scheduler mUiThread;
    private final Scheduler mExecutorThread;
    private BaseReqEntity mBaseReqEntity;
    private LogoutReqEntity mLogoutReqEntity;
    private RegReqEntity mRegReqEntity;

    public void setBaseReqEntity(BaseReqEntity baseReqEntity) {
        mBaseReqEntity = baseReqEntity;

    }

    public void setLogoutReqEntity(LogoutReqEntity logoutReqEntity) {
        mLogoutReqEntity = logoutReqEntity;

        mBaseReqEntity = logoutReqEntity;
    }

    public void setRegReqEntity(RegReqEntity regReqEntity) {
        mRegReqEntity = regReqEntity;
        mBaseReqEntity = regReqEntity;
    }

    @Inject
    public BaseRespUseCase(BaseRepository baseRepository, @Named("ui_thread") Scheduler uiThread, @Named
            ("executor_thread") Scheduler executorThread) {
        mBaseRepository = baseRepository;

        mUiThread = uiThread;
        mExecutorThread = executorThread;

    }


    @Override
    public Observable<BaseRespEntity> buildObservable() {


        switch (mBaseReqEntity.getAction()) {
            case TextConstant.ACTION_LOGOUT:
                return mBaseRepository.logout(mLogoutReqEntity).observeOn(mUiThread).subscribeOn(mExecutorThread)
                        .flatMap(new
                        RetrofitVoidUtils());
            case TextConstant.ACTION_REG:
                return mBaseRepository.register(mRegReqEntity).observeOn(mUiThread).subscribeOn(mExecutorThread).flatMap
                        (new
                                RetrofitVoidUtils());
            default:
                return Observable.error(new BaseErrorException(TextConstant.BASE_SERVER_ERROR_MSG,""));
        }

    }


}
