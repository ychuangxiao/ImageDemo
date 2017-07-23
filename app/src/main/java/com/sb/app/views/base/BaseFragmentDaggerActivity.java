package com.sb.app.views.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * 文件名称：{@link BaseFragmentDaggerActivity}
 * <br/>
 * 功能描述：基类
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：2017/4/18 16:13
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：2017/4/18 16:13
 * <br/>
 * 修改备注：
 */
public abstract class BaseFragmentDaggerActivity extends BaseFragment {




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initInjector();
        initPresenter();
        super.onCreate(savedInstanceState);
    }

    /**
     * 初始化注解
     */
    public abstract void initInjector();


    /**
     * 初始化中间者
     */
    public abstract void initPresenter();




}
