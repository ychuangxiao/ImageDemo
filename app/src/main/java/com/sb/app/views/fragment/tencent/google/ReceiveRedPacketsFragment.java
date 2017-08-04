package com.sb.app.views.fragment.tencent.google;


import android.os.Bundle;

import com.sb.app.R;
import com.sb.app.views.base.BaseFragmentDaggerActivity;

public class ReceiveRedPacketsFragment extends BaseFragmentDaggerActivity {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    public ReceiveRedPacketsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReceiveRedPacketsFragment.
     */

    public static ReceiveRedPacketsFragment newInstance(String param1, String param2) {
        ReceiveRedPacketsFragment fragment = new ReceiveRedPacketsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void DestroyView() {

    }

    @Override
    public void initView() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_receive_red_packets;
    }


}
