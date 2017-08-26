package com.sb.app.views.activitys.ali;

import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatImageView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.model.AliPaymentModel;
import com.sb.app.model.BankModel;
import com.sb.app.model.base.BaseMobileModel;
import com.sb.app.utils.TimeUtils;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.base.BaseActivity;
import com.sb.app.views.fragment.PaymentGoogleFragment;
import com.sb.app.views.fragment.PaymentIosFragment;
import com.sb.app.views.fragment.PaymentMenuFragment;
import com.sb.app.views.fragment.PaymentMobileStyleFragment;
import com.sb.app.views.listeners.MobileChangeListener;
import com.sb.app.views.viewgroup.PrimaryDarkIosView;
import com.sb.app.views.viewgroup.PrimaryDarkView;
import com.sb.common.fontawesom.typeface.BaseFontAwesome;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.math.BigDecimal;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

public class PaymentActivity extends BaseActivity implements MobileChangeListener<AliPaymentModel>, TimePickerDialog
        .OnTimeSetListener {


    @BindView(R.id.topPrimaryDarkContainer)
    RelativeLayout mRelativeLayout;//头部状态栏容器

    PrimaryDarkView mPrimaryDarkView;
    PrimaryDarkIosView mPrimaryDarkIosView;
    TimePickerDialog mTimePickerDialog;//顶部时间


    @BindView(R.id.iosBackContainer)
    LinearLayout iosBackContainer;
    @BindView(R.id.androidBackContainer)
    LinearLayout androidBackContainer;


    //以上是新添加


    @BindView(R.id.content)
    FrameLayout content;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @BindView(R.id.watermarkImageView)
    AppCompatImageView watermarkImageView;

    PaymentGoogleFragment mPaymentGoogleFragment;
    String paymentTag = "PaymentGoogleFragment";


    PaymentIosFragment mPaymentIosFragment;

    String paymentIosTag = "PaymentIosFragment";

    Fragment fragment;

    PaymentMenuFragment mPaymentMenuFragment;
    String paymentMenuTag = "PaymentMenuFragment";

    PaymentMobileStyleFragment mPaymentMobileStyleFragment;
    String mobileStyleTag = "PaymentMobileStyleFragment";


    AliPaymentModel aliPaymentModel;
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
                            if (mPaymentMobileStyleFragment != null) {

                                showFragment(mPaymentMobileStyleFragment);
                                mPaymentMobileStyleFragment.loadViewData(aliPaymentModel);
                            } else {

                                mPaymentMobileStyleFragment = PaymentMobileStyleFragment
                                        .newInstance(aliPaymentModel);
                                addFragment(R.id.content, mPaymentMobileStyleFragment,
                                        mobileStyleTag);
                                mPaymentMobileStyleFragment.setMobileChangeListener(new MobileChangeListener<BaseMobileModel>() {
                                    @Override
                                    public void onItemClickListener(BaseMobileModel model) {
                                        //外观设置

                                        aliPaymentModel.setMobileType(model.getMobileType());
                                        aliPaymentModel.setNetworkSignal(model.getNetworkSignal());
                                        aliPaymentModel.setMobileCarrier(model.getMobileCarrier());
                                        aliPaymentModel.setNetworkType(model.getNetworkType());
                                        aliPaymentModel.setTopTime(model.getTopTime());
                                        aliPaymentModel.setDateTimeStyle(model.getDateTimeStyle());
                                        aliPaymentModel.setDir(model.getDir());
                                        aliPaymentModel.setBatteryAdd(model.getBatteryAdd());
                                        aliPaymentModel.setBatteryNum(model.getBatteryNum());
                                        aliPaymentModel.setBlueTeeth(model.getBlueTeeth());
                                        aliPaymentModel.setLocation(model.getLocation());
                                        aliPaymentModel.setBatteryNumBar(model.getBatteryNumBar());
                                        mergerTopStatus();
                                    }
                                });


                            }
                            return true;
                        case R.id.navigation_content:

                            watermarkImageView.setVisibility(View.GONE);
                            if (mPaymentMenuFragment != null) {

                                showFragment(mPaymentMenuFragment);
                                mPaymentMenuFragment.loadViewData(aliPaymentModel);
                            } else {

                                mPaymentMenuFragment = PaymentMenuFragment.newInstance
                                        (aliPaymentModel);
                                addFragment(R.id.content, mPaymentMenuFragment, paymentMenuTag);
                                mPaymentMenuFragment.setMobileChangeListener(PaymentActivity.this);
                            }

                            return true;
                        case R.id.navigation_preview:


                            if (getApplicationComponent().context()
                                    .sharedpreferences.Watermark().get()) {


                                watermarkImageView.setVisibility(View.VISIBLE);
                            } else {
                                watermarkImageView.setVisibility(View.GONE);
                            }
                            if (aliPaymentModel.getTopToolStyle() == AppConstant.ACTION_20) {
                                getWindow().clearFlags(
                                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                            } else {
                                // 隐藏状态栏
                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                            }

                            switch (aliPaymentModel.getMobileType()) {
                                case AppConstant.ACTION_10:


                                    if (mPaymentIosFragment != null) {

                                        showFragment(mPaymentIosFragment);
                                        mPaymentIosFragment.loadViewData(aliPaymentModel);
                                    } else {
                                        mPaymentIosFragment = PaymentIosFragment.newInstance
                                                (aliPaymentModel, navigation);
                                        addFragment(R.id.content, mPaymentIosFragment,
                                                paymentIosTag);
                                        mPaymentIosFragment.setMobileChangeListener
                                                (PaymentActivity.this);
                                    }

                                    break;
                                case AppConstant.ACTION_20:


                                    if (mPaymentGoogleFragment != null) {

                                        showFragment(mPaymentGoogleFragment);
                                        mPaymentGoogleFragment.loadViewData(aliPaymentModel);


                                    } else {

                                        mPaymentGoogleFragment = PaymentGoogleFragment
                                                .newInstance(aliPaymentModel, navigation);
                                        addFragment(R.id.content, mPaymentGoogleFragment,
                                                paymentTag);
                                        mPaymentGoogleFragment.setMobileChangeListener
                                                (PaymentActivity.this);
                                    }


                                    break;
                            }


                            return true;
                    }
                    return false;
                }

            };


    void hideFragment() {

        fragment = getSupportFragmentManager().findFragmentByTag(mobileStyleTag);

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
        }
    }

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {

        setToolTitle(getString(R.string.title_activity_payment)) ;



        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        aliPaymentModel = new AliPaymentModel();
        BankModel bankModel = new BankModel("浦发银行", 120);

        aliPaymentModel.setBankModel(bankModel);

        aliPaymentModel.setTopToolStyle(AppConstant.ACTION_10);
        aliPaymentModel.setTopToolStyle(AppConstant.ACTION_10);
        aliPaymentModel.setMobileType(AppConstant.ACTION_20);
        aliPaymentModel.setNetworkSignal(AppConstant.ACTION_10);
        aliPaymentModel.setNetworkType(AppConstant.ACTION_10);
        aliPaymentModel.setFinish(false);
        aliPaymentModel.setRemark("转账");
        aliPaymentModel.setBankNo("8888");
        aliPaymentModel.setMobileCarrier(AppConstant.CARRIER_CHINA_YD);
        aliPaymentModel.setReceiptUserName(getString(R.string.app_name));
        aliPaymentModel.setPaymentType("中国工商银行(6666)");
        aliPaymentModel.setReceiptMoney(BigDecimal.valueOf(8888888.00));
        aliPaymentModel.setCreateTime(System.currentTimeMillis());
        aliPaymentModel.setPaymentTime(aliPaymentModel.getCreateTime());
        aliPaymentModel.setTopTime(aliPaymentModel.getCreateTime());
        aliPaymentModel.setLastTime(TimeUtils.addHour2(2, aliPaymentModel.getCreateTime()));

        aliPaymentModel.setBatteryAdd(true);
        aliPaymentModel.setBatteryNum(true);
        aliPaymentModel.setBlueTeeth(true);
        aliPaymentModel.setLocation(true);
        aliPaymentModel.setBatteryAdd(true);
        aliPaymentModel.setDir(true);
        aliPaymentModel.setBatteryNumBar(40);

        navigation.setSelectedItemId(R.id.navigation_mobile_style);


        setLeftMenu();

    }


    /**
     * 设置抽屉菜单
     */
    void setLeftMenu() {

        Float size = 10F;
        int color = getResources().getColor(R.color.white);

        //用户
        MenuItem menuItem = navigation.getMenu().findItem(R.id.navigation_mobile_style);
        setMenu(menuItem, BaseFontAwesome.Icon.icon_mobile, size);


        //业务管理
        menuItem = navigation.getMenu().findItem(R.id.navigation_content);
        setMenu(menuItem, BaseFontAwesome.Icon.icon_content, size);

        //资金账户
        menuItem = navigation.getMenu().findItem(R.id.navigation_preview);
        setMenu(menuItem, BaseFontAwesome.Icon.icon_brow, size);


    }


    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_payment;
    }



    @Nullable
    private Boolean createImage() {
        switch (aliPaymentModel.getMobileType()) {
            case AppConstant.ACTION_10:

                if (mPaymentIosFragment == null) {
                    alertMsg("请切换到预览效果");
                    return true;
                }
                mPaymentIosFragment.createImage();
                break;
            case AppConstant.ACTION_20:
                if (mPaymentGoogleFragment == null) {
                    alertMsg("请切换到预览效果");
                    return true;
                }
                mPaymentGoogleFragment.createImage();
        }
        return null;
    }

    @Override
    public void onItemClickListener(AliPaymentModel model) {
        aliPaymentModel = model;

        //判断是否为系统

        if (model.getTopToolStyle() == AppConstant.ACTION_20) {
            mToolbar.setVisibility(View.GONE);
        } else if (model.getTopToolStyle() == AppConstant.ACTION_10) {
            mToolbar.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case AppConstant.ACTION_10: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    createImage();
                } else {
                    // 权限被用户拒绝了，洗洗睡吧。
                }
                return;
            }
        }
    }

    //新添加的


    private void mergerTopStatus() {
        //处理头部

        mRelativeLayout.removeAllViews();


        if (aliPaymentModel.getTopToolStyle() == AppConstant.ACTION_10) {

            switch (aliPaymentModel.getMobileType()) {

                case AppConstant.ACTION_10:
                    //添加顶部标题栏
                    mPrimaryDarkIosView = PrimaryDarkIosView.build(this);
                    mPrimaryDarkIosView.binder(aliPaymentModel);
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
                    mPrimaryDarkView = PrimaryDarkView.build(this);
                    mPrimaryDarkView.binder(aliPaymentModel);
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
        else {
            getWindow().clearFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }





        ViewGroup.LayoutParams params;

        params = mToolbar.getLayoutParams();





        switch (aliPaymentModel.getMobileType()) {
            case AppConstant.ACTION_10:
                iosBackContainer.setVisibility(View.VISIBLE);
                androidBackContainer.setVisibility(View.GONE);

                params.height = ViewUtils.dip2px(this, 30);
                mToolbar.setLayoutParams(params);
                mTitleView.setTextSize(16F);
                setToolTitle("账单详情");
                mTitleView.getPaint().setFakeBoldText(false);
                mTitleView.setGravity(Gravity.CENTER|Gravity.CENTER_VERTICAL);
                mTitleView.setPadding(0,0,0,0);
                mTitleView.setTypeface(null);
                break;
            case AppConstant.ACTION_20:
                params.height = ViewUtils.dip2px(this, 48);
                mToolbar.setLayoutParams(params);
                setToolTitle("账单详情");
                iosBackContainer.setVisibility(View.GONE);
                androidBackContainer.setVisibility(View.VISIBLE);
                Typeface typeface = ResourcesCompat.getFont(this, R.font.apple);

                mTitleView.setTypeface(typeface);

                mTitleView.setTextSize(14F);
                mTitleView.getPaint().setFakeBoldText(true);
                mTitleView.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
                mTitleView.setPadding(ViewUtils.sp2px(this,55F),0,0,0);
                break;
        }


    }

    void onTopDateTimeClick() {


        if (mTimePickerDialog == null) {
            Calendar calendar = Calendar.getInstance();

            calendar.setTimeInMillis(aliPaymentModel.getTopTime());
            mTimePickerDialog = TimePickerDialog.newInstance(this, calendar.get(Calendar
                    .HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        }

        mTimePickerDialog.show(this.getFragmentManager(), "TimePickerDialog2");
    }


    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(aliPaymentModel.getTopTime());
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar
                        .get(Calendar.DAY_OF_MONTH), hourOfDay, minute,
                second);
        aliPaymentModel.setTopTime(calendar.getTimeInMillis());

        if (aliPaymentModel.getMobileType() == AppConstant.ACTION_20) {
            mPrimaryDarkView.binder(aliPaymentModel);
        } else {
            mPrimaryDarkIosView.binder(aliPaymentModel);
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
}
