package com.sb.app.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.R;
import com.sb.app.model.EditModel;
import com.sb.app.utils.ToastUtils;
import com.sb.app.views.listeners.ContactClickListener;
import com.sb.app.views.widget.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2017/8/1.
 */

public class BottomSheetEditSignFragment extends BottomSheetDialogFragment {


    @BindView(R.id.etAmount)
    ClearEditText mEtAmount;
    @BindView(R.id.btnHandle)
    AppCompatButton mBtnHandle;
    @BindView(R.id.btnCancelHandle)
    AppCompatButton mBtnCancelHandle;

    @BindView(R.id.tvTitle)
    AppCompatTextView tvTitle;

    EditModel mEditModel;
    private static final String ARG_PARAM1 = "param1";
    @BindView(R.id.amountTextInputLayout)
    TextInputLayout mAmountTextInputLayout;


    public BottomSheetEditSignFragment() {

    }


    public static BottomSheetEditSignFragment newInstance(EditModel model) {
        BottomSheetEditSignFragment fragment = new BottomSheetEditSignFragment();


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

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {

        View mRootView = inflater.inflate(R.layout.fragment_edit_sign, container, false);
        ButterKnife.bind(this, mRootView);//绑定framgent

        refreshData(mEditModel);
        return mRootView;
    }


    public void refreshData(EditModel editModel) {
        InputFilter[] filters = {new InputFilter.LengthFilter(editModel.getMaxLength())};
        mEtAmount.setFilters(filters);
        tvTitle.setText(editModel.getTitle());

        mAmountTextInputLayout.setHint(editModel.getHintText());
        mEtAmount.setHint(editModel.getHintText());
        mEtAmount.setText(editModel.getText());
        mEtAmount.setSelection(editModel.getText().length());

        mEtAmount.setInputType(editModel.getInputType());



    }


    @OnClick(R.id.btnCancelHandle)
    void onCancelClick() {
        this.dismiss();
    }


    @OnClick(R.id.btnHandle)
    void onHandleClick() {


        if (StringUtils.isEmpty(mEtAmount.getText().toString())) {
            ToastUtils.alert(getActivity(), String.format("%s不能为空", mEditModel.getHintText()));
            return;
        }


        if (mDoubleContactClickListener != null) {
            mDoubleContactClickListener.onItemClickListener(Double.parseDouble(mEtAmount.getText().toString()));

            dismiss();
        } else {
            dismiss();
        }

    }


    ContactClickListener<Double> mDoubleContactClickListener;


    public void setDoubleContactClickListener(ContactClickListener<Double> doubleContactClickListener) {
        mDoubleContactClickListener = doubleContactClickListener;
    }

}
