package com.sb.app.views.activitys.tencent.money;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.di.HasComponent;
import com.sb.app.di.components.BizComponent;
import com.sb.app.di.components.DaggerBizComponent;
import com.sb.app.views.base.BaseDaggerActivity;
import com.sb.app.views.fragment.MobileStyleForDatabaseFragment;
import com.sb.app.views.fragment.tencent.google.PocketMoneyFragment;
import com.sb.app.views.fragment.tencent.ios.PocketMoneyIosFragment;
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


/**
 * 文件名称：{@link PocketMoneyActivity}
 * <br/>
 * 功能描述： 零钱
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：2017/7/19 09:53
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：2017/7/19 09:53
 * <br/>
 * 修改备注：
 */
public class PocketMoneyActivity extends BaseDaggerActivity implements HasComponent<BizComponent>,
        MobileChangeListener<MobileStyleRealm>, TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.topPrimaryDarkContainer)
    RelativeLayout mRelativeLayout;//头部状态栏容器

    @BindView(R.id.content)
    FrameLayout content;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;


    @BindView(R.id.iosBackContainer)
    RelativeLayout iosBackContainer;
    @BindView(R.id.androidBackContainer)
    RelativeLayout androidBackContainer;


    @BindView(R.id.watermarkImageView)
    AppCompatImageView watermarkImageView;


    MobileStyleForDatabaseFragment mMobileStyleForDatabaseFragment;
    String mobileStyleTag = "MobileStyleForDatabaseFragment";
    PrimaryDarkView mPrimaryDarkView;
    PrimaryDarkIosView mPrimaryDarkIosView;
    TimePickerDialog mTimePickerDialog;//顶部时间

    PocketMoneyFragment mPocketMoneyFragment;
    String mPocketMoneyTag = "PocketMoneyFragment";

    PocketMoneyIosFragment mPocketMoneyIosFragment;
    String mPocketMoneyIosTag = "PocketMoneyIosFragment";


    BizComponent mBizComponent;
    ContactRealm mContactRealm;

    Realm mRealm;

    MobileStyleRealm mMobileStyleRealm;
    @BindView(R.id.topTitleIos)
    TextView mTopTitleIos;

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

        mRealm = Realm.getDefaultInstance();


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


        navigation.setSelectedItemId(R.id.navigation_mobile_style);
        setLeftMenu();
    }


    /**
     * 设置抽屉菜单
     */
    void setLeftMenu() {

        Float size = 10F;


        MenuItem menuItem = navigation.getMenu().findItem(R.id.navigation_mobile_style);
        setMenu(menuItem, BaseFontAwesome.Icon.icon_mobile, size);


        menuItem = navigation.getMenu().findItem(R.id.navigation_preview);
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
                        case R.id.navigation_mobile_style:
                            watermarkImageView.setVisibility(View.GONE);

                            if (mMobileStyleForDatabaseFragment != null) {

                                showFragment(mMobileStyleForDatabaseFragment);
                                mMobileStyleForDatabaseFragment.loadViewData();
                            } else {

                                mMobileStyleForDatabaseFragment = MobileStyleForDatabaseFragment
                                        .newInstance();

                                mMobileStyleForDatabaseFragment.setMobileChangeListener
                                        (PocketMoneyActivity.this);

                                addFragment(R.id.content, mMobileStyleForDatabaseFragment,
                                        mobileStyleTag);

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

                                    if (mPocketMoneyIosFragment != null) {

                                        showFragment(mPocketMoneyIosFragment);


                                    } else {

                                        mPocketMoneyIosFragment = PocketMoneyIosFragment
                                                .newInstance(navigation);
                                        addFragment(R.id.content, mPocketMoneyIosFragment,
                                                mPocketMoneyIosTag);

                                        mPocketMoneyIosFragment.setMobileChangeListener
                                                (PocketMoneyActivity.this);
                                    }
                                    break;
                                case TextConstant.MOBILE_VERSION_ANDROID_4:

                                    if (mPocketMoneyFragment != null) {

                                        showFragment(mPocketMoneyFragment);


                                    } else {

                                        mPocketMoneyFragment = PocketMoneyFragment
                                                .newInstance(navigation);
                                        addFragment(R.id.content, mPocketMoneyFragment,
                                                mPocketMoneyTag);

                                        mPocketMoneyFragment.setMobileChangeListener
                                                (PocketMoneyActivity.this);
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

                params.height = getResources().getDimensionPixelSize(R.dimen.height_top_bar_ios);
                mToolbar.setLayoutParams(params);
                mTitleView.setTextSize(14F);
                mTopTitleIos.setText("零钱");

                break;
            case TextConstant.MOBILE_VERSION_ANDROID_4:
                params.height = getResources().getDimensionPixelSize(R.dimen.height_top_bar);
                mToolbar.setLayoutParams(params);
                setToolTitle("零钱");
                iosBackContainer.setVisibility(View.GONE);
                androidBackContainer.setVisibility(View.VISIBLE);
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

        fragment = getSupportFragmentManager().findFragmentByTag(mPocketMoneyTag);

        if (fragment != null) {
            mPocketMoneyFragment = (PocketMoneyFragment) fragment;

            hideFragment(mPocketMoneyFragment);
        }

        fragment = getSupportFragmentManager().findFragmentByTag(mobileStyleTag);

        if (fragment != null) {
            mMobileStyleForDatabaseFragment = (MobileStyleForDatabaseFragment) fragment;

            hideFragment(mMobileStyleForDatabaseFragment);
        }

        fragment = getSupportFragmentManager().findFragmentByTag(mPocketMoneyIosTag);

        if (fragment != null) {
            mPocketMoneyIosFragment = (PocketMoneyIosFragment) fragment;

            hideFragment(mPocketMoneyIosFragment);
        }


    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_pocket_money;
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
        if (navigation.getSelectedItemId() == R.id.navigation_preview) {

            navigation.setSelectedItemId(R.id.navigation_mobile_style);
            navigation.setVisibility(View.VISIBLE);
            getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {


            finish();
        }
    }


}
