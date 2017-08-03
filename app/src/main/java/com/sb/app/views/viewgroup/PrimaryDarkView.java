package com.sb.app.views.viewgroup;


import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.widget.RelativeLayout;

import com.sb.app.R;
import com.sb.app.model.base.BaseMobileModel;
import com.sb.app.utils.TimeUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文件名称：{@link PrimaryDarkView}
 * <br/>
 * 功能描述：顶部标题栏视图
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/1 17:08
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/1 17:08
 * <br/>
 * 修改备注：
 */
public class PrimaryDarkView extends RelativeLayout {


    Context mContext;//上下文
    boolean alreadyInflated = false;


    @BindView(R.id.tvWifi)
    AppCompatImageView tvWifi;

    @BindView(R.id.topDateTime)
    AppCompatTextView topDateTime;
    @BindView(R.id.signal)
    AppCompatImageView signal;


    @BindView(R.id.tvBattery)
    AppCompatImageView tvBattery;


    /**
     * 静态视图绑定
     *
     * @param context 上下文
     * @return
     */
    public static PrimaryDarkView build(Context context) {
        PrimaryDarkView instance = new PrimaryDarkView(context);
        instance.onFinishInflate();
        return instance;
    }

    public PrimaryDarkView(Context context) {
        super(context);
        mContext = context;
    }

    String ampmText;

    public void binder(BaseMobileModel aliPaymentModel) {


        if (!aliPaymentModel.getDateTimeStyle()) {
            Calendar mCalendar = Calendar.getInstance();
            mCalendar.setTimeInMillis(TimeUtils.millis2millis(aliPaymentModel.getTopTime(), TimeUtils.DEFAULT_PATTERN));
            int apm = mCalendar.get(Calendar.AM_PM);


            int hour = mCalendar.get(Calendar.HOUR_OF_DAY);

            if (hour < 1) {
                ampmText = "午夜 ";
            } else if (hour < 12) {
                ampmText = "上午 ";
            } else if (hour < 13) {
                ampmText = "中午 ";
            } else {
                ampmText = "下午 ";
            }


            if (apm == 1) {
                topDateTime.setText(ampmText + TimeUtils.millis2String(mCalendar.getTimeInMillis(), TimeUtils
                        .DEFAULT_PATTERN_4_1));

            } else {
                topDateTime.setText(ampmText + TimeUtils.millis2String(mCalendar.getTimeInMillis(), TimeUtils
                        .DEFAULT_PATTERN_4_1));
            }
        } else {
            topDateTime.setText(TimeUtils.millis2String(aliPaymentModel.getTopTime(), TimeUtils.DEFAULT_PATTERN_4));
        }


        switch (aliPaymentModel.getNetworkType()) {
            case 10:
                tvWifi.setImageResource(R.mipmap.ic_top_network_wifi);
                break;
            case 20:
                tvWifi.setImageResource(R.mipmap.ic_top_network_g);
                break;
            case 30:
                tvWifi.setImageResource(R.mipmap.ic_top_network_e);
                break;
            case 40:
                tvWifi.setImageResource(R.mipmap.ic_top_network_3g);
                break;
            case 50:
                tvWifi.setImageResource(R.mipmap.ic_top_network_4g);
                break;
        }


        switch (aliPaymentModel.getNetworkSignal()) {
            case 10:
                signal.setImageResource(R.mipmap.ic_top_signal1);
                break;
            case 20:
                signal.setImageResource(R.mipmap.ic_top_signal2);
                break;
            case 30:
                signal.setImageResource(R.mipmap.ic_top_signal3);
                break;
            case 40:
                signal.setImageResource(R.mipmap.ic_top_signal4);
                break;
            case 50:
                signal.setImageResource(R.mipmap.ic_top_signal5);
                break;
        }


        switch (aliPaymentModel.getBatteryNumBar()) {
            case 0:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_0);
                break;
            case 4:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_4);
                break;

            case 8:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_8);
                break;
            case 12:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_12);
                break;
            case 16:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_16);
                break;
            case 20:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_20);
                break;
            case 24:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_24);
                break;
            case 28:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_28);
                break;
            case 32:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_32);
                break;
            case 36:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_36);
                break;
            case 40:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_40);
                break;
            case 44:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_44);
                break;

            case 48:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_48);
                break;
            case 52:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_52);
                break;
            case 56:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_56);
                break;
            case 60:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_60);
                break;
            case 64:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_64);
                break;

            case 68:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_68);
                break;
            case 72:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_72);
                break;
            case 76:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_76);
                break;
            case 80:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_80);
                break;
            case 84:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_84);
                break;
            case 88:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_88);
                break;
            case 92:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_92);
                break;
            case 96:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_96);
                break;
            case 100:
                tvBattery.setImageResource(R.mipmap.ic_top_batery_100);
                break;
        }


    }


    /**
     * The malreadyInflated hack is needed because of an Android bug
     * which leads to infinite calls of onFinishInflate()
     * when inflating a layout with a parent and using
     * the <merge /> tag.
     */
    @Override
    public void onFinishInflate() {
        if (!alreadyInflated) {
            alreadyInflated = true;
            inflate(getContext(), R.layout.google_primary_dark, this);
            ButterKnife.bind(this);
        }
        super.onFinishInflate();
    }


}
