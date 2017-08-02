package com.sb.app.views.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.AndroidApplication;
import com.sb.app.R;
import com.sb.app.di.HasComponent;
import com.sb.app.di.components.ApplicationComponent;
import com.sb.app.model.BankModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by banditcat-pc on 2017/7/3.
 */

public abstract class BaseFragment extends Fragment {

    protected abstract void DestroyView();

    protected ProgressDialog mProgressDialog;


    Unbinder mUnbinder;
    protected RecyclerView mBaseRecyclerView;


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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View mRootView = inflater.inflate(getContentViewId(), container, false);
        mUnbinder = ButterKnife.bind(this, mRootView);//绑定framgent
        return mRootView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(getString(R.string.alert_handle));


        initView();
    }

    @Override
    public void onDestroy() {

        DestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
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

    /**
     * 导航至新活动窗体
     *
     * @param intent
     */
    public void navigateActivity(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);


        getActivity().overridePendingTransition(R.anim.animation_down_in, R.anim.animation_top_out);
    }

    public void navigateActivity(Intent intent) {
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.animation_down_in, R.anim.animation_top_out);
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


        if (hour >= 2 && flag) {
            return true;
        } else {
            return false;
        }

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


    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {

        if (StringUtils.isEmpty(message)) {
            return;
        }
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }


    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }


    /**
     * Get the Main Application component for dependency injection.
     */
    protected ApplicationComponent getApplicationComponent(Context context) {
        return ((AndroidApplication) context).getApplicationComponent();
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


    protected void hideSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘


    }

    protected void showSoftInput() {


        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

    }
}
