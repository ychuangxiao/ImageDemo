package demo.banditcat.com.imagedemo.activitys.ali;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.math.BigDecimal;

import butterknife.BindView;
import demo.banditcat.com.imagedemo.R;
import demo.banditcat.com.imagedemo.base.BaseActivity;
import demo.banditcat.com.imagedemo.model.AliPaymentModel;
import demo.banditcat.com.imagedemo.model.BankModel;
import demo.banditcat.com.imagedemo.utils.TimeUtils;

public class PaymentActivity extends BaseActivity {

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

    AliPaymentModel aliPaymentModel;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            hideFragment();
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_dashboard:


                    if (mPaymentGoogleFragment != null) {
                        aliPaymentModel = mPaymentGoogleFragment.getAliPaymentModel();
                    }

                    fragment = getSupportFragmentManager().findFragmentByTag(paymentMenuTag);

                    if (fragment != null) {
                        mPaymentMenuFragment = (PaymentMenuFragment) fragment;
                        showFragment(mPaymentMenuFragment);
                        mPaymentMenuFragment.loadViewData(aliPaymentModel);
                    } else {

                        mPaymentMenuFragment = PaymentMenuFragment.newInstance(aliPaymentModel);
                        addFragment(R.id.content, mPaymentMenuFragment, paymentMenuTag);
                    }

                    return true;
                case R.id.navigation_notifications:


                    if (mPaymentMenuFragment != null) {
                        aliPaymentModel = mPaymentMenuFragment.getAliPaymentModel();
                    }


                    fragment = getSupportFragmentManager().findFragmentByTag(paymentTag);

                    if (fragment != null) {
                        mPaymentGoogleFragment = (PaymentGoogleFragment) fragment;

                        showFragment(mPaymentGoogleFragment);
                        mPaymentGoogleFragment.loadViewData(aliPaymentModel);

                    } else {

                        mPaymentGoogleFragment = PaymentGoogleFragment.newInstance(aliPaymentModel);
                        addFragment(R.id.content, mPaymentGoogleFragment, paymentTag);
                    }

                    return true;
            }
            return false;
        }

    };


    void hideFragment() {
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


        navigation.setSelectedItemId(R.id.navigation_notifications);


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

}
