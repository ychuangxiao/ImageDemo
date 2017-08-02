package com.sb.app.views.activitys.tencent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.ilogie.android.library.common.util.ArrayUtils;
import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.di.HasComponent;
import com.sb.app.di.components.BizComponent;
import com.sb.app.di.components.DaggerBizComponent;
import com.sb.app.model.RedPackedDetailsModel;
import com.sb.app.model.RedPackedModel;
import com.sb.app.model.WeChatModel;
import com.sb.app.utils.LogUtils;
import com.sb.app.utils.MathUtils;
import com.sb.app.utils.TimeUtils;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.activitys.ali.PaymentActivity;
import com.sb.app.views.base.BaseActivity;
import com.sb.app.views.base.BaseDaggerActivity;
import com.sb.app.views.fragment.BottomSheetUserFragment;
import com.sb.app.views.fragment.PaymentGoogleFragment;
import com.sb.app.views.fragment.PaymentIosFragment;
import com.sb.app.views.fragment.PaymentMenuFragment;
import com.sb.app.views.fragment.PaymentMobileStyleFragment;
import com.sb.app.views.fragment.WeChatFragment;
import com.sb.app.views.listeners.MobileChangeListener;
import com.sb.app.views.listeners.RecyclerClickListener;
import com.sb.app.views.listeners.WeChatMessage2ClickListener;
import com.sb.app.views.viewgroup.ChatFriendMessageItemView;
import com.sb.app.views.viewgroup.ChatFriendRedPacketItemView;
import com.sb.app.views.viewgroup.ChatFriendTransferItemView;
import com.sb.app.views.viewgroup.ChatMeMessageItemView;
import com.sb.app.views.viewgroup.ChatMeRedPacketItemView;
import com.sb.app.views.viewgroup.ChatMeTransferItemView;
import com.sb.app.views.viewgroup.chat.ReceiveRedPacketItemView;
import com.sb.common.fontawesom.typeface.BaseFontAwesome;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.ChatGroupRealm;
import com.sb.data.entitys.realm.ContactRealm;
import com.sb.data.entitys.realm.WebChatMessageRealm;

import java.math.BigDecimal;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;
import butterknife.OnTouch;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;

