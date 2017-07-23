package com.sb.app.views.fragment;


import android.os.Bundle;

import com.sb.app.R;
import com.sb.app.views.base.BaseFragment;


 /**
  * 文件名称：{@link WeChatFindFragment}
  * <br/>
  * 功能描述： 发现
  * <br/>
  * 创建作者：administrator
  * <br/>
  * 创建时间：2017/7/17 09:32
  * <br/>
  * 修改作者：administrator
  * <br/>
  * 修改时间：2017/7/17 09:32
  * <br/>
  * 修改备注：
  */
public class WeChatFindFragment extends BaseFragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    public WeChatFindFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeChatMeFragment.
     */

    public static WeChatFindFragment newInstance(String param1, String param2) {
        WeChatFindFragment fragment = new WeChatFindFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    protected void DestroyView() {

    }

    @Override
    public void initView() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_we_chat_find;
    }



}
