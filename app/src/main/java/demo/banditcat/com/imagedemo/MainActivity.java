package demo.banditcat.com.imagedemo;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.banditcat.common.fontawesom.typeface.BaseFontAwesome;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;
import butterknife.OnTextChanged;
import demo.banditcat.com.imagedemo.activitys.DemoActivity;
import demo.banditcat.com.imagedemo.activitys.google.ChangeReceiptActivity;
import demo.banditcat.com.imagedemo.base.BaseActivity;
import demo.banditcat.com.imagedemo.constant.AppConstant;
import demo.banditcat.com.imagedemo.model.AliPaymentModel;
import demo.banditcat.com.imagedemo.model.BankModel;
import demo.banditcat.com.imagedemo.utils.SimpleUtils;
import demo.banditcat.com.imagedemo.utils.TimeUtils;
import demo.banditcat.com.imagedemo.utils.ViewUtils;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;

public class MainActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.alipayConstraintLayout)
    ConstraintLayout alipayConstraintLayout;

    @BindView(R.id.tvPaymentType)
    AppCompatTextView tvPaymentType;


    @BindView(R.id.etPaymentType)
    AppCompatEditText etPaymentType;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.tvOrderNo2)
    AppCompatTextView tvOrderNo2;

    @BindView(R.id.tvPaymentTime)
    AppCompatTextView tvPaymentTime;
    @BindView(R.id.tvBankHandleTime)
    AppCompatTextView tvBankHandleTime;

    @BindView(R.id.tvBankHandleOverTime)
    AppCompatTextView tvBankHandleOverTime;
    @BindView(R.id.tvHandleType)
    AppCompatTextView tvHandleType;

    @BindView(R.id.tvUnHandleLine2)
    AppCompatTextView tvUnHandleLine2;

    @BindView(R.id.tvBankHandleOverDot)
    AppCompatTextView tvBankHandleOverDot;

    @BindView(R.id.tvWXTSMessage)
    AppCompatTextView tvWXTSMessage;

    @BindView(R.id.tvWXTS)
    AppCompatTextView tvWXTS;

    @BindView(R.id.topDateTime)
    AppCompatTextView topDateTime;

    @BindView(R.id.tvMoney)
    AppCompatTextView tvMoney;//金额

    @BindView(R.id.tvReceiptUserInfo)
    AppCompatTextView tvReceiptUserInfo;

    @BindView(R.id.moneyRelativeLayout)
    RelativeLayout moneyRelativeLayout;

    @BindView(R.id.etMoney)
    AppCompatEditText etMoney;


    @BindView(R.id.tvCreateTime2)
    AppCompatTextView tvCreateTime;

    @BindView(R.id.tvBankImage)
    AppCompatImageView tvBankImage;

    @BindView(R.id.etRemark)
    AppCompatEditText etRemark;

    @BindView(R.id.tvRemark)
    AppCompatTextView tvRemark;

    @BindView(R.id.tvBankUserName)
    AppCompatTextView tvBankUserName;

    DatePickerDialog mDatePickerDialog;

    DatePickerDialog mDatePickerDialog30;

    TimePickerDialog mTimePickerDialog;
    TimePickerDialog mTimePickerDialog2;
    TimePickerDialog mTimePickerDialog30;

    AliPaymentModel mAliPaymentModel;

    int handleIndex = 10;//10 toptime 20 hadnleTIme 30 lastTime 40 createTime

    @Override
    public void initView() {

        setToolTitle(getString(R.string.title_activity_main)).setToolTitleGravity(Gravity.CENTER);

        ViewUtils.setCompoundRightDrawables(this, tvPaymentType, BaseFontAwesome.Icon.icon_right, getResources().getColor(R.color.colorRightTitle), 4f);

        mAliPaymentModel = new AliPaymentModel();

        BankModel bankModel = new BankModel("浦发银行", 120);

        mAliPaymentModel.setBankModel(bankModel);
        mAliPaymentModel.setBankNo("8888");
        mAliPaymentModel.setReceiptUserName("张三");
        mAliPaymentModel.setPaymentType("中国工商银行(6666)");
        mAliPaymentModel.setReceiptMoney(BigDecimal.valueOf(8888888.00));
        mAliPaymentModel.setCreateTime(System.currentTimeMillis());
        mAliPaymentModel.setPaymentTime(mAliPaymentModel.getCreateTime());
        mAliPaymentModel.setTopTime(mAliPaymentModel.getCreateTime());
        mAliPaymentModel.setLastTime(TimeUtils.addHour2(2, mAliPaymentModel.getCreateTime()));


        setTimeInfo(mAliPaymentModel.getPaymentTime());

        initViewInfo();

    }

    private void setTimeInfo(long time) {

        mAliPaymentModel.setLastTime(TimeUtils.addHour2(2, time));
        topDateTime.setText(TimeUtils.millis2String(mAliPaymentModel.getLastTime(), TimeUtils.DEFAULT_PATTERN_4));

        tvOrderNo2.setText(randomOrderNo(time));


        tvPaymentTime.setText(TimeUtils.millis2String(time, TimeUtils.DEFAULT_PATTERN_3));

        tvBankHandleTime.setText(tvPaymentTime.getText());


        if (tvHandleType.getTag().toString().compareTo("0") == 0) {
            tvBankHandleOverTime.setText(String.format(tvBankHandleOverTime.getTag().toString(), TimeUtils.addHour(2, time)));
        } else if (tvHandleType.getTag().toString().compareTo("1") == 0) {
            tvBankHandleOverTime.setText(TimeUtils.addHour(2, time));
        }


        tvCreateTime.setText(TimeUtils.millis2String(time, TimeUtils.DEFAULT_PATTERN_2));
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.fab)
    void onFabClick() {


        Bitmap cacheBitmapFromView = SimpleUtils.getCacheBitmapFromView(alipayConstraintLayout);
        SimpleUtils.saveBitmapToSdCard(MainActivity.this, cacheBitmapFromView, "styleOne");

/*        navigateActivity(new Intent(this, DemoActivity.class));*/


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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.tvPaymentTime)
    void paymentTimeClick() {

        handleIndex = 20;

        if (mDatePickerDialog != null) {
            mDatePickerDialog.show(getFragmentManager(), "Datepickerdialog");
        } else {
            Calendar calendar = Calendar.getInstance();

            calendar.setTimeInMillis(mAliPaymentModel.getPaymentTime());


            mDatePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get
                    (Calendar.DAY_OF_MONTH));

            mDatePickerDialog.show(getFragmentManager(), "Datepickerdialog");
        }


    }

    @OnClick(R.id.tvBankHandleOverTime)
    void onBankHandleOverTimeClick() {

        handleIndex = 30;
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(mAliPaymentModel.getLastTime());

        mDatePickerDialog30 = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get
                (Calendar.DAY_OF_MONTH));

        mDatePickerDialog30.show(getFragmentManager(), "DatePickerDialog30");
    }


    @OnLongClick(R.id.tvBankHandleOverTime)
    boolean onBankHandleOverTimeLongClick() {
        handleIndex = 30;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mAliPaymentModel.getLastTime());
        mTimePickerDialog30 = TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        mTimePickerDialog30.show(getFragmentManager(), "mTimePickerDialog30");

        return true;
    }


    @OnClick(R.id.topDateTime)
    void onTopDateTime() {
        handleIndex = 10;

        if (mTimePickerDialog2 != null) {
            mTimePickerDialog2.show(getFragmentManager(), "TimePickerDialog2");
        } else {
            Calendar calendar = Calendar.getInstance();

            calendar.setTimeInMillis(mAliPaymentModel.getTopTime());
            mTimePickerDialog2 = TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
            mTimePickerDialog2.show(getFragmentManager(), "TimePickerDialog2");
        }


    }

    @OnLongClick(R.id.tvPaymentTime)
    boolean onPaymentTimeLongClick() {

        handleIndex = 20;


        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(mAliPaymentModel.getPaymentTime());


        mTimePickerDialog = TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

        mTimePickerDialog.show(getFragmentManager(), "TimePickerDialog");

        return true;
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        Calendar calendar = Calendar.getInstance();


        switch (handleIndex) {
            case 10:

                calendar.setTimeInMillis(mAliPaymentModel.getTopTime());
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get
                        (Calendar.DAY_OF_MONTH), hourOfDay, minute, second);

                topDateTime.setText(TimeUtils.millis2String(calendar.getTimeInMillis(), TimeUtils.DEFAULT_PATTERN_4));
                break;
            case 20:

                calendar.setTimeInMillis(mAliPaymentModel.getPaymentTime());
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get
                        (Calendar.DAY_OF_MONTH), hourOfDay, minute, second);
                mAliPaymentModel.setPaymentTime(calendar.getTimeInMillis());
                setTimeInfo(mAliPaymentModel.getPaymentTime());
                break;
            case 30:

                //判断下 时间不能早于付款成功时间

                calendar.setTimeInMillis(mAliPaymentModel.getLastTime());
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get
                        (Calendar.DAY_OF_MONTH), hourOfDay, minute, second);

                if (getDistanceTime(mAliPaymentModel.getPaymentTime(), calendar.getTimeInMillis())) {


                    mAliPaymentModel.setLastTime(calendar.getTimeInMillis());

                    if (tvHandleType.getTag().toString().compareTo("0") == 0) {
                        tvBankHandleOverTime.setText(String.format(tvBankHandleOverTime.getTag().toString(), TimeUtils.millis2String(calendar.getTimeInMillis(), TimeUtils.DEFAULT_PATTERN_3)));
                    } else if (tvHandleType.getTag().toString().compareTo("1") == 0) {
                        tvBankHandleOverTime.setText(TimeUtils.millis2String(calendar.getTimeInMillis(), TimeUtils.DEFAULT_PATTERN_3));
                    }

                } else {
                    Toast.makeText(this, "到账成功时间至少比付款成功时间晚2小时！", Toast.LENGTH_SHORT).show();

                    return;
                }

                break;
        }

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        Calendar calendar = Calendar.getInstance();


        switch (handleIndex) {
            case 20:
                calendar.setTimeInMillis(mAliPaymentModel.getPaymentTime());
                calendar.set(year, monthOfYear, dayOfMonth);

                mAliPaymentModel.setPaymentTime(calendar.getTimeInMillis());
                setTimeInfo(mAliPaymentModel.getPaymentTime());

                break;

            case 30:
                calendar.setTimeInMillis(mAliPaymentModel.getLastTime());
                calendar.set(year, monthOfYear, dayOfMonth);
                if (getDistanceTime(mAliPaymentModel.getPaymentTime(), calendar.getTimeInMillis())) {

                    mAliPaymentModel.setLastTime(calendar.getTimeInMillis());
                    if (tvHandleType.getTag().toString().compareTo("0") == 0) {
                        tvBankHandleOverTime.setText(String.format(tvBankHandleOverTime.getTag().toString(), TimeUtils.millis2String(calendar.getTimeInMillis(), TimeUtils.DEFAULT_PATTERN_3)));
                    } else if (tvHandleType.getTag().toString().compareTo("1") == 0) {
                        tvBankHandleOverTime.setText(TimeUtils.millis2String(calendar.getTimeInMillis(), TimeUtils.DEFAULT_PATTERN_3));
                    }

                } else {
                    Toast.makeText(this, "到账成功时间至少比付款成功时间晚2小时！", Toast.LENGTH_SHORT).show();

                    return;
                }

                break;
        }


    }

    @OnClick(R.id.tvHandleType)
    void onHandleTypeClick() {
        //说明是处理中，更改为交易完成
        if (tvHandleType.getTag().toString().compareTo("0") == 0) {

            //处理页面逻辑
            tvHandleType.setTag("1");
            //文本改为交易完成
            tvHandleType.setText("交易完成");
            tvHandleType.setTextColor(getResources().getColor(R.color.colorHandle));
            tvUnHandleLine2.setBackgroundColor(getResources().getColor(R.color.colorHandleLine));
            tvBankHandleOverDot.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_bank_ok));

            tvWXTSMessage.setVisibility(View.GONE);
            tvWXTS.setVisibility(View.GONE);
        } else if (tvHandleType.getTag().toString().compareTo("1") == 0) {
            tvHandleType.setTag("0");
            tvHandleType.setText("处理中");
            tvHandleType.setTextColor(getResources().getColor(R.color.colorUnHandle));
            tvUnHandleLine2.setBackgroundColor(getResources().getColor(R.color.colorUnHandleLine));
            tvBankHandleOverDot.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_ali_tx_jd_more));
            tvWXTSMessage.setVisibility(View.VISIBLE);
            tvWXTS.setVisibility(View.VISIBLE);
        }

        setTimeInfo(mAliPaymentModel.getPaymentTime());
    }


    //金额
    @OnClick(R.id.tvMoney)
    void onMoneyClick() {


        if (moneyRelativeLayout.getVisibility() == View.GONE) {

            moneyRelativeLayout.setVisibility(View.VISIBLE);
        } else {
            moneyRelativeLayout.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.tvPaymentType)
    void onPaymentTypeClick() {


        if (etPaymentType.getVisibility() == View.GONE) {

            etPaymentType.setVisibility(View.VISIBLE);
        } else {
            etPaymentType.setVisibility(View.GONE);
        }
    }


    @OnClick(R.id.tvRemark)
    void onRemarkClick() {


        if (etRemark.getVisibility() == View.GONE) {

            etRemark.setVisibility(View.VISIBLE);
        } else {
            etRemark.setVisibility(View.GONE);
        }
    }


    @OnTextChanged(value = R.id.etPaymentType, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterPaymentTypeTextChanged(Editable s) {
        if (TextUtils.isEmpty(s.toString())) {
            return;
        }

        mAliPaymentModel.setPaymentType(s.toString());

        tvPaymentType.setText(mAliPaymentModel.getPaymentType());
    }


    @OnTextChanged(value = R.id.etRemark, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterRemarkTextChanged(Editable s) {
        if (TextUtils.isEmpty(s.toString())) {
            return;
        }

        mAliPaymentModel.setRemark(s.toString());

        tvRemark.setText(mAliPaymentModel.getRemark());
    }

    @OnTextChanged(value = R.id.etMoney, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterMoneyTextChanged(Editable s) {


        if (TextUtils.isEmpty(s.toString())) {
            return;
        }


        mAliPaymentModel.setReceiptMoney(new BigDecimal(s.toString()));

        tvMoney.setText(ViewUtils.mergeMoney(mAliPaymentModel.getReceiptMoney()));
    }

    @OnClick(R.id.topConstraintLayout)
    void onChangeBankClick() {

        Intent intent = new Intent(this, ChangeReceiptActivity.class);


        intent.putExtra(AppConstant.EXTRA_NO, mAliPaymentModel);

        navigateActivity(intent, 2000);
    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void storageNeedPer() {
        // 那些权限涉及到存储权限的，写在这里

    }

    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void storageNeedShowRat(final PermissionRequest request) {
        // 解释为什么需要这个权限
        showRationaleDialog("存储权限是本程序必不可少的权限，请开启", request);
    }

    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void storageDenied() {
        // 如果用户不授予某权限时调用的方法
        openAppSetting("您拒绝了存储权限，请授权");

    }

    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void storageAsk() {
        //如果用户选择了让设备“不再询问”，而调用的方法
        // 如果用户不授予某权限时调用的方法
        openAppSetting("您拒绝了存储权限，请授权");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (resultCode) {
            case 2000: /* 取得数据，并显示于画面上 */
                Bundle bunde = data.getExtras();
                mAliPaymentModel = (AliPaymentModel) bunde.getSerializable(AppConstant.EXTRA_NO);

                initViewInfo();
                break;
            default:
                break;
        }

    }

    /*
    * 初始化view信息
    * */
    void initViewInfo() {


        ViewUtils.initBankInfo(tvBankImage, mAliPaymentModel.getBankModel().getType());
        tvBankUserName.setText(mAliPaymentModel.getReceiptUserName());

        tvReceiptUserInfo.setText(String.format(tvReceiptUserInfo.getTag().toString(), mAliPaymentModel.getBankModel().getBankName()
                , mAliPaymentModel.getBankNo()
                , mAliPaymentModel.getReceiptUserName()
        ));

        tvMoney.setText(ViewUtils.mergeMoney(mAliPaymentModel.getReceiptMoney()));
        tvPaymentType.setText(mAliPaymentModel.getPaymentType());
    }
}
