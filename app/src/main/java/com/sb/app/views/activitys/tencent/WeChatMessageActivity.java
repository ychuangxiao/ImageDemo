package com.sb.app.views.activitys.tencent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.ilogie.android.library.common.util.ArrayUtils;
import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.di.HasComponent;
import com.sb.app.di.components.BizComponent;
import com.sb.app.di.components.DaggerBizComponent;
import com.sb.app.model.WeChatModel;
import com.sb.app.model.base.BaseMobileModel;
import com.sb.app.views.base.BaseDaggerActivity;
import com.sb.app.views.fragment.PaymentMobileStyleFragment;
import com.sb.app.views.listeners.MobileChangeListener;
import com.sb.app.views.viewgroup.PrimaryDarkIosView;
import com.sb.app.views.viewgroup.PrimaryDarkView;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.ChatGroupRealm;
import com.sb.data.entitys.realm.ContactRealm;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import io.realm.Realm;

public class WeChatMessageActivity extends BaseDaggerActivity implements HasComponent<BizComponent>,
        MobileChangeListener<WeChatModel>, TimePickerDialog.OnTimeSetListener {


    @BindView(R.id.topPrimaryDarkContainer)
    RelativeLayout mRelativeLayout;//头部状态栏容器

    @BindView(R.id.content)
    FrameLayout content;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;


    BizComponent mBizComponent;


    WeChatMessageFragment mWeChatMessageFragment;
    String mWeChatMessageFragmentTag = "WeChatMessageFragment";


    PaymentMobileStyleFragment mPaymentMobileStyleFragment;
    String mobileStyleTag = "PaymentMobileStyleFragment";


    PrimaryDarkView mPrimaryDarkView;
    PrimaryDarkIosView mPrimaryDarkIosView;

    TimePickerDialog mTimePickerDialog;//顶部时间

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
        mWeChatModel.setTopStatusColor(AppConstant.ACTION_20);

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
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


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

        navigation.setSelectedItemId(R.id.navigation_home);
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
                    mergerTopStatus();
                    hideFragment();
                    switch (item.getItemId()) {
                        case R.id.navigation_home:


                            if (mPaymentMobileStyleFragment != null) {

                                showFragment(mPaymentMobileStyleFragment);
                                mPaymentMobileStyleFragment.loadViewData(mWeChatModel);
                            } else {

                                mPaymentMobileStyleFragment = PaymentMobileStyleFragment
                                        .newInstance(mWeChatModel);

                                mPaymentMobileStyleFragment.setMobileChangeListener(new MobileChangeListener<BaseMobileModel>() {
                                    @Override
                                    public void onItemClickListener(BaseMobileModel model) {

                                        //更改了外观设置

                                        mWeChatModel.setMobileType(model.getMobileType());
                                        mWeChatModel.setNetworkSignal(model.getNetworkSignal());
                                        mWeChatModel.setMobileCarrier(model.getMobileCarrier());
                                        mWeChatModel.setNetworkType(model.getNetworkType());
                                        mWeChatModel.setTopTime(model.getTopTime());
                                        mWeChatModel.setDateTimeStyle(model.getDateTimeStyle());
                                        mWeChatModel.setDir(model.getDir());
                                        mWeChatModel.setBatteryAdd(model.getBatteryAdd());
                                        mWeChatModel.setBatteryNum(model.getBatteryNum());
                                        mWeChatModel.setBlueTeeth(model.getBlueTeeth());
                                        mWeChatModel.setLocation(model.getLocation());
                                        mWeChatModel.setBatteryNumBar(model.getBatteryNumBar());
                                        mergerTopStatus();


                                    }
                                });

                                addFragment(R.id.content, mPaymentMobileStyleFragment,
                                        mobileStyleTag);

                            }
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
                                mPaymentMenuFragment.setMobileChangeListener(PaymentActivityV2.this);
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
                                                (PaymentActivityV2.this);
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

    private void mergerTopStatus() {
        //处理头部

        mRelativeLayout.removeAllViews();


        if (mWeChatModel.getTopToolStyle() == AppConstant.ACTION_10) {

            switch (mWeChatModel.getMobileType()) {

                case AppConstant.ACTION_10:
                    //添加顶部标题栏
                    mPrimaryDarkIosView = PrimaryDarkIosView.build(WeChatMessageActivity.this);
                    mPrimaryDarkIosView.binder(mWeChatModel);
                    mRelativeLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onTopDateTimeClick();
                        }
                    });
                    mRelativeLayout.addView(mPrimaryDarkIosView);
                    break;
                case AppConstant.ACTION_20:
                    //添加顶部标题栏
                    mPrimaryDarkView = PrimaryDarkView.build(WeChatMessageActivity.this);
                    mPrimaryDarkView.binder(mWeChatModel);
                    mRelativeLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onTopDateTimeClick();
                        }
                    });
                    mRelativeLayout.addView(mPrimaryDarkView);
                    break;
            }


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
            mPaymentMobileStyleFragment = (PaymentMobileStyleFragment) fragment;

            hideFragment(mPaymentMobileStyleFragment);
        }

     /*


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

    void onTopDateTimeClick() {


        if (mTimePickerDialog == null) {
            Calendar calendar = Calendar.getInstance();

            calendar.setTimeInMillis(mWeChatModel.getTopTime());
            mTimePickerDialog = TimePickerDialog.newInstance(this, calendar.get(Calendar
                    .HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        }

        mTimePickerDialog.show(this.getFragmentManager(), "TimePickerDialog2");
    }

    @Override
    public void onItemClickListener(WeChatModel model) {
        mWeChatModel = model;

        //判断是否为系统

        if (model.getTopToolStyle() == AppConstant.ACTION_10) {
            mToolbar.setVisibility(View.GONE);
        } else if (model.getTopToolStyle() == AppConstant.ACTION_10) {
            mToolbar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(mWeChatModel.getTopTime());
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar
                        .get(Calendar.DAY_OF_MONTH), hourOfDay, minute,
                second);
        mWeChatModel.setTopTime(calendar.getTimeInMillis());

        if (mWeChatModel.getMobileType() == AppConstant.ACTION_20) {
            mPrimaryDarkView.binder(mWeChatModel);
        } else {
            mPrimaryDarkIosView.binder(mWeChatModel);
        }
    }
}
