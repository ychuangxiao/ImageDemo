package com.sb.domain.utils;


import android.text.TextUtils;

import com.sb.data.entitys.base.BaseRespEntity;
import com.sb.data.rest.exceptions.BaseErrorException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;

/**
 * 文件名称：{@link RetrofitVoidUtils}
 * <br/>
 * 功能描述：Retrofit Utils
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/5 18:48
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/5 18:48
 * <br/>
 * 修改备注：
 */
public class RetrofitVoidUtils implements Function<BaseRespEntity, Observable<BaseRespEntity>> {


    /**
     * Apply some calculation to the input value and return some other value.
     *
     * @param baseResponseEntity the input value
     * @return the output value
     * @throws Exception on error
     */
    @Override
    public Observable<BaseRespEntity> apply(final BaseRespEntity baseResponseEntity) throws Exception {
        return Observable.create(new ObservableOnSubscribe<BaseRespEntity>() {
            @Override
            public void subscribe(ObservableEmitter<BaseRespEntity> subscriber) throws Exception {
                if (baseResponseEntity.isSuccess()) {
                    subscriber.onNext(baseResponseEntity);
                } else if (!TextUtils.isEmpty(baseResponseEntity.getErr()) && !TextUtils.isEmpty(baseResponseEntity.getMsg())) {
                    //如果没有错误代码,说明不是业务逻辑错误,无须提供给客户端显示
                    subscriber.onError(new BaseErrorException(baseResponseEntity.getMsg(), baseResponseEntity.getErr(),baseResponseEntity.getErrorDetails()));
                }

                subscriber.onComplete();
            }
        });
    }
}
