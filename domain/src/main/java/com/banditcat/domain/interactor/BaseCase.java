package com.banditcat.domain.interactor;


import io.reactivex.Observable;

/**
 * 文件名称：{@link BaseCase}
 * <br/>
 * 功能描述：基类用例抽象类
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：2017/4/18 16:49
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：2017/4/18 16:49
 * <br/>
 * 修改备注：
 */

public abstract class BaseCase<T> {
    public abstract Observable<T> buildObservable();

    public Observable<T> execute() {
        return buildObservable();
    }
}