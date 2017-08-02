package com.sb.app.views.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sb.app.AndroidApplication;
import com.sb.app.R;
import com.sb.app.di.HasComponent;
import com.sb.app.di.components.ApplicationComponent;
import com.sb.app.di.components.BizComponent;
import com.sb.app.views.adapters.ChatContactAdapter;
import com.sb.app.views.listeners.RecyclerClickListener;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.ChatGroupRealm;
import com.sb.data.entitys.realm.ContactRealm;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by admin on 2017/8/1.
 */

public class BottomSheetUserFragment extends BottomSheetDialogFragment implements RecyclerClickListener<ContactRealm> {
    Unbinder mUnbinder;


    Realm mRealm;

    @Inject
    ChatContactAdapter mChatContactAdapter;


    @BindView(R.id.recyclerList)
    RecyclerView recyclerList;


    public BottomSheetUserFragment() {
        mRealm = Realm.getDefaultInstance();
    }


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    RealmList<ContactRealm> mContactRealms;

    public static BottomSheetUserFragment newInstance(String param1, String param2) {
        BottomSheetUserFragment fragment = new BottomSheetUserFragment();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getComponent(BizComponent.class).inject(this);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    public void refreshData(String groupId, String defaultUserId) {
        mContactRealms = mRealm.where(ChatGroupRealm.class).equalTo(TextConstant.TABLE_COLUMN_ID, groupId).findFirst
                ().getContactRealms();

        mChatContactAdapter.setDefaultUserId(defaultUserId);
        mChatContactAdapter.setItems(mContactRealms, true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {

        View mRootView = inflater.inflate(R.layout.fragment_contact, container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);//绑定framgent

        mChatContactAdapter.setOnChooseUserItemClickListener(this);


        recyclerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerList.setHasFixedSize(true);
        // 设置item动画
        recyclerList.setItemAnimator(new DefaultItemAnimator());
        recyclerList.setAdapter(mChatContactAdapter);
        refreshData(mParam1, mParam2);
        return mRootView;
    }


    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }


    /**
     * Get the Main Application component for dependency injection.
     */
    protected ApplicationComponent getApplicationComponent(Context context) {
        return ((AndroidApplication) context).getApplicationComponent();
    }

    @Override
    public void onItemClickListener(ContactRealm model) {


        if (onChooseUserItemClickListener != null) {
            onChooseUserItemClickListener.onItemClickListener(model);
        }
        this.dismiss();
    }


    RecyclerClickListener<ContactRealm> onChooseUserItemClickListener;


    public void setOnChooseUserItemClickListener(RecyclerClickListener<ContactRealm> onChooseUserItemClickListener) {
        this.onChooseUserItemClickListener = onChooseUserItemClickListener;
    }
}
