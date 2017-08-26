package com.sb.app.views.activitys.tencent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sb.common.utils.ArrayUtils;
import com.sb.common.utils.StringUtils;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.di.HasComponent;
import com.sb.app.di.components.BizComponent;
import com.sb.app.di.components.DaggerBizComponent;
import com.sb.app.model.WeChatModel;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.base.BaseDaggerActivity;
import com.sb.app.views.fragment.MobileStyleForDatabaseFragment;
import com.sb.app.views.fragment.tencent.google.SetMessageContentFragment;
import com.sb.app.views.fragment.tencent.google.WeChatMessageFragment;
import com.sb.app.views.fragment.tencent.ios.WeChatMessageIosFragment;
import com.sb.app.views.listeners.MobileChangeListener;
import com.sb.app.views.viewgroup.PrimaryDarkIosView;
import com.sb.app.views.viewgroup.PrimaryDarkView;
import com.sb.common.fontawesom.typeface.BaseFontAwesome;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.ChatGroupRealm;
import com.sb.data.entitys.realm.ContactRealm;
import com.sb.data.entitys.realm.MobileStyleRealm;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;

public class WeChatMessageActivity extends BaseDaggerActivity implements HasComponent<BizComponent>,
        MobileChangeListener<MobileStyleRealm>, TimePickerDialog.OnTimeSetListener {


    @BindView(R.id.topPrimaryDarkContainer)
    RelativeLayout mRelativeLayout;//头部状态栏容器

    @BindView(R.id.content)
    FrameLayout content;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;


    @BindView(R.id.iosBackContainer)
    LinearLayout iosBackContainer;
    @BindView(R.id.androidBackContainer)
    LinearLayout androidBackContainer;

    @BindView(R.id.ivSetting)
    AppCompatImageView ivSetting;

    @BindView(R.id.tvTopMessageCount)
    AppCompatTextView tvTopMessageCount;


    @BindView(R.id.watermarkImageView)
    AppCompatImageView watermarkImageView;


    BizComponent mBizComponent;
    MobileStyleRealm mMobileStyleRealm;
    WeChatModel mWeChatModel;

    WeChatMessageFragment mWeChatMessageFragment;
    String mWeChatMessageFragmentTag = "WeChatMessageFragment";


    WeChatMessageIosFragment mWeChatMessageIosFragment;

    String mWeChatMessageIosFragmentTag = "WeChatMessageIosFragment";

    MobileStyleForDatabaseFragment mMobileStyleForDatabaseFragment;
    String mobileStyleTag = "MobileStyleForDatabaseFragment";


    SetMessageContentFragment mSetMessageContentFragment;//内容设置
    String setMessageContentFragmentTag = "SetMessageContentFragment";


    PrimaryDarkView mPrimaryDarkView;
    PrimaryDarkIosView mPrimaryDarkIosView;

    TimePickerDialog mTimePickerDialog;//顶部时间

    Realm mRealm;
    ChatGroupRealm chatGroupRealm = null;

    ContactRealm otherContactRealm = null;

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
        setLeftMenu();

        // 必须得加上否则显示不出效果 可以通过这个在以后设置显示或隐藏
        setProgressBarIndeterminateVisibility(true);
        chatGroupRealm = mRealm.where(ChatGroupRealm.class).equalTo(TextConstant
                .COLUMN_NAME_FOR_ID, groupId).findFirst();

        if (chatGroupRealm == null || ArrayUtils.isEmpty(chatGroupRealm.getContactRealms())) {
            finish();
            return;
        }
        mMobileStyleRealm = mRealm.where(MobileStyleRealm.class).findFirst();


        otherContactRealm = chatGroupRealm.getContactRealms().where().equalTo("isMe", false).findFirst();

        setToolTitle(otherContactRealm.getUserNick());
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (navigation.getSelectedItemId() == R.id.navigation_preview) {

                    navigation.setSelectedItemId(R.id.navigation_mobile_style);
                    navigation.setVisibility(View.VISIBLE);
                    getWindow().clearFlags(
                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
                } else {
                    finish();
                }
            }
        });

        navigation.setSelectedItemId(R.id.navigation_mobile_style);

    }

    MenuItem mMenuItem;


    /**
     * 设置抽屉菜单
     */
    void setLeftMenu() {

        Float size = 10F;
        int color = getResources().getColor(R.color.white);

        //用户
        mMenuItem = navigation.getMenu().findItem(R.id.navigation_mobile_style);
        setMenu(mMenuItem, BaseFontAwesome.Icon.icon_mobile, size);



        //资金账户
        mMenuItem = navigation.getMenu().findItem(R.id.navigation_preview);
        setMenu(mMenuItem, BaseFontAwesome.Icon.icon_brow, size);


        //业务管理
        mMenuItem = navigation.getMenu().findItem(R.id.navigation_content);
        setMenu(mMenuItem, BaseFontAwesome.Icon.icon_content, size);
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


    Fragment fragment;
    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener = new
            BottomNavigationView
                    .OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    mergerTopStatus();
                    hideFragment();
                    switch (item.getItemId()) {
                        case R.id.navigation_mobile_style:

                            watermarkImageView.setVisibility(View.GONE);
                            if (mMobileStyleForDatabaseFragment != null) {

                                showFragment(mMobileStyleForDatabaseFragment);
                                mMobileStyleForDatabaseFragment.loadViewData();
                            } else {

                                mMobileStyleForDatabaseFragment = MobileStyleForDatabaseFragment
                                        .newInstance();

                                mMobileStyleForDatabaseFragment.setMobileChangeListener(WeChatMessageActivity.this);

                                addFragment(R.id.content, mMobileStyleForDatabaseFragment,
                                        mobileStyleTag);

                            }
                            return true;

                        case R.id.navigation_content:

                            watermarkImageView.setVisibility(View.GONE);


                            if (mSetMessageContentFragment != null) {

                                showFragment(mSetMessageContentFragment);
                                mSetMessageContentFragment.loadViewData(groupId);
                            } else {

                                mSetMessageContentFragment = SetMessageContentFragment
                                        .newInstance(groupId);


                                addFragment(R.id.content, mSetMessageContentFragment,
                                        setMessageContentFragmentTag);

                            }

                            return true;

                        case R.id.navigation_preview:


                            if (getApplicationComponent().context()
                                    .sharedpreferences.Watermark().get()) {


                                watermarkImageView.setVisibility(View.VISIBLE);
                            } else {
                                watermarkImageView.setVisibility(View.GONE);
                            }

                            if (mMobileStyleRealm.getTopToolStyle() == TextConstant.TOOL_STYLE_SYSTEM) {
                                getWindow().clearFlags(
                                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                            } else {
                                // 隐藏状态栏
                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                            }


                            navigation.setVisibility(View.GONE);

                            switch (mMobileStyleRealm.getMobileVersion()) {
                                case TextConstant.MOBILE_VERSION_IOS:


                                    if (mWeChatMessageIosFragment != null) {

                                        showFragment(mWeChatMessageIosFragment);
                                        mWeChatMessageIosFragment.loadViewData(mWeChatModel);
                                    } else {
                                        mWeChatMessageIosFragment = WeChatMessageIosFragment.newInstance
                                                (mWeChatModel, navigation);
                                        addFragment(R.id.content, mWeChatMessageIosFragment,
                                                mWeChatMessageIosFragmentTag);

                                    }

                                    break;
                                case TextConstant.MOBILE_VERSION_ANDROID_4:

                                    if (mWeChatMessageFragment != null) {

                                        showFragment(mWeChatMessageFragment);
                                        mWeChatMessageFragment.loadViewData(mWeChatModel);


                                    } else {

                                        mWeChatMessageFragment = WeChatMessageFragment
                                                .newInstance(mWeChatModel, navigation);
                                        addFragment(R.id.content, mWeChatMessageFragment,
                                                mWeChatMessageFragmentTag);


                                    }

                                    break;
                            }


                            return true;
                    }


                    return false;
                }

            };

    private void mergerTopStatus() {
        mRelativeLayout.removeAllViews();

        ViewGroup.LayoutParams params;

        params = mToolbar.getLayoutParams();


        if (mMobileStyleRealm.getTopToolStyle() == TextConstant.TOOL_STYLE_CUSTOMER) {


            switch (mMobileStyleRealm.getMobileVersion()) {

                case TextConstant.MOBILE_VERSION_IOS:
                    //添加顶部标题栏
                    mPrimaryDarkIosView = PrimaryDarkIosView.build(this);
                    mPrimaryDarkIosView.binder(mMobileStyleRealm);
                    mRelativeLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onTopDateTimeClick();
                        }
                    });
                    mRelativeLayout.addView(mPrimaryDarkIosView);


                    break;
                case TextConstant.MOBILE_VERSION_ANDROID_4:
                    //添加顶部标题栏
                    mPrimaryDarkView = PrimaryDarkView.build(this);
                    mPrimaryDarkView.binder(mMobileStyleRealm);
                    mRelativeLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onTopDateTimeClick();
                        }
                    });
                    mRelativeLayout.addView(mPrimaryDarkView);


                    break;
            }

        } else {


            getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        int padding;

        switch (mMobileStyleRealm.getMobileVersion()) {
            case TextConstant.MOBILE_VERSION_IOS:

                mMenuItem.setVisible(true);

                if (chatGroupRealm.getGroupChatCount()>0) {

                    tvTopMessageCount.setText("微信(" + chatGroupRealm.getGroupChatCount() + ")");
                } else {
                    tvTopMessageCount.setText("微信");
                }


                iosBackContainer.setVisibility(View.VISIBLE);
                androidBackContainer.setVisibility(View.GONE);

                params.height = getResources().getDimensionPixelSize(R.dimen.height_top_bar_ios);
                mToolbar.setLayoutParams(params);
                mTitleView.setTextSize(14F);
                padding = ViewUtils.sp2px(this, 6.5F);
                ivSetting.setPadding(padding, padding, padding, padding);

                mTitleView.setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
                mTitleView.setPadding(0, 0, 0, 0);
                break;
            case TextConstant.MOBILE_VERSION_ANDROID_4:

                mMenuItem.setVisible(false);
                params.height = getResources().getDimensionPixelSize(R.dimen.height_top_bar);
                mToolbar.setLayoutParams(params);

                iosBackContainer.setVisibility(View.GONE);
                androidBackContainer.setVisibility(View.VISIBLE);

                mTitleView.setTextSize(18F);
                padding = ViewUtils.sp2px(this, 8.5F);
                ivSetting.setPadding(padding, padding, padding, padding);
                mTitleView.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                mTitleView.setPadding(ViewUtils.sp2px(this, 55F), 0, 0, 0);
                break;
        }

    }


    void hideFragment() {

        fragment = getSupportFragmentManager().findFragmentByTag(mWeChatMessageFragmentTag);

        if (fragment != null) {
            mWeChatMessageFragment = (WeChatMessageFragment) fragment;

            hideFragment(mWeChatMessageFragment);
        }

        fragment = getSupportFragmentManager().findFragmentByTag(mobileStyleTag);

        if (fragment != null) {
            mMobileStyleForDatabaseFragment = (MobileStyleForDatabaseFragment) fragment;

            hideFragment(mMobileStyleForDatabaseFragment);
        }

        fragment = getSupportFragmentManager().findFragmentByTag(mWeChatMessageIosFragmentTag);

        if (fragment != null) {
            mWeChatMessageIosFragment = (WeChatMessageIosFragment) fragment;

            hideFragment(mWeChatMessageIosFragment);
        }

        fragment = getSupportFragmentManager().findFragmentByTag(setMessageContentFragmentTag);

        if (fragment != null) {
            mSetMessageContentFragment = (SetMessageContentFragment) fragment;

            hideFragment(mSetMessageContentFragment);
        }


    }

    void onTopDateTimeClick() {


        if (mTimePickerDialog == null) {
            Calendar calendar = Calendar.getInstance();

            calendar.setTimeInMillis(mMobileStyleRealm.getTopTime());
            mTimePickerDialog = TimePickerDialog.newInstance(this, calendar.get(Calendar
                    .HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        }

        mTimePickerDialog.show(this.getFragmentManager(), "TimePickerDialog2");
    }

    @Override
    public void onItemClickListener(MobileStyleRealm model) {
        mMobileStyleRealm = model;

        mergerTopStatus();
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

        final Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(mMobileStyleRealm.getTopTime());
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar
                        .get(Calendar.DAY_OF_MONTH), hourOfDay, minute,
                second);

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mMobileStyleRealm.setTopTime(calendar.getTimeInMillis());
            }
        });

        if (mMobileStyleRealm.getMobileVersion() == TextConstant.MOBILE_VERSION_ANDROID_4) {
            mPrimaryDarkView.binder(mMobileStyleRealm);
        } else {
            mPrimaryDarkIosView.binder(mMobileStyleRealm);
        }
    }

    @OnClick({R.id.iosBackContainer, R.id.androidBackContainer})
    void onIosBackClick() {
        if (navigation.getSelectedItemId() == R.id.navigation_preview) {

            navigation.setSelectedItemId(R.id.navigation_mobile_style);
            navigation.setVisibility(View.VISIBLE);
            getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            finish();
        }
    }


    @OnClick(R.id.ivSetting)
    void onSettingClick() {
        Intent intent = new Intent(this, ContactDetailActivity.class);


        intent.putExtra(AppConstant.EXTRA_NO, otherContactRealm.getUserId());

        navigateActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mMobileStyleRealm.setTopStatusColor(R.color.colorPrimaryForWeChat);
            }
        });
    }
}
