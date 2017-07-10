package com.banditcat.app.views.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.banditcat.app.R;
import com.banditcat.app.views.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoreFragment extends BaseFragment {

    public MoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MoreFragment.
     */
    public static MoreFragment newInstance() {
        MoreFragment fragment = new MoreFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        return R.layout.fragment_more;
    }



}
