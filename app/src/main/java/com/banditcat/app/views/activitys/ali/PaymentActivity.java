package com.banditcat.app.views.activitys.ali;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.banditcat.common.fontawesom.typeface.BaseFontAwesome;

import java.math.BigDecimal;

import butterknife.BindView;
import com.banditcat.app.R;
import com.banditcat.app.views.base.BaseActivity;
import com.banditcat.app.constant.AppConstant;
import com.banditcat.app.views.listeners.MobileChangeListener;
import com.banditcat.app.model.AliPaymentModel;
import com.banditcat.app.model.BankModel;
import com.banditcat.app.utils.TimeUtils;

public class PaymentActivity extends BaseActivity implements MobileChangeListener<AliPaymentModel> {

    @BindView(R.id.content)
    FrameLayout content;

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private TextView mTextMessage;

    PaymentGoogleFragment mPaymentGoogleFragment;
    String paymentTag = "PaymentGoogleFragment";
    Fragment fragment;

    PaymentMenuFragment mPaymentMenuFragment;
    String paymentMenuTag = "PaymentMenuFragment";

    PaymentMobileStyleFragment mPaymentMobileStyleFragment;
    String mobileStyleTag = "PaymentMobileStyleFragment";


    AliPaymentModel aliPaymentModel;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView
            .OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            hideFragment();
            switch (item.getItemId()) {
                case R.id.navigation_home:


                    fragment = getSupportFragmentManager().findFragmentByTag(mobileStyleTag);

                    if (fragment != null) {
                        mPaymentMobileStyleFragment = (PaymentMobileStyleFragment) fragment;
                        showFragment(mPaymentMobileStyleFragment);
                        mPaymentMobileStyleFragment.loadViewData(aliPaymentModel);
                    } else {

                        mPaymentMobileStyleFragment = PaymentMobileStyleFragment.newInstance(aliPaymentModel);
                        addFragment(R.id.content, mPaymentMobileStyleFragment, mobileStyleTag);
                        mPaymentMobileStyleFragment.setMobileChangeListener(PaymentActivity.this);

                    }
                    return true;
                case R.id.navigation_dashboard:



                    fragment = getSupportFragmentManager().findFragmentByTag(paymentMenuTag);

                    if (fragment != null) {
                        mPaymentMenuFragment = (PaymentMenuFragment) fragment;
                        showFragment(mPaymentMenuFragment);
                        mPaymentMenuFragment.loadViewData(aliPaymentModel);
                    } else {

                        mPaymentMenuFragment = PaymentMenuFragment.newInstance(aliPaymentModel);
                        addFragment(R.id.content, mPaymentMenuFragment, paymentMenuTag);
                        mPaymentMenuFragment.setMobileChangeListener(PaymentActivity.this);
                    }

                    return true;
                case R.id.navigation_notifications:




                    fragment = getSupportFragmentManager().findFragmentByTag(paymentTag);

                    if (fragment != null) {
                        mPaymentGoogleFragment = (PaymentGoogleFragment) fragment;

                        showFragment(mPaymentGoogleFragment);
                        mPaymentGoogleFragment.loadViewData(aliPaymentModel);



                    } else {

                        mPaymentGoogleFragment = PaymentGoogleFragment.newInstance(aliPaymentModel);
                        addFragment(R.id.content, mPaymentGoogleFragment, paymentTag);
                        mPaymentGoogleFragment.setMobileChangeListener(PaymentActivity.this);
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

        setToolTitle(getString(R.string.title_activity_payment)).setDisplayHome(true).setHomeOnClickListener();


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        aliPaymentModel = new AliPaymentModel();
        BankModel bankModel = new BankModel("浦发银行", 120);

        aliPaymentModel.setBankModel(bankModel);

        aliPaymentModel.setTopToolStyle(AppConstant.ACTION_10);
        aliPaymentModel.setMobileType(AppConstant.ACTION_20);
        aliPaymentModel.setNetworkSignal(AppConstant.ACTION_10);
        aliPaymentModel.setNetworkType(AppConstant.ACTION_10);
        aliPaymentModel.setFinish(false);
        aliPaymentModel.setRemark("转账");
        aliPaymentModel.setBankNo("8888");
        aliPaymentModel.setReceiptUserName("张三");
        aliPaymentModel.setPaymentType("中国工商银行(6666)");
        aliPaymentModel.setReceiptMoney(BigDecimal.valueOf(8888888.00));
        aliPaymentModel.setCreateTime(System.currentTimeMillis());
        aliPaymentModel.setPaymentTime(aliPaymentModel.getCreateTime());
        aliPaymentModel.setTopTime(aliPaymentModel.getCreateTime());
        aliPaymentModel.setLastTime(TimeUtils.addHour2(2, aliPaymentModel.getCreateTime()));


        navigation.setSelectedItemId(R.id.navigation_home);


        setLeftMenu();

    }


    /**
     * 设置抽屉菜单
     */
    void setLeftMenu() {

        Float size = 10F;
        int color = getResources().getColor(R.color.white);

        //用户
        MenuItem menuItem = navigation.getMenu().findItem(R.id.navigation_home);
        setMenu(menuItem, BaseFontAwesome.Icon.icon_mobile, size);


        //业务管理
        menuItem = navigation.getMenu().findItem(R.id.navigation_dashboard);
        setMenu(menuItem, BaseFontAwesome.Icon.icon_content, size);

        //资金账户
        menuItem = navigation.getMenu().findItem(R.id.navigation_notifications);
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


            mPaymentGoogleFragment.createImage();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClickListener(AliPaymentModel model) {
        aliPaymentModel = model;
    }
}
