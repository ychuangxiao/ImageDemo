package com.sb.app.views.fragment;


import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.sb.app.R;
import com.sb.app.model.ScreenModel;
import com.sb.app.model.menu.HomeHandleModel;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.activitys.ali.PaymentActivity;
import com.sb.app.views.activitys.tencent.WeChatActivity;
import com.sb.app.views.adapters.MainAdapter;
import com.sb.app.views.base.BaseFragment;
import com.sb.app.views.listeners.RecyclerClickListener;
import com.sb.common.fontawesom.typeface.BaseFontAwesome;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment {


    MainAdapter mMainAdapter;


    @BindView(R.id.tvVideo)
    AppCompatTextView tvVideo;

    @BindView(R.id.tvFavourable)
    AppCompatTextView tvFavourable;

    @BindView(R.id.tvWeChat)
    AppCompatTextView tvWeChat;

    @BindView(R.id.tvAli)
    AppCompatTextView tvAli;

    @BindView(R.id.handleConstraintLayout)
    ConstraintLayout handleConstraintLayout;


    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PaymentMenuFragment.
     */

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();


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


        ViewUtils.setCompoundTopDrawables(getActivity(), tvWeChat, BaseFontAwesome.Icon
                .icon_weixin, getResources().getColor(R.color.md_green_A400), 24F);

        ViewUtils.setCompoundTopDrawables(getActivity(), tvAli, BaseFontAwesome.Icon
                .icon_ali, getResources().getColor(R.color.md_light_blue_800), 24F);


        ScreenModel screenModel = ViewUtils.getScreen(getActivity());

        ViewGroup.LayoutParams layoutParams = handleConstraintLayout.getLayoutParams();


        layoutParams.height = (int) (screenModel.getScreenWidth() / 2 * 0.85);

        handleConstraintLayout.setLayoutParams(layoutParams);

    }

    @OnClick(R.id.weChatRelativeLayout)
    void onWeChatClick() {

        navigateActivity(new Intent(getActivity(), WeChatActivity.class));
    }

    @OnClick(R.id.aliRelativeLayout)
    void onAliClick() {

        navigateActivity(new Intent(getActivity(), PaymentActivity.class));
    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_main;
    }


    @OnClick(R.id.tvFavourable)
    void onYouHui() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(tvFavourable.getTag().toString());
        intent.setData(content_url);
        startActivity(intent);
    }

    @OnClick(R.id.tvVideo)
    void onYouHui2() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(tvVideo.getTag().toString());
        intent.setData(content_url);
        startActivity(intent);
    }
}