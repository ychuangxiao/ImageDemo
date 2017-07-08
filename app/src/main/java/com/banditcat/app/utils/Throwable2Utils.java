package com.banditcat.app.utils;


import com.banditcat.data.rest.exceptions.BaseErrorException;

import io.reactivex.functions.Consumer;

/**
 * 文件名称：{@link Throwable2Utils}
 * <br/>
 * 功能描述：异常工具类
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：9/13/16 09:51
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：9/13/16 09:51
 * <br/>
 * 修改备注：
 */
public abstract class Throwable2Utils<T extends Throwable> implements Consumer<T> {


    /**
     * Consume the given value.
     *
     * @param t the value
     * @throws Exception on error
     */
    @Override
    public void accept(T t) throws Exception {
        if (t instanceof BaseErrorException) {

            call((BaseErrorException) t);

        } else {
            call(new BaseErrorException(t.getMessage(), ""));
        }
    }



    public abstract void call(BaseErrorException error);

}
