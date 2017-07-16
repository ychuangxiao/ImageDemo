package com.banditcat.app.views.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;

import com.banditcat.app.BuildConfig;
import com.banditcat.app.R;
import com.banditcat.app.constant.AppConstant;
import com.banditcat.app.utils.ViewUtils;
import com.banditcat.app.views.base.BaseFragment;
import com.banditcat.app.views.webview.ProtocolWebViewActivity;
import com.banditcat.common.fontawesom.typeface.BaseFontAwesome;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoreFragment extends BaseFragment {


    @BindView(R.id.tvCurrentVersion)
    AppCompatTextView tvCurrentVersion;


    @BindView(R.id.tvSuggest)
    AppCompatTextView tvSuggest;


    @BindView(R.id.tvQQ)
    AppCompatTextView tvQQ;

    @BindView(R.id.tvQQ2)
    AppCompatTextView tvQQ2;

    @BindView(R.id.tvQQ3)
    AppCompatTextView tvQQ3;

    @BindView(R.id.tvProtocol)
    AppCompatTextView tvProtocol;

    @BindView(R.id.tvCache)
    AppCompatTextView tvCache;





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
        ViewUtils.setCompoundRightDrawables(getContext(), tvSuggest, BaseFontAwesome.Icon.icon_right, getResources()
                .getColor(R.color
                        .colorRightTitle), 4f);
        ViewUtils.setCompoundRightDrawables(getContext(), tvQQ, BaseFontAwesome.Icon.icon_right, getResources()
                .getColor(R.color
                        .colorRightTitle), 4f);

        ViewUtils.setCompoundRightDrawables(getContext(), tvQQ2, BaseFontAwesome.Icon.icon_right, getResources()
                .getColor(R.color
                        .colorRightTitle), 4f);

        ViewUtils.setCompoundRightDrawables(getContext(), tvQQ3, BaseFontAwesome.Icon.icon_right, getResources()
                .getColor(R.color
                        .colorRightTitle), 4f);
        ViewUtils.setCompoundRightDrawables(getContext(), tvProtocol, BaseFontAwesome.Icon.icon_right, getResources()
                .getColor(R.color
                        .colorRightTitle), 4f);
        ViewUtils.setCompoundRightDrawables(getContext(), tvCache, BaseFontAwesome.Icon.icon_right, getResources()
                .getColor(R.color
                        .colorRightTitle), 4f);


        tvCurrentVersion.setText(String.format(tvCurrentVersion.getTag().toString(), BuildConfig.VERSION_NAME));
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


    @OnClick(R.id.protocolRelativeLayout)
    void onProtocolClick() {
        Intent intent = new Intent(getActivity(), ProtocolWebViewActivity.class);
        intent.putExtra(AppConstant.EXTRA_NO, "http://ssss.cxylm.net/service/");
        navigateActivity(intent);
    }

    @OnClick(R.id.customerServicesRelativeLayout)
    void onQQClick() {

        String qqUrl = String.format("mqqwpa://im/chat?chat_type=wpa&uin=%s&version=1", tvQQ.getText().toString()
                .trim());
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
    }

    @OnClick(R.id.customerServicesRelativeLayout2)
    void onQQ2Click() {

        String qqUrl = String.format("mqqwpa://im/chat?chat_type=wpa&uin=%s&version=1", tvQQ2.getText().toString()
                .trim());
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
    }

    @OnClick(R.id.customerServicesRelativeLayout3)
    void onQQ3Click() {

        String qqUrl = String.format("mqqwpa://im/chat?chat_type=wpa&uin=%s&version=1", tvQQ3.getText().toString()
                .trim());
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
    }


}
