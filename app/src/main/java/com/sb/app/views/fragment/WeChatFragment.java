package com.sb.app.views.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sb.common.utils.ArrayUtils;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.di.components.BizComponent;
import com.sb.app.views.activitys.tencent.ContactDetailActivity;
import com.sb.app.views.activitys.tencent.WeChatMessageActivity;
import com.sb.app.views.adapters.ContactAdapter;
import com.sb.app.views.adapters.WeChatHomeAdapter;
import com.sb.app.views.base.BaseFragmentDaggerActivity;
import com.sb.app.views.listeners.ContactClickListener;
import com.sb.app.views.listeners.RecyclerClickListener;
import com.sb.data.entitys.realm.ChatGroupRealm;
import com.sb.data.entitys.realm.ContactRealm;
import com.sb.data.entitys.realm.WebChatMessageRealm;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeChatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeChatFragment extends BaseFragmentDaggerActivity implements RecyclerClickListener<ChatGroupRealm> {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    @Inject
    WeChatHomeAdapter mWeChatHomeAdapter;

    private String mParam1;
    private String mParam2;

    @BindView(R.id.recyclerList)
    RecyclerView recyclerList;

    Realm mRealm;


    public WeChatFragment() {
        mRealm = Realm.getDefaultInstance();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactFragment.
     */

    public static WeChatFragment newInstance(String param1, String param2) {
        WeChatFragment fragment = new WeChatFragment();
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
        this.getComponent(BizComponent.class).inject(this);
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void DestroyView() {

        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();

            mChatGroupRealms.removeAllChangeListeners();

        }
    }


    RealmResults<ChatGroupRealm> mChatGroupRealms;

    @Override
    public void initView() {


        mChatGroupRealms = mRealm.where(ChatGroupRealm.class).findAll();


        mChatGroupRealms.addChangeListener(new RealmChangeListener<RealmResults<ChatGroupRealm>>() {
            @Override
            public void onChange(RealmResults<ChatGroupRealm> chatGroupRealms) {
                chatGroupRealms.size();
            }
        });


        mBaseRecyclerView = recyclerList;

        mWeChatHomeAdapter.setRecyclerClickListener(this);


        initLinearLayoutRecyclerView(new LinearLayoutManager(getActivity()));

        mBaseRecyclerView.setAdapter(mWeChatHomeAdapter);


        final RealmResults<ContactRealm> contactRealm = mRealm.where(ContactRealm.class).findAll();

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                long count = realm.where(ChatGroupRealm.class).count();


                if (count < 1) {
                    ChatGroupRealm chatGroupRealm = realm.createObject(ChatGroupRealm.class, UUID.randomUUID()
                            .toString());


                    chatGroupRealm.setPay(true);
                    chatGroupRealm.setTopFlag(1);
                    chatGroupRealm.setGroupName("微信支付");


                    if (contactRealm.where().equalTo("isMe", false).count() > 1) {
                        //默认添加好友的聊天
                        chatGroupRealm = realm.createObject(ChatGroupRealm.class, UUID.randomUUID()
                                .toString());

                        chatGroupRealm.setContactRealms(new RealmList<ContactRealm>());
                        chatGroupRealm.getContactRealms().add(contactRealm.where().equalTo("isMe", false).findFirst());
                        chatGroupRealm.getContactRealms().add(contactRealm.where().equalTo("isMe", true).findFirst());
                        chatGroupRealm.setGroupName(contactRealm.where().equalTo("isMe", false).findFirst()
                                .getUserNick());
                        chatGroupRealm.setGroupChatCount(100);

                    }
                }


            }
        });


        loadData();


    }

    public void loadData() {


        mWeChatHomeAdapter.setItems(mChatGroupRealms, true);

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_contact;
    }


    @Override
    public void onItemClickListener(ChatGroupRealm model) {

        Intent intent;
        if (model.isPay()) {

        } else {
            intent = new Intent(getActivity(), WeChatMessageActivity.class);
            intent.putExtra(AppConstant.EXTRA_NO, model.getId());
            navigateActivity(intent);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        mWeChatHomeAdapter.setItems(mChatGroupRealms, true);
    }
}
