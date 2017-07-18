package com.banditcat.app.views.activitys.tencent;

import com.banditcat.app.R;
import com.banditcat.app.views.base.BaseActivity;

public class PurseActivity extends BaseActivity {


    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {
        setToolTitle(getString(R.string.title_activity_purse)).setDisplayHome(true)
                .setHomeOnClickListener();
    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_purse;
    }

}
