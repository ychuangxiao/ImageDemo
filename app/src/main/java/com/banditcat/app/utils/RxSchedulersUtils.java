package com.banditcat.app.utils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * 文件名称：{@link RxSchedulersUtils}
 * <br/>
 * 功能描述：
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：2017/4/20 17:34
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：2017/4/20 17:34
 * <br/>
 * 修改备注：
 */
public class RxSchedulersUtils {

    public static <T> ObservableTransformer<T, T> MainIOThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
