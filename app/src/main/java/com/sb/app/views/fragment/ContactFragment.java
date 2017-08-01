package com.sb.app.views.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.di.components.BizComponent;
import com.sb.app.views.activitys.tencent.ContactDetailActivity;
import com.sb.app.views.adapters.ContactAdapter;
import com.sb.app.views.base.BaseFragmentDaggerActivity;
import com.sb.app.views.listeners.ContactClickListener;
import com.sb.data.entitys.realm.ContactRealm;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends BaseFragmentDaggerActivity implements ContactClickListener<ContactRealm> {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    @Inject
    ContactAdapter mContactAdapter;

    private String mParam1;
    private String mParam2;

    @BindView(R.id.recyclerList)
    RecyclerView recyclerList;

    Realm mRealm;


    public ContactFragment() {
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

    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
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

        }
    }


    List<ContactRealm> mContactRealms;
    RealmResults<ContactRealm> contactRealmRealmResults;

    public void loadData() {

        contactRealmRealmResults = mRealm.where(ContactRealm.class).equalTo("isMe", false).findAll();

        mContactRealms.clear();
        for (ContactRealm contactRealm : contactRealmRealmResults) {
            mContactRealms.add(contactRealm);
        }


        mContactRealms.add(0, null);

        mContactRealms.add(null);
        mContactAdapter.setContactRealms(mContactRealms);
    }

    @Override
    public void initView() {

        mContactRealms = new ArrayList<>();


        mBaseRecyclerView = recyclerList;

        mContactAdapter.setContactClickListener(this);


        initLinearLayoutRecyclerView(new LinearLayoutManager(getActivity()));


        mBaseRecyclerView.setAdapter(mContactAdapter);

        loadData();

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_contact;
    }


    @Override
    public void onItemClickListener(ContactRealm model) {
        Intent intent = new Intent(getActivity(), ContactDetailActivity.class);


        intent.putExtra(AppConstant.EXTRA_NO, model.getUserId());

        navigateActivity(intent);

    }

    @Override
    public void onResume() {
        super.onResume();

        loadData();
    }
}
