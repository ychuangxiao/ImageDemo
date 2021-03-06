package com.sb.app.views.activitys.ali;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.model.AliPaymentModel;
import com.sb.app.model.BankModel;
import com.sb.app.model.base.BaseMobileModel;
import com.sb.app.utils.TimeUtils;
import com.sb.app.views.base.BaseActivity;
import com.sb.app.views.fragment.PaymentGoogleFragment;
import com.sb.app.views.fragment.PaymentIosFragment;
import com.sb.app.views.fragment.PaymentMenuFragment;
import com.sb.app.views.fragment.PaymentMobileStyleFragment;
import com.sb.app.views.listeners.MobileChangeListener;
import com.sb.common.fontawesom.typeface.BaseFontAwesome;

import java.math.BigDecimal;

import butterknife.BindView;

public class PaymentActivityV2 extends BaseActivity implements MobileChangeListener<AliPaymentModel> {

    @BindView(R.id.content)
    FrameLayout content;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private TextView mTextMessage;

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

                    hideFragment();
                    switch (item.getItemId()) {
                        case R.id.navigation_mobile_style:


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
                                    }
                                });


                            }
                            return true;
                        case R.id.navigation_content:


                            if (mPaymentMenuFragment != null) {

                                showFragment(mPaymentMenuFragment);
                                mPaymentMenuFragment.loadViewData(aliPaymentModel);
                            } else {

                                mPaymentMenuFragment = PaymentMenuFragment.newInstance
                                        (aliPaymentModel);
                                addFragment(R.id.content, mPaymentMenuFragment, paymentMenuTag);
                                mPaymentMenuFragment.setMobileChangeListener(PaymentActivityV2.this);
                            }

                            return true;
                        case R.id.navigation_preview:


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
                                                (PaymentActivityV2.this);
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
                                                (PaymentActivityV2.this);
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

        setToolTitle(getString(R.string.title_activity_payment));


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            if (checkPermissions()) {
                createImage();
            }


            return true;

        }

        return super.onOptionsItemSelected(item);
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
}
