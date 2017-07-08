package com.banditcat.app.views.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.banditcat.app.R;
import com.banditcat.app.model.BankModel;
import com.banditcat.common.fontawesom.IconicsDrawable;
import com.banditcat.common.fontawesom.typeface.IIcon;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * 文件名称：{@link BaseActivity}
 * <br/>
 * 功能描述：基类
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：2017/4/18 16:13
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：2017/4/18 16:13
 * <br/>
 * 修改备注：
 */
public abstract class BaseActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;//工具条控件

    @BindView(R.id.tv_title)
    protected AppCompatTextView mTitleView;//工具条标题控件


    protected ProgressDialog mProgressDialog;
    protected RecyclerView mBaseRecyclerView;

    protected final Scheduler mUiThread = Schedulers.io();
    protected final Scheduler mExecutorThread = AndroidSchedulers.mainThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(getContentViewId());
        ButterKnife.bind(this);//必须在setContentView之后

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.alert_handle));
        setSupportActionBar(mToolbar);
        initView();

    }

    /**
     * 初始化视图，工具条等信息
     */
    public abstract void initView();

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    protected abstract int getContentViewId();


    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().add(containerViewId, fragment, tag).commit();
    }

    protected void hideFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().hide(fragment).commit();
    }

    protected void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().show(fragment).commit();
    }

    //返回键返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Set the title of this titleview
     *
     * @param title Title to set
     * @return AppBaseActivity
     */
    public BaseActivity setToolTitle(CharSequence title) {
        mTitleView.setText(title);
        setTitle("");

        return this;
    }

    /**
     * Set the gravity parameters associated with this tool title.
     *
     * @param gravity {@link android.view.Gravity}
     * @return AppBaseActivity
     */
    public BaseActivity setToolTitleGravity(int gravity) {
        Toolbar.LayoutParams params = (Toolbar.LayoutParams) mTitleView.getLayoutParams();
        params.gravity = gravity;
        mTitleView.setLayoutParams(params);
        //mBaseTitleView.setGravity(gravity);
        return this;
    }

    /**
     * Set whether home should be displayed as an "up" affordance.
     *
     * @param hasHomeButton weather has home button
     */
    public BaseActivity setDisplayHome(boolean hasHomeButton) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(hasHomeButton);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        return this;
    }


    /**
     * set up click
     *
     * @return AppBaseActivity
     */
    public BaseActivity setHomeOnClickListener() {
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        return this;
    }

    /**
     * 弹出提示信息
     *
     * @param msg 消息文本
     */
    protected void alertMsg(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }


        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }


    /**
     * 导航至新活动窗体
     *
     * @param intent
     */
    public void navigateActivity(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        // overridePendingTransition(R.anim.animation_down_in, R.anim.animation_top_out);
    }

    /**
     * 导航至新活动窗体
     *
     * @param intent
     */
    public void navigateActivity(Intent intent) {
        startActivity(intent);
        // overridePendingTransition(R.anim.animation_down_in, R.anim.animation_top_out);
    }

    /**
     * 设置导航
     *
     * @param menuItem {@link MenuItem}
     * @param icon     {@link IIcon}
     * @param size     图标大小
     */
    protected void setMenu(MenuItem menuItem, IIcon icon, Float size) {


        setMenu(menuItem, icon, size, getResources().getColor(R.color.colorPrimary));
    }


    /**
     * 设置导航
     *
     * @param menuItem {@link MenuItem}
     * @param icon     {@link IIcon}
     * @param size     图标大小
     * @param color    颜色
     */
    protected void setMenu(MenuItem menuItem, IIcon icon, Float size, int color) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT > 19) {
            drawable = new IconicsDrawable(this, icon).actionBar(size).color(color).paddingDp(2);
        } else {
            drawable = new IconicsDrawable(this, icon).actionBar(size).color(color).paddingDp(2);
        }

        menuItem.setIcon(drawable);
    }


    /**
     * init LinearLayout RecyclerView
     *
     * @param layoutManager Set the {@link RecyclerView.LayoutManager} that this RecyclerView will
     *                      use.
     * @return widget for RecyclerView
     */
    protected RecyclerView initLinearLayoutRecyclerView(RecyclerView.LayoutManager layoutManager) {


        mBaseRecyclerView.setLayoutManager(layoutManager);
        mBaseRecyclerView.setHasFixedSize(true);
        // 设置item动画
        mBaseRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return this.mBaseRecyclerView;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /*
    * 获取订单号
    * */
    protected String randomOrderNo(long currentTimeMillis) {


        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(currentTimeMillis);

        DateFormat format2 = new SimpleDateFormat("yyyyMMdd");
        StringBuilder stringBuilder = new StringBuilder(format2.format(calendar.getTime()));

        stringBuilder.append("2000400");

        for (int i = 0; i < 17; i++) {
            stringBuilder.append(String.valueOf((int) (Math.random() * 10)));
        }


        return stringBuilder.toString();
    }

    // 这些方法写到基类好一些
    protected void openAppSetting(String content) {
        new AlertDialog.Builder(this).setMessage(content).setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 跳转到app设置
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            }
        }).create().show();
    }



    /*
   *计算time2减去time1的差值 差值只设置 几天 几个小时 或 几分钟
   * 根据差值返回多长之间前或多长时间后
   * */
    public boolean getDistanceTime(long time1, long time2) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long diff;
        boolean flag;
        if (time1 <= time2) {
            diff = time2 - time1;
            flag = true;
        } else {
            diff = time1 - time2;
            flag = false;
        }
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);


        Toast.makeText(this, "adada=====！" + String.valueOf(hour), Toast.LENGTH_SHORT).show();

        if (hour >= 2 && flag) {
            return true;
        } else {
            return false;
        }

    }

    public Collection<BankModel> initBankInfo() {
        Collection<BankModel> bankModels = new ArrayList<>();

        bankModels.add(new BankModel("中国银行", 10));
        bankModels.add(new BankModel("中国建设银行", 20));
        bankModels.add(new BankModel("中国农业银行", 30));
        bankModels.add(new BankModel("中国工商银行", 40));
        bankModels.add(new BankModel("中国邮政储蓄银行", 50));
        bankModels.add(new BankModel("中国民生银行", 60));
        bankModels.add(new BankModel("中国光大银行", 70));
        bankModels.add(new BankModel("中信银行", 80));
        bankModels.add(new BankModel("招商银行", 90));
        bankModels.add(new BankModel("兴业银行", 100));
        bankModels.add(new BankModel("网商银行", 110));
        bankModels.add(new BankModel("浦发银行", 120));
        bankModels.add(new BankModel("平安银行", 130));
        bankModels.add(new BankModel("交通银行", 140));
        bankModels.add(new BankModel("华夏银行", 150));
        bankModels.add(new BankModel("广发银行", 160));

        return bankModels;
    }
}
