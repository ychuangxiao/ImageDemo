package com.sb.app.views.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sb.common.utils.StringUtils;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.model.AliPaymentModel;
import com.sb.app.model.EditModel;
import com.sb.app.utils.MathUtils;
import com.sb.app.utils.SimpleUtils;
import com.sb.app.utils.TimeUtils;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.activitys.google.ChangeReceiptActivity;
import com.sb.app.views.base.BaseFragment;
import com.sb.app.views.listeners.MobileChangeListener;
import com.sb.app.views.viewgroup.EditTextItemView;
import com.sb.app.views.viewgroup.PrimaryDarkIosView;
import com.sb.app.views.viewgroup.PrimaryDarkView;
import com.sb.app.views.viewgroup.PrimaryTopTitleIosView;
import com.sb.common.fontawesom.typeface.BaseFontAwesome;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.math.BigDecimal;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link PaymentIosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentIosFragment extends BaseFragment implements DatePickerDialog
        .OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static BottomNavigationView mBottomNavigationView;

    //手机外观

    @BindView(R.id.tv2)
    AppCompatTextView tv2;


    @BindView(R.id.tv3)
    AppCompatTextView tv3;


    @BindView(R.id.tv6)
    AppCompatTextView tv6;


    @BindView(R.id.tv7)
    AppCompatTextView tv7;


    @BindView(R.id.primaryDarkConstraintLayout)
    ConstraintLayout primaryDarkConstraintLayout;


    @BindView(R.id.primaryConstraintLayout)
    ConstraintLayout primaryConstraintLayout;


    @BindView(R.id.alipayConstraintLayout)
    ConstraintLayout alipayConstraintLayout;

    @BindView(R.id.alipayNestedScrollView)
    NestedScrollView aliNestedScrollView;


    @BindView(R.id.tvPaymentType)
    AppCompatTextView tvPaymentType;


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


    @BindView(R.id.tvMoney)
    AppCompatTextView tvMoney;//金额

    @BindView(R.id.tvReceiptUserInfo)
    AppCompatTextView tvReceiptUserInfo;


    @BindView(R.id.tvCreateTime2)
    AppCompatTextView tvCreateTime;

    @BindView(R.id.tvBankImage)
    AppCompatImageView tvBankImage;


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

    PrimaryDarkView mPrimaryDarkView;//顶部标题栏
    PrimaryDarkIosView mPrimaryDarkIosView;

    Context mContext;


    BottomSheetDialog mBottomSheetDialog;
    EditTextItemView mEditTextItemView;

    public void setContext(Context context) {
        mContext = context;
    }

    public PaymentIosFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PaymentGoogleFragment.
     */
    public static PaymentIosFragment newInstance(AliPaymentModel aliPaymentModel, BottomNavigationView navigationView) {


        mBottomNavigationView = navigationView;
        PaymentIosFragment fragment = new PaymentIosFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, aliPaymentModel);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void DestroyView() {

    }

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {
        if (getArguments() == null) {
            return;
        }


        ViewUtils.setCompoundRightDrawables(getContext(), tv2, BaseFontAwesome.Icon
                .icon_right, getResources().getColor(R.color
                .colorRightTitle), 5f);

        ViewUtils.setCompoundRightDrawables(getContext(), tv3, BaseFontAwesome.Icon
                .icon_right, getResources().getColor(R.color
                .colorRightTitle), 5f);


        ViewUtils.setCompoundRightDrawables(getContext(), tv6, BaseFontAwesome.Icon
                .icon_right, getResources().getColor(R.color
                .colorRightTitle), 5f);

        ViewUtils.setCompoundRightDrawables(getContext(), tv7, BaseFontAwesome.Icon
                .icon_right, getResources().getColor(R.color
                .colorRightTitle), 5f);


        loadViewData((AliPaymentModel) getArguments().getSerializable(ARG_PARAM1));


        //创建底部文本编辑视图

        mEditTextItemView = EditTextItemView.build(getActivity());


    }

    public void loadViewData(AliPaymentModel model) {


        mAliPaymentModel = model;
        mBottomNavigationView.setVisibility(View.GONE);


        initViewInfo();


        if (mAliPaymentModel.getFinish()) {

            tvHandleType.setTag(1);

            showHandleLine();
        } else {
            tvHandleType.setTag(0);
            hideHandleLine();
        }
    }


    /*
    * 初始化view信息
    * */
    void initViewInfo() {


        //手机样式


        ViewUtils.initBankInfo(tvBankImage, mAliPaymentModel.getBankModel().getType());
        tvBankUserName.setText(mAliPaymentModel.getReceiptUserName());
        tvReceiptUserInfo.setText(String.format(tvReceiptUserInfo.getTag().toString(),
                mAliPaymentModel.getBankModel().getBankName(),
                mAliPaymentModel.getBankNo(), mAliPaymentModel.getReceiptUserName()));
        tvMoney.setText(ViewUtils.mergeMoney(mAliPaymentModel.getReceiptMoney()));
        tvPaymentType.setText(mAliPaymentModel.getPaymentType());

        tvRemark.setText(mAliPaymentModel.getRemark());
    }


    /*
    * 设置时间信息
    * */
    private void setTimeInfo(long time) {

        mAliPaymentModel.setLastTime(TimeUtils.addHour2(2, time));


        if (mPrimaryDarkIosView != null) {
            mPrimaryDarkIosView.binder(mAliPaymentModel);
        }

        if (StringUtils.isEmpty(tvOrderNo2.getText().toString())) {
            tvOrderNo2.setText(randomOrderNo(time));
        }
        tvPaymentTime.setText(TimeUtils.millis2String(time, TimeUtils.DEFAULT_PATTERN_3));

        tvBankHandleTime.setText(tvPaymentTime.getText());


        if (tvHandleType.getTag().toString().compareTo("0") == 0) {
            tvBankHandleOverTime.setText(String.format(tvBankHandleOverTime.getTag().toString(),
                    TimeUtils.addHour(2, time)));
        } else if (tvHandleType.getTag().toString().compareTo("1") == 0) {
            tvBankHandleOverTime.setText(TimeUtils.addHour(2, time));


        }


        tvCreateTime.setText(TimeUtils.millis2String(time, TimeUtils.DEFAULT_PATTERN_2));
    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_payment_ios;
    }


    @OnClick(R.id.tvPaymentTime)
    void paymentTimeClick() {

        handleIndex = 20;

        if (mDatePickerDialog != null) {
            mDatePickerDialog.show(getActivity().getFragmentManager(), "Datepickerdialog");
        } else {
            Calendar calendar = Calendar.getInstance();

            calendar.setTimeInMillis(mAliPaymentModel.getPaymentTime());


            mDatePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH), calendar.get(Calendar
                            .DAY_OF_MONTH));

            mDatePickerDialog.show(getActivity().getFragmentManager(), "Datepickerdialog");
        }


    }

    @OnClick(R.id.tvBankHandleOverTime)
    void onBankHandleOverTimeClick() {

        handleIndex = 30;
        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(mAliPaymentModel.getLastTime());

        mDatePickerDialog30 = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar
                        .DAY_OF_MONTH));

        mDatePickerDialog30.show(getActivity().getFragmentManager(), "DatePickerDialog30");
    }


    @OnLongClick(R.id.tvBankHandleOverTime)
    boolean onBankHandleOverTimeLongClick() {
        handleIndex = 30;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mAliPaymentModel.getLastTime());
        mTimePickerDialog30 = TimePickerDialog.newInstance(this, calendar.get(Calendar
                .HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        mTimePickerDialog30.show(getActivity().getFragmentManager(), "mTimePickerDialog30");

        return true;
    }


    void onTopDateTimeClick() {
        handleIndex = 10;

        if (mTimePickerDialog2 != null) {
            mTimePickerDialog2.show(getActivity().getFragmentManager(), "TimePickerDialog2");
        } else {
            Calendar calendar = Calendar.getInstance();

            calendar.setTimeInMillis(mAliPaymentModel.getTopTime());
            mTimePickerDialog2 = TimePickerDialog.newInstance(this, calendar.get(Calendar
                    .HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
            mTimePickerDialog2.show(getActivity().getFragmentManager(), "TimePickerDialog2");
        }


    }

    @OnLongClick(R.id.tvPaymentTime)
    boolean onPaymentTimeLongClick() {

        handleIndex = 20;


        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(mAliPaymentModel.getPaymentTime());


        mTimePickerDialog = TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE), true);

        mTimePickerDialog.show(getActivity().getFragmentManager(), "TimePickerDialog");

        return true;
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        Calendar calendar = Calendar.getInstance();


        switch (handleIndex) {
            case 10:

                calendar.setTimeInMillis(mAliPaymentModel.getTopTime());
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar
                                .get(Calendar.DAY_OF_MONTH), hourOfDay, minute,
                        second);

                mAliPaymentModel.setTopTime(calendar.getTimeInMillis());
                if (mModelMobileChangeListener != null) {

                    mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
                }


                if (mAliPaymentModel.getMobileType() == AppConstant.ACTION_20) {
                    mPrimaryDarkView.binder(mAliPaymentModel);
                } else {
                    mPrimaryDarkIosView.binder(mAliPaymentModel);
                }


                break;
            case 20:

                calendar.setTimeInMillis(mAliPaymentModel.getPaymentTime());
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar
                                .get(Calendar.DAY_OF_MONTH), hourOfDay, minute,
                        second);

                mAliPaymentModel.setPaymentTime(calendar.getTimeInMillis());
                if (mModelMobileChangeListener != null) {

                    mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
                }


                setTimeInfo(mAliPaymentModel.getPaymentTime());
                break;
            case 30:

                //判断下 时间不能早于付款成功时间

                calendar.setTimeInMillis(mAliPaymentModel.getLastTime());
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar
                                .get(Calendar.DAY_OF_MONTH), hourOfDay, minute,
                        second);

                //判断是否已完成

                Long betweenSecond = TimeUtils.comperHour(mAliPaymentModel.getPaymentTime(), calendar.getTimeInMillis
                        ());

                if (mAliPaymentModel.getFinish()) {

                    if (betweenSecond < 0) {
                        Toast.makeText(getActivity(), "到账成功时间必须大于等于付款成功时间", Toast.LENGTH_SHORT)
                                .show();

                        return;
                    }


                } else {

                    if (betweenSecond < 3600 * 2) {
                        Toast.makeText(getActivity(), "付款成功时间必须比到账成功时间 大2小时！", Toast.LENGTH_SHORT)
                                .show();

                        return;
                    }


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

                if (mModelMobileChangeListener != null) {

                    mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
                }


                setTimeInfo(mAliPaymentModel.getPaymentTime());

                break;

            case 30:
                calendar.setTimeInMillis(mAliPaymentModel.getLastTime());
                calendar.set(year, monthOfYear, dayOfMonth);
                if (getDistanceTime(mAliPaymentModel.getPaymentTime(), calendar.getTimeInMillis()
                )) {

                    mAliPaymentModel.setLastTime(calendar.getTimeInMillis());
                    if (mModelMobileChangeListener != null) {

                        mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
                    }

                    if (tvHandleType.getTag().toString().compareTo("0") == 0) {
                        tvBankHandleOverTime.setText(String.format(tvBankHandleOverTime.getTag()
                                .toString(), TimeUtils.millis2String(calendar
                                .getTimeInMillis(), TimeUtils.DEFAULT_PATTERN_3)));
                    } else if (tvHandleType.getTag().toString().compareTo("1") == 0) {
                        tvBankHandleOverTime.setText(TimeUtils.millis2String(calendar
                                .getTimeInMillis(), TimeUtils.DEFAULT_PATTERN_3));
                    }

                } else {
                    Toast.makeText(getActivity(), "付款成功时间必须比到账成功时间 大2小时！", Toast.LENGTH_SHORT)
                            .show();

                    return;
                }

                break;
        }


    }

    @OnClick(R.id.tvHandleType)
    void onHandleTypeClick() {
        //说明是处理中，更改为交易完成
        if (tvHandleType.getTag().toString().compareTo("0") == 0) {
            showHandleLine();

            mAliPaymentModel.setFinish(true);

        } else if (tvHandleType.getTag().toString().compareTo("1") == 0) {
            hideHandleLine();
            mAliPaymentModel.setFinish(false);

        }


    }

    private void hideHandleLine() {
        tvHandleType.setTag("0");
        tvHandleType.setText("处理中");
        tvHandleType.setTextColor(getResources().getColor(R.color.colorUnHandle));
        tvUnHandleLine2.setBackgroundColor(getResources().getColor(R.color.colorUnHandleLine));
        tvBankHandleOverDot.setBackgroundDrawable(getResources().getDrawable(R.mipmap
                .ic_ali_tx_jd_more));
        tvWXTSMessage.setVisibility(View.GONE);
        tvWXTS.setVisibility(View.GONE);

        setTimeInfo(mAliPaymentModel.getPaymentTime());
    }

    private void showHandleLine() {
        //处理页面逻辑
        tvHandleType.setTag("1");
        //文本改为交易完成
        tvHandleType.setText("交易成功");
        tvHandleType.setTextColor(getResources().getColor(R.color.colorHandle));
        tvUnHandleLine2.setBackgroundColor(getResources().getColor(R.color.colorHandleLine));
        tvBankHandleOverDot.setBackgroundDrawable(getResources().getDrawable(R.mipmap.ic_bank_ok));

        tvWXTSMessage.setVisibility(View.GONE);
        tvWXTS.setVisibility(View.GONE);

        setTimeInfo(mAliPaymentModel.getPaymentTime());
    }

    EditModel model;

    //金额
    @OnClick(R.id.tvMoney)
    void onMoneyClick() {

/*
        if (moneyRelativeLayout.getVisibility() == View.GONE) {

            moneyRelativeLayout.setVisibility(View.VISIBLE);
        } else {
            moneyRelativeLayout.setVisibility(View.GONE);
        }*/


        model = new EditModel();
        model.setHintText("付款金额");
        model.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        model.setHandleAction(AppConstant.ACTION_10);
        model.setMaxLength(12);
        model.setText(MathUtils.toString(mAliPaymentModel.getReceiptMoney()));

        initDialog();

    }

    private void initDialog() {
        if (mBottomSheetDialog == null) {
            mBottomSheetDialog = new BottomSheetDialog(getActivity());


            mBottomSheetDialog.setContentView(mEditTextItemView);


            mEditTextItemView.getBtnHandle().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    switch (model.getHandleAction()) {
                        case AppConstant.ACTION_10:

                            mAliPaymentModel.setReceiptMoney(new BigDecimal(mEditTextItemView
                                    .getEtChangeValue().getText().toString()));

                            tvMoney.setText(ViewUtils.mergeMoney(mAliPaymentModel.getReceiptMoney
                                    ()));
                            break;

                        case AppConstant.ACTION_20:

                            mAliPaymentModel.setPaymentType(mEditTextItemView
                                    .getEtChangeValue().getText().toString());
                            tvPaymentType.setText(mAliPaymentModel.getPaymentType());

                            break;

                        case AppConstant.ACTION_30:

                            mAliPaymentModel.setRemark(mEditTextItemView
                                    .getEtChangeValue().getText().toString());
                            tvRemark.setText(mAliPaymentModel.getRemark());

                            break;
                    }


                    mBottomSheetDialog.dismiss();
                    if (mModelMobileChangeListener != null) {

                        mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
                    }

                }
            });

            mEditTextItemView.getBtnCancel().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mBottomSheetDialog.dismiss();
                }
            });

            View view = mBottomSheetDialog.getWindow().findViewById(android.support.design.R.id
                    .design_bottom_sheet);
            BottomSheetBehavior.from(view).setPeekHeight(alipayConstraintLayout.getLayoutParams()
                    .height);


        }
        mEditTextItemView.getEtChangeValue().setMaxLines(model.getMaxLines());
        mEditTextItemView.binder(model);

        mEditTextItemView
                .getEtChangeValue().setSelection(model.getText().length());

        mBottomSheetDialog.show();
    }

    @OnClick(R.id.tvPaymentType)
    void onPaymentTypeClick() {


        model = new EditModel();
        model.setHintText("付款方式");
        model.setInputType(InputType.TYPE_CLASS_TEXT);
        model.setHandleAction(AppConstant.ACTION_20);
        model.setMaxLength(20);
        model.setText(mAliPaymentModel.getPaymentType());
        initDialog();
    }


    @OnClick(R.id.tvRemark)
    void onRemarkClick() {


        model = new EditModel();
        model.setHintText("转账说明");
        model.setInputType(InputType.TYPE_CLASS_TEXT);
        model.setHandleAction(AppConstant.ACTION_30);
        model.setMaxLength(50);
        model.setMaxLines(5);
        model.setText(mAliPaymentModel.getRemark());

        initDialog();
    }


    @OnClick(R.id.topConstraintLayout)
    void onChangeBankClick() {

        Intent intent = new Intent(getActivity(), ChangeReceiptActivity.class);


        intent.putExtra(AppConstant.EXTRA_NO, mAliPaymentModel);

        navigateActivity(intent, 2000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case 2000: /* 取得数据，并显示于画面上 */
                Bundle bunde = data.getExtras();
                mAliPaymentModel = (AliPaymentModel) bunde.getSerializable(AppConstant.EXTRA_NO);

                //主要是 户名 银行 卡号

                if (mModelMobileChangeListener != null) {

                    mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
                }


                initViewInfo();
                break;
            default:
                break;
        }
    }


    public void createImage() {


        Point point = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(point);


        //Bitmap cacheBitmapFromView = SimpleUtils.getCacheBitmapFromView(alipayConstraintLayout,
        // point);
        Bitmap cacheBitmapFromView = SimpleUtils.getViewImage(alipayConstraintLayout, point);
        SimpleUtils.saveBitmapToSdCard(getActivity(), cacheBitmapFromView, "styleOne");
    }


    MobileChangeListener<AliPaymentModel> mModelMobileChangeListener;

    public void setMobileChangeListener(MobileChangeListener<AliPaymentModel>
                                                modelMobileChangeListener) {
        this.mModelMobileChangeListener = modelMobileChangeListener;
    }
}
