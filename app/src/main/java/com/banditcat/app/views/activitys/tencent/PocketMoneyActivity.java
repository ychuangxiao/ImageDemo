package com.banditcat.app.views.activitys.tencent;

import com.banditcat.app.R;
import com.banditcat.app.views.base.BaseActivity;


/**
 * 文件名称：{@link PocketMoneyActivity}
 * <br/>
 * 功能描述： 零钱
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：2017/7/19 09:53
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：2017/7/19 09:53
 * <br/>
 * 修改备注：
 */
public class PocketMoneyActivity extends BaseActivity {

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {
        setToolTitle(getString(R.string.title_activity_pocket_money)).setDisplayHome(true)
                .setHomeOnClickListener();
    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_pocket_money;
    }

}