public class WeChatMessageActivity extends BaseDaggerActivity implements HasComponent<BizComponent>,
        MobileChangeListener<WeChatModel> {



    @BindView(R.id.content)
    FrameLayout content;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;


    BizComponent mBizComponent;


    WeChatMessageFragment mWeChatMessageFragment;
    String mWeChatMessageFragmentTag = "WeChatMessageFragment";

    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();

        }
    }

    @Override
    public BizComponent getComponent() {
        return mBizComponent;
    }


    /**
     * 初始化注解
     */
    @Override
    public void initInjector() {
        this.mBizComponent = DaggerBizComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    /**
     * 初始化中间者
     */
    @Override
    public void initPresenter() {

    }

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {
        injectExtras();
        mRealm = Realm.getDefaultInstance();
        mWeChatModel = new WeChatModel();
        mWeChatModel.setGroupId(groupId);


        // 必须得加上否则显示不出效果 可以通过这个在以后设置显示或隐藏
        setProgressBarIndeterminateVisibility(true);
        ChatGroupRealm chatGroupRealm = mRealm.where(ChatGroupRealm.class).equalTo(TextConstant
                .COLUMN_NAME_FOR_ID, groupId).findFirst();

        if (chatGroupRealm == null || ArrayUtils.isEmpty(chatGroupRealm.getContactRealms())) {
            finish();
            return;
        }


        ContactRealm otherContactRealm = chatGroupRealm.getContactRealms().where().equalTo("isMe", false).findFirst();

        setToolTitle(otherContactRealm.getUserNick()).setDisplayHome(true);


        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (navigation.getSelectedItemId() == R.id.navigation_notifications) {

                    navigation.setSelectedItemId(R.id.navigation_home);
                    navigation.setVisibility(View.VISIBLE);
                     getWindow().clearFlags(
                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
                } else {
                    finish();
                }
            }
        });
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigation.setSelectedItemId(R.id.navigation_notifications);
    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_we_chat_message;
    }

    String groupId;

    private void injectExtras() {
        Bundle extras_ = getIntent().getExtras();
        if (extras_ == null) {

            finish();
            return;
        }

        if (!extras_.containsKey(AppConstant.EXTRA_NO)) {
            finish();
            return;
        }


        groupId = extras_.getString(AppConstant.EXTRA_NO);


        if (StringUtils.isEmpty(groupId)) {
            finish();
            return;
        }


    }


    WeChatModel mWeChatModel;

    Fragment fragment;
    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener = new
            BottomNavigationView
                    .OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    hideFragment();
                    switch (item.getItemId()) {
                        case R.id.navigation_home:


                            /*if (mPaymentMobileStyleFragment != null) {

                                showFragment(mPaymentMobileStyleFragment);
                                mPaymentMobileStyleFragment.loadViewData(aliPaymentModel);
                            } else {

                                mPaymentMobileStyleFragment = PaymentMobileStyleFragment
                                        .newInstance(aliPaymentModel);
                                addFragment(R.id.content, mPaymentMobileStyleFragment,
                                        mobileStyleTag);
                                mPaymentMobileStyleFragment.setMobileChangeListener
                                        (PaymentActivity.this);

                            }*/
                            return true;
                        case R.id.navigation_dashboard:

/*
                            if (mPaymentMenuFragment != null) {

                                showFragment(mPaymentMenuFragment);
                                mPaymentMenuFragment.loadViewData(aliPaymentModel);
                            } else {

                                mPaymentMenuFragment = PaymentMenuFragment.newInstance
                                        (aliPaymentModel);
                                addFragment(R.id.content, mPaymentMenuFragment, paymentMenuTag);
                                mPaymentMenuFragment.setMobileChangeListener(PaymentActivity.this);
                            }*/

                            return true;
                        case R.id.navigation_notifications:

                            // 隐藏状态栏
                            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

                            switch (mWeChatModel.getMobileType()) {
                                case AppConstant.ACTION_10:


                                   /* if (mPaymentIosFragment != null) {

                                        showFragment(mPaymentIosFragment);
                                        mPaymentIosFragment.loadViewData(aliPaymentModel);
                                    } else {
                                        mPaymentIosFragment = PaymentIosFragment.newInstance
                                                (aliPaymentModel, navigation);
                                        addFragment(R.id.content, mPaymentIosFragment,
                                                paymentIosTag);
                                        mPaymentIosFragment.setMobileChangeListener
                                                (PaymentActivity.this);
                                    }*/

                                    break;
                                case AppConstant.ACTION_20:

                                    if (mWeChatMessageFragment != null) {

                                        showFragment(mWeChatMessageFragment);
                                        mWeChatMessageFragment.loadViewData(mWeChatModel);


                                    } else {

                                        mWeChatMessageFragment = WeChatMessageFragment
                                                .newInstance(mWeChatModel, navigation);
                                        addFragment(R.id.content, mWeChatMessageFragment,
                                                mWeChatMessageFragmentTag);

                                        mWeChatMessageFragment.setMobileChangeListener(WeChatMessageActivity.this);
                                    }

                                    break;
                            }


                            return true;
                    }
                    return false;
                }

            };


    void hideFragment() {

        fragment = getSupportFragmentManager().findFragmentByTag(mWeChatMessageFragmentTag);

        if (fragment != null) {
            mWeChatMessageFragment = (WeChatMessageFragment) fragment;

            hideFragment(mWeChatMessageFragment);
        }



     /*   fragment = getSupportFragmentManager().findFragmentByTag(mobileStyleTag);

        if (fragment != null) {
            mPaymentMobileStyleFragment = (PaymentMobileStyleFragment) fragment;

            hideFragment(mPaymentMobileStyleFragment);
        }


        fragment = getSupportFragmentManager().findFragmentByTag(paymentIosTag);

        if (fragment != null) {
            mPaymentIosFragment = (PaymentIosFragment) fragment;

            hideFragment(mPaymentIosFragment);
        }


        fragment = getSupportFragmentManager().findFragmentByTag(paymentTag);

        if (fragment != null) {
            mPaymentGoogleFragment = (PaymentGoogleFragment) fragment;

            hideFragment(mPaymentGoogleFragment);
        }


        fragment = getSupportFragmentManager().findFragmentByTag(paymentMenuTag);

        if (fragment != null) {
            mPaymentMenuFragment = (PaymentMenuFragment) fragment;

            hideFragment(mPaymentMenuFragment);
        }*/
    }

    @Override
    public void onItemClickListener(WeChatModel model) {
        mWeChatModel = model;

        //判断是否为系统

        if (model.getTopToolStyle() == AppConstant.ACTION_20) {
            mToolbar.setVisibility(View.GONE);
        } else if (model.getTopToolStyle() == AppConstant.ACTION_10) {
            mToolbar.setVisibility(View.VISIBLE);
        }
    }
}
