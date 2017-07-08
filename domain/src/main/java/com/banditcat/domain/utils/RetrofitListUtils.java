package com.banditcat.domain.utils;


import android.text.TextUtils;

import com.banditcat.data.constant.TextConstant;
import com.banditcat.data.entitys.base.BaseRespEntity;
import com.banditcat.data.rest.exceptions.BaseErrorException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;

/**
 * 文件名称：{@link RetrofitListUtils}
 * <br/>
 * 功能描述：Retrofit Utils
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
public class RetrofitListUtils<T> implements Function<BaseRespEntity<T>, Observable<BaseRespEntity<T>>> {


    /**
     * Apply some calculation to the input value and return some other value.
     *
     * @param baseResponseEntity the input value
     * @return the output value
     * @throws Exception on error
     */
    @Override
    public Observable<BaseRespEntity<T>> apply(final BaseRespEntity<T> baseResponseEntity) throws Exception {


        return Observable.create(new ObservableOnSubscribe<BaseRespEntity<T>>() {
            @Override
            public void subscribe(ObservableEmitter<BaseRespEntity<T>> e) throws Exception {
                if (baseResponseEntity.isSuccess() && null != baseResponseEntity.getData()) {

                    e.onNext(baseResponseEntity);

                } else if (!TextUtils.isEmpty(baseResponseEntity.getErr()) && !TextUtils.isEmpty(baseResponseEntity.getMsg())) {
                    //如果没有错误代码,说明不是业务逻辑错误,无须提供给客户端显示
                    e.onError(new BaseErrorException(baseResponseEntity.getMsg(), baseResponseEntity.getErr()));
                } else if ( null != baseResponseEntity.getTotalCount() && baseResponseEntity.getTotalCount() < 0) {
                    e.onError(new BaseErrorException(TextConstant.BASE_NO_DATA_ERROR_MSG, ""));
                } else {
                    e.onError(new BaseErrorException(TextConstant.BASE_ERROR_MSG, ""));
                }
                e.onComplete();
            }
        });

    }
}
