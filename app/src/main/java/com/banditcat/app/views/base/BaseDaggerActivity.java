package com.banditcat.app.views.base;

import android.os.Bundle;


/**
 * 文件名称：{@link BaseDaggerActivity}
 * <br/>
 * 功能描述：基类
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：2017/4/18 16:13
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：2017/4/18 16:13
 * <br/>
 * 修改备注：
 */
public abstract class BaseDaggerActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
