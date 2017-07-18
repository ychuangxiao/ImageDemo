package com.banditcat.app.views.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.banditcat.app.R;
import com.banditcat.app.views.activitys.tencent.PurseActivity;
import com.banditcat.app.views.base.BaseFragment;

import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeChatMeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeChatMeFragment extends BaseFragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    public WeChatMeFragment() {
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

    public static WeChatMeFragment newInstance(String param1, String param2) {
        WeChatMeFragment fragment = new WeChatMeFragment();
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
        return R.layout.fragment_we_chat_me;
    }


    @OnClick(R.id.tvMoney)
    void onMoneyClick() {
        navigateActivity(new Intent(getActivity(), PurseActivity.class));
    }
}
