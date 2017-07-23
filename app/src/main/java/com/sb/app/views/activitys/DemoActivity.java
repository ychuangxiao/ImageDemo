package com.sb.app.views.activitys;

import com.sb.app.R;
import com.sb.app.views.base.BaseActivity;

import butterknife.OnClick;

public class DemoActivity extends BaseActivity {

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {

    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_demo;
    }


    @OnClick(R.id.etLocation)
    void onLocationClick()
    {

    }

}
