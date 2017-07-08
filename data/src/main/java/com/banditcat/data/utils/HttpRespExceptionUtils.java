package com.banditcat.data.utils;


import android.util.Log;

import com.banditcat.data.constant.TextConstant;
import com.banditcat.data.rest.exceptions.BaseErrorException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.net.ConnectException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * 文件名称：{@link HttpRespExceptionUtils}
 * <br/>
 * 功能描述：
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/21 20:19
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：16/6/21 20:19
 * <br/>
 * 修改备注：
 */

public class HttpRespExceptionUtils<T> implements Function<Throwable, ObservableSource<T>> {

    /**
     * Apply some calculation to the input value and return some other value.
     *
     * @param throwable the input value
     * @return the output value
     * @throws Exception on error
     */
    @Override
    public ObservableSource<T> apply(Throwable throwable) throws Exception {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;


            switch (httpException.code()) {
                case 401:

                    Log.e("e", throwable.getMessage());

                    return Observable.error(new BaseErrorException(TextConstant.BASE_ERROR_UN_AUTHORIZATION_MSG, String.valueOf(httpException.code
                            ())));
            }

        } else if (throwable instanceof ConnectException) {
            return Observable.error(new BaseErrorException(TextConstant.BASE_ERROR_MSG, ""));
        }

        return Observable.error(new BaseErrorException(TextConstant.BASE_SERVER_ERROR_MSG, ""));
    }
}


