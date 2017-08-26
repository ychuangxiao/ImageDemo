package com.sb.app.views.fragment.tencent.google;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.views.base.BaseFragment;
import com.sb.app.views.widget.ClearEditText;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.ChatGroupRealm;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import io.realm.Realm;


public class SetMessageContentFragment extends BaseFragment {

    Realm mRealm;

    ChatGroupRealm mChatGroupRealm;

    String mGroupId;
    @BindView(R.id.etCount)
    ClearEditText mEtCount;

    public SetMessageContentFragment() {
        mRealm = Realm.getDefaultInstance();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PaymentMenuFragment.
     */

    public static SetMessageContentFragment newInstance(String gourpId) {
        SetMessageContentFragment fragment = new SetMessageContentFragment();

        Bundle args = new Bundle();
        args.putString(AppConstant.EXTRA_NO, gourpId);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mGroupId = getArguments().getString(AppConstant.EXTRA_NO);

        }
    }

    @Override
    protected void DestroyView() {

    }

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {


        loadViewData(mGroupId);

    }


    public void loadViewData(String groupId) {

        mChatGroupRealm = mRealm.where(ChatGroupRealm.class).equalTo(TextConstant.TABLE_COLUMN_ID, groupId).findFirst();
        mEtCount.setText(String.valueOf(mChatGroupRealm.getGroupChatCount()));

    }


    @OnTextChanged(value = R.id.etCount, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterMessageCountTextChanged(final Editable s) {
        if (TextUtils.isEmpty(s.toString())) {
            return;
        }

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mChatGroupRealm.setGroupChatCount(Integer.parseInt(s.toString()));
            }
        });

    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_set_message_content;
    }


}
