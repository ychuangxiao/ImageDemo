package com.sb.app.views.activitys.tencent;

import com.sb.app.R;
import com.sb.app.views.base.BaseFragment;


/**
 * 文件名称：{@link PocketMoneyActivityFragment}
 * <br/>
 * 功能描述： 零钱（Android）
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：2017/7/19 09:53
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：2017/7/19 09:53
 * <br/>
 * 修改备注：
 */
public class PocketMoneyActivityFragment extends BaseFragment {

    public PocketMoneyActivityFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PocketMoneyActivityFragment.
     */
    public static PocketMoneyActivityFragment newInstance() {
        PocketMoneyActivityFragment fragment = new PocketMoneyActivityFragment();
        return fragment;
    }

    @Override
    protected void DestroyView() {

    }

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
        return R.layout.fragment_pocket_money;
    }
}
