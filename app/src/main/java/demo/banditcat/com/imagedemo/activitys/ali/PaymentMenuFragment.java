package demo.banditcat.com.imagedemo.activitys.ali;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextUtils;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import demo.banditcat.com.imagedemo.R;
import demo.banditcat.com.imagedemo.base.BaseFragment;
import demo.banditcat.com.imagedemo.listeners.MobileChangeListener;
import demo.banditcat.com.imagedemo.listeners.RecyclerClickListener;
import demo.banditcat.com.imagedemo.model.AliPaymentModel;
import demo.banditcat.com.imagedemo.model.MenuModel;
import demo.banditcat.com.imagedemo.utils.TimeUtils;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link PaymentMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentMenuFragment extends BaseFragment implements RecyclerClickListener<MenuModel>, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    AliPaymentModel mAliPaymentModel;


    @BindView(R.id.etMoney)
    AppCompatEditText etMoney;

    @BindView(R.id.etReceiptUserName)
    AppCompatEditText etReceiptUserName;


    @BindView(R.id.etBankNo)
    AppCompatEditText etBankNo;

    @BindView(R.id.etRemark)
    AppCompatEditText etRemark;

    @BindView(R.id.tvCreateDate2)
    AppCompatTextView tvCreateDate;

    @BindView(R.id.tvCreateTime2)
    AppCompatTextView tvCreateTime;

    @BindView(R.id.ckType)
    AppCompatCheckBox ckType;

    DatePickerDialog mDatePickerDialog;


    TimePickerDialog mTimePickerDialog;

    public PaymentMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PaymentMenuFragment.
     */

    public static PaymentMenuFragment newInstance(AliPaymentModel aliPaymentModel) {
        PaymentMenuFragment fragment = new PaymentMenuFragment();
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



        loadViewData((AliPaymentModel) getArguments().getSerializable(ARG_PARAM1));

    }

    public void loadViewData(AliPaymentModel model) {
        if (model == null) {
            return;
        }

        mAliPaymentModel = model;

        DecimalFormat df = new DecimalFormat("########.########");

        etMoney.setText(df.format(mAliPaymentModel.getReceiptMoney()));

        etReceiptUserName.setText(mAliPaymentModel.getReceiptUserName());

        etBankNo.setText(mAliPaymentModel.getBankNo());

        etRemark.setText(mAliPaymentModel.getRemark());

        tvCreateDate.setText(TimeUtils.millis2String(mAliPaymentModel.getPaymentTime(), TimeUtils.DEFAULT_PATTERN_5));

        tvCreateTime.setText(TimeUtils.millis2String(mAliPaymentModel.getPaymentTime(), TimeUtils.DEFAULT_PATTERN_4));

        ckType.setChecked(mAliPaymentModel.getFinish());
    }


    public AliPaymentModel getAliPaymentModel() {
        return mAliPaymentModel;
    }
    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_payment_menu;
    }


    @Override
    public void onItemClickListener(MenuModel model) {

    }

    /**
     * @param view        The view associated with this listener.
     * @param year        The year that was set.
     * @param monthOfYear The month that was set (0-11) for compatibility
     *                    with {@link Calendar}.
     * @param dayOfMonth  The day of the month that was set.
     */
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mAliPaymentModel.getPaymentTime());
        calendar.set(year, monthOfYear, dayOfMonth);

        mAliPaymentModel.setPaymentTime(calendar.getTimeInMillis());
        if (mModelMobileChangeListener != null) {

            mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
        }

        tvCreateDate.setText(TimeUtils.millis2String(calendar.getTimeInMillis(), TimeUtils.DEFAULT_PATTERN_5));
    }

    /**
     * @param view      The view associated with this listener.
     * @param hourOfDay The hour that was set.
     * @param minute    The minute that was set.
     * @param second    The second that was set
     */
    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mAliPaymentModel.getPaymentTime());
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get
                (Calendar.DAY_OF_MONTH), hourOfDay, minute, second);


        mAliPaymentModel.setPaymentTime(calendar.getTimeInMillis());

        if (mModelMobileChangeListener != null) {

            mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
        }



        tvCreateTime.setText(TimeUtils.millis2String(calendar.getTimeInMillis(), TimeUtils.DEFAULT_PATTERN_4));
    }

    @OnClick(R.id.tvCreateDate2)
    void paymentTimeClick() {


        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(mAliPaymentModel.getPaymentTime());


        mDatePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get
                (Calendar.DAY_OF_MONTH));

        mDatePickerDialog.show(getActivity().getFragmentManager(), "Datepickerdialog");

    }

    @OnClick(R.id.tvCreateTime2)
    void onBankHandleOverTimeClick() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mAliPaymentModel.getPaymentTime());
        mTimePickerDialog = TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        mTimePickerDialog.show(getActivity().getFragmentManager(), "mTimePickerDialog30");


    }


    @OnTextChanged(value = R.id.etMoney, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterPaymentTypeTextChanged(Editable s) {
        if (TextUtils.isEmpty(s.toString())) {
            return;
        }

        mAliPaymentModel.setReceiptMoney(new BigDecimal(s.toString()));
    }


    @OnTextChanged(value = R.id.etReceiptUserName, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterReceiptUserNameTextChanged(Editable s) {
        if (TextUtils.isEmpty(s.toString())) {
            return;
        }

        mAliPaymentModel.setReceiptUserName(s.toString());
    }

    @OnTextChanged(value = R.id.etBankNo, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterBankNoTextChanged(Editable s) {
        if (TextUtils.isEmpty(s.toString())) {
            return;
        }

        mAliPaymentModel.setBankNo(s.toString());
    }


    @OnTextChanged(value = R.id.etRemark, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void afterRemarkChanged(Editable s) {
        if (TextUtils.isEmpty(s.toString())) {
            return;
        }

        mAliPaymentModel.setRemark(s.toString());
    }

    @OnCheckedChanged(R.id.ckType)
    void onChecked(boolean checked) {
        mAliPaymentModel.setFinish(checked);
    }


    MobileChangeListener<AliPaymentModel> mModelMobileChangeListener;

    public void setMobileChangeListener(MobileChangeListener<AliPaymentModel> modelMobileChangeListener) {
        this.mModelMobileChangeListener = modelMobileChangeListener;
    }
}
