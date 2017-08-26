package com.sb.app.views.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.sb.common.utils.StringUtils;
import com.sb.app.R;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.activitys.tencent.EditWeChatActivity;
import com.sb.app.views.activitys.tencent.money.PurseActivity;
import com.sb.app.views.base.BaseFragment;
import com.sb.data.entitys.realm.ContactRealm;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeChatMeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeChatMeFragment extends BaseFragment {


    Realm mRealm;


    ContactRealm mContactRealm;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.headerImage)
    AppCompatImageView mHeaderImage;
    @BindView(R.id.tvWeChatNick)
    AppCompatTextView mTvWeChatNick;
    @BindView(R.id.tvWeChatNo)
    AppCompatTextView mTvWeChatNo;
    Unbinder unbinder;
    @BindView(R.id.noConstraintLayout)
    ConstraintLayout mNoConstraintLayout;
    Unbinder unbinder1;


    private String mParam1;
    private String mParam2;


    public WeChatMeFragment() {
        mRealm = Realm.getDefaultInstance();
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
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();

        }
    }

    @Override
    public void initView() {
        refreshData();
    }

    public void refreshData() {
        mContactRealm = mRealm.where(ContactRealm.class).equalTo("isMe",
                true).findFirst();

        mTvWeChatNick.setText(mContactRealm.getUserNick());
        mTvWeChatNo.setText(String.format(mTvWeChatNo.getTag().toString(), mContactRealm.getWeChatNo()));


        if (mContactRealm.isSystem()) {
            mHeaderImage.setImageResource(ViewUtils.getDefaultFace()[mContactRealm
                    .getImageIndex()]);
        }
        else if (StringUtils.isNotEmpty(mContactRealm.getImgPath())){
            // 加载本地图片
            File file = new File(mContactRealm.getImgPath());
            Glide.with(this).load(file).into(mHeaderImage);
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_we_chat_me;
    }


    @OnClick(R.id.tvMoney)
    void onMoneyClick() {
        navigateActivity(new Intent(getActivity(), PurseActivity.class));
    }


    @OnClick(R.id.noConstraintLayout)
    void onEditClick() {
        startActivity(new Intent(getActivity(), EditWeChatActivity.class));
    }

    @Override
    public void onResume() {
        super.onResume();

        refreshData();
    }
}
