package com.sb.app.views.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.R;
import com.sb.app.utils.ToastUtils;
import com.sb.app.views.listeners.VoiceClickListener;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2017/8/1.
 */

public class BottomSheetVoiceFragment extends BottomSheetDialogFragment {


    @BindView(R.id.sbVoice)
    AppCompatSeekBar mSbVoice;
    @BindView(R.id.tvCurrentVoiceTime)
    AppCompatTextView mTvCurrentVoiceTime;
    @BindView(R.id.btnHandle)
    AppCompatButton mBtnHandle;
    @BindView(R.id.btnCancelHandle)
    AppCompatButton mBtnCancelHandle;


    public BottomSheetVoiceFragment() {

    }


    public static BottomSheetVoiceFragment newInstance() {
        BottomSheetVoiceFragment fragment = new BottomSheetVoiceFragment();


        return fragment;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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

        View mRootView = inflater.inflate(R.layout.fragment_voice, container, false);
        ButterKnife.bind(this, mRootView);//绑定framgent



        mSbVoice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                mTvCurrentVoiceTime.setText(String.format(mTvCurrentVoiceTime.getTag().toString(),i));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return mRootView;
    }


    @OnClick(R.id.btnHandle)
    void onHandleClick() {

        if (mSbVoice.getProgress() < 1) {
            ToastUtils.alert(getActivity(), "语音时间至少大于1秒");
            return;
        }


        if (mVoiceClickListener != null) {
            mVoiceClickListener.onVoiceClickListener(mSbVoice.getProgress());
            this.dismiss();
        }

    }


    VoiceClickListener mVoiceClickListener;

    public void setVoiceClickListener(VoiceClickListener voiceClickListener) {
        mVoiceClickListener = voiceClickListener;
    }


}
