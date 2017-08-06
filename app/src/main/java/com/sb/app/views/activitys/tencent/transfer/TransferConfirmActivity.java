package com.sb.app.views.activitys.tencent.transfer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.makeramen.roundedimageview.RoundedImageView;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.di.HasComponent;
import com.sb.app.di.components.BizComponent;
import com.sb.app.di.components.DaggerBizComponent;
import com.sb.app.model.RedPackedDetailsModel;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.activitys.tencent.RedPacketsDetailActivity;
import com.sb.app.views.base.BaseDaggerActivity;
import com.sb.app.views.fragment.MobileStyleForDatabaseFragment;
import com.sb.app.views.fragment.tencent.google.TransferConfirmDetailsFragment;
import com.sb.app.views.fragment.tencent.ios.TransferConfirmDetailsIosFragment;
import com.sb.app.views.listeners.MobileChangeListener;
import com.sb.app.views.viewgroup.PrimaryDarkIosView;
import com.sb.app.views.viewgroup.PrimaryDarkView;
import com.sb.common.fontawesom.typeface.BaseFontAwesome;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.ContactRealm;
import com.sb.data.entitys.realm.MobileStyleRealm;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;

public class TransferConfirmActivity extends BaseDaggerActivity implements HasComponent<BizComponent>,
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


    @BindView(R.id.watermarkImageView)
    AppCompatImageView watermarkImageView;


    MobileStyleForDatabaseFragment mMobileStyleForDatabaseFragment;
    String mobileStyleTag = "MobileStyleForDatabaseFragment";
    PrimaryDarkView mPrimaryDarkView;
    PrimaryDarkIosView mPrimaryDarkIosView;
    TimePickerDialog mTimePickerDialog;//顶部时间

    TransferConfirmDetailsFragment mTransferConfirmDetailsFragment;
    String mTransferConfirmTag = "TransferConfirmDetailsFragment";

    TransferConfirmDetailsIosFragment mTransferConfirmDetailsIosFragment;
    String mTransferConfirmIosTag = "TransferConfirmDetailsIosFragment";

    RedPackedDetailsModel mRedPackedDetailsModel;
    BizComponent mBizComponent;
    ContactRealm mContactRealm;

    Realm mRealm;

    MobileStyleRealm mMobileStyleRealm;

    /**
     * 初始化参数
     */
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


        mRedPackedDetailsModel = extras_.getParcelable(AppConstant.EXTRA_NO);


        if (mRedPackedDetailsModel == null) {
            finish();
            return;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();

        }
    }


    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {
        injectExtras();
        mRealm = Realm.getDefaultInstance();



        mContactRealm = mRealm.where(ContactRealm.class).equalTo(TextConstant.COLUMN_NAME_FOR_USERID_CONTACTREALM,
                mRedPackedDetailsModel.getSendUserId()).findFirst();

        setToolTitle(getString(R.string.title_activity_friend_red_packets_detail));





        // 必须得加上否则显示不出效果 可以通过这个在以后设置显示或隐藏
        setProgressBarIndeterminateVisibility(true);

        mMobileStyleRealm = mRealm.where(MobileStyleRealm.class).findFirst();


        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mMobileStyleRealm.setTopStatusColor(R.color.colorPrimaryForWeChat);
            }
        });

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        navigation.setSelectedItemId(R.id.navigation_home);
        setLeftMenu();
    }


    /**
     * 设置抽屉菜单
     */
    void setLeftMenu() {

        Float size = 10F;


        MenuItem menuItem = navigation.getMenu().findItem(R.id.navigation_home);
        setMenu(menuItem, BaseFontAwesome.Icon.icon_mobile, size);


        menuItem = navigation.getMenu().findItem(R.id.navigation_notifications);
        setMenu(menuItem, BaseFontAwesome.Icon.icon_brow, size);


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
                        case R.id.navigation_home:
                            watermarkImageView.setVisibility(View.GONE);

                            if (mMobileStyleForDatabaseFragment != null) {

                                showFragment(mMobileStyleForDatabaseFragment);
                                mMobileStyleForDatabaseFragment.loadViewData();
                            } else {

                                mMobileStyleForDatabaseFragment = MobileStyleForDatabaseFragment
                                        .newInstance();

                                mMobileStyleForDatabaseFragment.setMobileChangeListener
                                        (TransferConfirmActivity.this);

                                addFragment(R.id.content, mMobileStyleForDatabaseFragment,
                                        mobileStyleTag);

                            }
                            return true;

                        case R.id.navigation_notifications:
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

                                    if (mTransferConfirmDetailsIosFragment != null) {

                                        showFragment(mTransferConfirmDetailsIosFragment);


                                    } else {

                                        mTransferConfirmDetailsIosFragment = TransferConfirmDetailsIosFragment
                                                .newInstance(mRedPackedDetailsModel, navigation);
                                        addFragment(R.id.content, mTransferConfirmDetailsIosFragment,
                                                mTransferConfirmIosTag);

                                        mTransferConfirmDetailsIosFragment.setMobileChangeListener
                                                (TransferConfirmActivity.this);
                                    }
                                    break;
                                case TextConstant.MOBILE_VERSION_ANDROID_4:

                                    if (mTransferConfirmDetailsFragment != null) {

                                        showFragment(mTransferConfirmDetailsFragment);


                                    } else {

                                        mTransferConfirmDetailsFragment = TransferConfirmDetailsFragment
                                                .newInstance(mRedPackedDetailsModel, navigation);
                                        addFragment(R.id.content, mTransferConfirmDetailsFragment,
                                                mTransferConfirmTag);

                                        mTransferConfirmDetailsFragment.setMobileChangeListener
                                                (TransferConfirmActivity.this);
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


        switch (mMobileStyleRealm.getMobileVersion()) {
            case TextConstant.MOBILE_VERSION_IOS:
                iosBackContainer.setVisibility(View.VISIBLE);
                androidBackContainer.setVisibility(View.GONE);

                params.height = ViewUtils.dip2px(this, 30);
                mToolbar.setLayoutParams(params);
                mTitleView.setTextSize(14F);
                setToolTitle("转账详情");
                mTitleView.setGravity(Gravity.CENTER|Gravity.CENTER_VERTICAL);
                mTitleView.setPadding(0,0,0,0);
                break;
            case TextConstant.MOBILE_VERSION_ANDROID_4:
                params.height = ViewUtils.dip2px(this, 48);
                mToolbar.setLayoutParams(params);
                setToolTitle("交易详情");
                iosBackContainer.setVisibility(View.GONE);
                androidBackContainer.setVisibility(View.VISIBLE);

                mTitleView.setTextSize(18F);

                mTitleView.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
                mTitleView.setPadding(ViewUtils.sp2px(this,55F),0,0,0);
                break;
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

    void hideFragment() {

        fragment = getSupportFragmentManager().findFragmentByTag(mTransferConfirmTag);

        if (fragment != null) {
            mTransferConfirmDetailsFragment = (TransferConfirmDetailsFragment) fragment;

            hideFragment(mTransferConfirmDetailsFragment);
        }

        fragment = getSupportFragmentManager().findFragmentByTag(mobileStyleTag);

        if (fragment != null) {
            mMobileStyleForDatabaseFragment = (MobileStyleForDatabaseFragment) fragment;

            hideFragment(mMobileStyleForDatabaseFragment);
        }

        fragment = getSupportFragmentManager().findFragmentByTag(mTransferConfirmIosTag);

        if (fragment != null) {
            mTransferConfirmDetailsIosFragment = (TransferConfirmDetailsIosFragment) fragment;

            hideFragment(mTransferConfirmDetailsIosFragment);
        }


    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_transfer_confirm;
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

    @Override
    public BizComponent getComponent() {
        return mBizComponent;
    }


    /**
     * @param view      The view associated with this listener.
     * @param hourOfDay The hour that was set.
     * @param minute    The minute that was set.
     * @param second    The second that was set
     */
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


    @Override
    public void onItemClickListener(MobileStyleRealm model) {

        mMobileStyleRealm = model;

        mergerTopStatus();

    }

    @OnClick({R.id.iosBackContainer, R.id.androidBackContainer})
    void onIosBackClick() {
        if (navigation.getSelectedItemId() == R.id.navigation_notifications) {

            navigation.setSelectedItemId(R.id.navigation_home);
            navigation.setVisibility(View.VISIBLE);
            getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {

            Intent intent = new Intent();


            finish();
        }
    }

}
