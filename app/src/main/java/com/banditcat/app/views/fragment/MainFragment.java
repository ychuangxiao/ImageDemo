package com.banditcat.app.views.fragment;


import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RadioGroup;

import com.banditcat.app.R;
import com.banditcat.app.model.menu.HomeHandleModel;
import com.banditcat.app.views.activitys.ali.PaymentActivity;
import com.banditcat.app.views.activitys.tencent.WeChatActivity;
import com.banditcat.app.views.adapters.MainAdapter;
import com.banditcat.app.views.base.BaseFragment;
import com.banditcat.app.views.listeners.RecyclerClickListener;
import com.banditcat.common.fontawesom.typeface.BaseFontAwesome;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment implements RecyclerClickListener<HomeHandleModel> {

    @BindView(R.id.recycle_list)
    RecyclerView mRecyclerView;//列表控件


    MainAdapter mMainAdapter;


    @BindView(R.id.tvVideo)
    AppCompatTextView tvVideo;

    @BindView(R.id.tvFavourable)
    AppCompatTextView tvFavourable;


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


        mBaseRecyclerView = mRecyclerView;

        mMainAdapter = new MainAdapter(getActivity());


        initLinearLayoutRecyclerView(new GridLayoutManager(getActivity(), 2)).setAdapter
                (mMainAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL);

        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable
                .bg_decoration_bottom));
        mBaseRecyclerView.addItemDecoration(dividerItemDecoration);
        mMainAdapter.setRecyclerClickListener(this);


        Collection<HomeHandleModel> collection = new ArrayList<>();

        collection.add(new HomeHandleModel("微信", R.color.silver, BaseFontAwesome.Icon
                .icon_weixin, 10, true));
        collection.add(new HomeHandleModel("支付宝", R.color.md_light_blue_A400, BaseFontAwesome
                .Icon.icon_ali, 20, true));


        mMainAdapter.setItems(collection);

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


    @Override
    public void onItemClickListener(HomeHandleModel model) {

        if (model.getEnable()) {
            switch (model.getHandleIndex()) {
                case 10:

                    navigateActivity(new Intent(getActivity(), WeChatActivity.class));
                    break;
                case 20:
                    navigateActivity(new Intent(getActivity(), PaymentActivity.class));
                    break;
            }
        }


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
