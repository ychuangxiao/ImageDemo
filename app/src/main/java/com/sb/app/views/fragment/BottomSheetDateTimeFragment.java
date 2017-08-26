package com.sb.app.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sb.common.utils.StringUtils;
import com.sb.app.R;
import com.sb.app.model.EditModel;
import com.sb.app.utils.TimeUtils;
import com.sb.app.utils.ToastUtils;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.listeners.DateClickListener;
import com.sb.common.fontawesom.typeface.BaseFontAwesome;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2017/8/1.
 */

public class BottomSheetDateTimeFragment extends BottomSheetDialogFragment implements DatePickerDialog
        .OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @BindView(R.id.tvDate)
    AppCompatTextView mTvDate;
    @BindView(R.id.tvTime)
    AppCompatTextView mTvTime;
    @BindView(R.id.btnHandle)
    AppCompatButton mBtnHandle;


    DatePickerDialog mDatePickerDialog;

    TimePickerDialog mTimePickerDialog;
    @BindView(R.id.tvTitle)
    AppCompatTextView mTvTitle;
    EditModel mEditModel;
    private static final String ARG_PARAM1 = "param1";
    public BottomSheetDateTimeFragment() {

    }


    public static BottomSheetDateTimeFragment newInstance(EditModel model) {
        BottomSheetDateTimeFragment fragment = new BottomSheetDateTimeFragment();

        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, model);

        fragment.setArguments(args);
        return fragment;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mEditModel = getArguments().getParcelable(ARG_PARAM1);

        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }



    public void refreshData(EditModel editModel) {

        mTvTitle.setText(editModel.getTitle());


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {

        View mRootView = inflater.inflate(R.layout.fragment_date_time, container, false);
        ButterKnife.bind(this, mRootView);//绑定framgent


        ViewUtils.setCompoundRightDrawables(getContext(), mTvDate, BaseFontAwesome.Icon.icon_date, getResources()
                .getColor(R.color
                        .colorAccent), 8f);

        ViewUtils.setCompoundRightDrawables(getContext(), mTvTime, BaseFontAwesome.Icon.icon_time, getResources()
                .getColor(R.color
                        .colorAccent), 8f);


        refreshData(mEditModel);
        return mRootView;
    }


    @OnClick(R.id.tvDate)
    void onDateClick() {
        if (mDatePickerDialog == null) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            mDatePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH), calendar.get(Calendar
                            .DAY_OF_MONTH));


        }
        mDatePickerDialog.show(getActivity().getFragmentManager(), "Datepickerdialog");
    }

    @OnClick(R.id.btnCancelHandle)
    void onCancelClick() {
        this.dismiss();
    }


    @OnClick(R.id.tvTime)
    void onTimeClick() {
        if (mTimePickerDialog == null) {

            Calendar calendar = Calendar.getInstance();

            calendar.setTimeInMillis(System.currentTimeMillis());

            mTimePickerDialog = TimePickerDialog.newInstance(this, calendar.get(Calendar
                    .HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);


        }
        mTimePickerDialog.show(getActivity().getFragmentManager(), "Datepickerdialog");
    }

    @OnClick(R.id.btnHandle)
    void onHandleClick() {

        if (StringUtils.isEmpty(mTvDate.getText().toString())) {
            ToastUtils.alert(getActivity(), "日期不能为空");
            return;
        }

        if (StringUtils.isEmpty(mTvTime.getText().toString())) {
            ToastUtils.alert(getActivity(), "时间不能为空");
            return;
        }

        if (mDateClickListener != null) {
            mDateClickListener.onItemClickListener(TimeUtils.string2Millis(String.format("%s %s", mTvDate.getText()
                    .toString(), mTvTime.getText().toString()), TimeUtils.DEFAULT_PATTERN_2));
            this.dismiss();
        }

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

        calendar.set(year, monthOfYear, dayOfMonth);


        mTvDate.setText(TimeUtils.millis2String(calendar.getTimeInMillis(), TimeUtils.DEFAULT_PATTERN_5));

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

        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar
                        .get(Calendar.DAY_OF_MONTH), hourOfDay, minute,
                second);

        mTvTime.setText(TimeUtils.millis2String(calendar.getTimeInMillis(), TimeUtils.DEFAULT_PATTERN_4));
    }


    DateClickListener mDateClickListener;


    public void setDateClickListener(DateClickListener dateClickListener) {
        mDateClickListener = dateClickListener;
    }


}
