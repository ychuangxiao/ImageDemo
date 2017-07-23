package com.sb.app.views.viewgroup;


import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RelativeLayout;

import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.model.AliPaymentModel;
import com.sb.app.utils.TimeUtils;
import com.sb.app.utils.ViewUtils;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文件名称：{@link PrimaryDarkIosView}
 * <br/>
 * 功能描述：顶部标题栏视图(IOS)
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
public class PrimaryDarkIosView extends RelativeLayout {

    private ClipDrawable clipDrawableTest;
    Context mContext;//上下文
    boolean alreadyInflated = false;


    @BindView(R.id.tvMobileType)
    AppCompatTextView tvMobileType;
    @BindView(R.id.tvWifi)
    AppCompatImageView tvWifi;

    @BindView(R.id.topDateTime)
    AppCompatTextView topDateTime;
    @BindView(R.id.signal)
    AppCompatImageView signal;

    @BindView(R.id.batteryNum)
    AppCompatTextView batteryNum;


    @BindView(R.id.tvLocation)
    AppCompatImageView tvLocation;

    @BindView(R.id.dischargeImageView)
    AppCompatImageView dischargeImageView;


    @BindView(R.id.battery)
    AppCompatImageView battery;


    @BindView(R.id.tvDir)
    AppCompatImageView tvDir;

    @BindView(R.id.tvBlueTeeth)
    AppCompatImageView tvBlueTeeth;


    /**
     * 静态视图绑定
     *
     * @param context 上下文
     * @return
     */
    public static PrimaryDarkIosView build(Context context) {
        PrimaryDarkIosView instance = new PrimaryDarkIosView(context);
        instance.onFinishInflate();
        return instance;
    }

    public PrimaryDarkIosView(Context context) {
        super(context);
        mContext = context;
    }

    String ampmText;

    public void binder(AliPaymentModel aliPaymentModel) {


        if (!aliPaymentModel.getDateTimeStyle()) {
            Calendar mCalendar = Calendar.getInstance();
            mCalendar.setTimeInMillis(TimeUtils.millis2millis(aliPaymentModel.getTopTime(),
                    TimeUtils.DEFAULT_PATTERN));
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
                topDateTime.setText(ampmText + TimeUtils.millis2String(mCalendar.getTimeInMillis
                        (), TimeUtils
                        .DEFAULT_PATTERN_4_1));

            } else {
                topDateTime.setText(ampmText + TimeUtils.millis2String(mCalendar.getTimeInMillis
                        (), TimeUtils
                        .DEFAULT_PATTERN_4_1));
            }
        } else {
            topDateTime.setText(TimeUtils.millis2String(aliPaymentModel.getTopTime(), TimeUtils
                    .DEFAULT_PATTERN_4));
        }


        if (aliPaymentModel.getDir()) {
            tvDir.setVisibility(View.VISIBLE);
        } else {
            tvDir.setVisibility(View.GONE);
        }

        if (aliPaymentModel.getLocation()) {
            tvLocation.setVisibility(View.VISIBLE);
        } else {
            tvLocation.setVisibility(View.GONE);
        }


        if (aliPaymentModel.getBlueTeeth()) {
            tvBlueTeeth.setVisibility(View.VISIBLE);
        } else {
            tvBlueTeeth.setVisibility(View.GONE);
        }
        if (aliPaymentModel.getBatteryNum()) {
            batteryNum.setText(String.valueOf(aliPaymentModel.getBatteryNumBar()) + "%");
            batteryNum.setVisibility(View.VISIBLE);
        } else {
            batteryNum.setVisibility(View.GONE);
        }


        //判断是白色 还是 黑色

        if (aliPaymentModel.getMobileType().compareTo(AppConstant.ACTION_10) == 0) {


            if (aliPaymentModel.getBatteryAdd()) {

                if (aliPaymentModel.getBatteryNumBar() < 20) {

                    battery.setImageResource(R.drawable.battery_red_black);
                } else {
                    //是否充电
                    battery.setImageResource(R.drawable.battery_green_black);
                }

                dischargeImageView.setVisibility(View.VISIBLE);
            } else {
                dischargeImageView.setVisibility(View.GONE);


                if (aliPaymentModel.getBatteryNumBar() < 20) {

                    battery.setImageResource(R.drawable.battery_red_black);
                } else {
                    battery.setImageResource(R.drawable.battery_black);
                }


            }


            switch (aliPaymentModel.getNetworkSignal()) {
                case 10:
                    signal.setImageResource(R.mipmap.ic_ios_top_signal1);
                    break;
                case 20:
                    signal.setImageResource(R.mipmap.ic_ios_top_signal2);
                    break;
                case 30:
                    signal.setImageResource(R.mipmap.ic_ios_top_signal3);
                    break;
                case 40:
                    signal.setImageResource(R.mipmap.ic_ios_top_signal4);
                    break;
                case 50:
                    signal.setImageResource(R.mipmap.ic_ios_top_signal5);
                    break;
            }

            tvWifi.setImageResource(R.mipmap.ic_ios_top_network_wifi);
            tvLocation.setImageResource(R.mipmap.ic_ios_top_location);
            tvDir.setImageResource(R.mipmap.ic_ios_top_dir);
            tvBlueTeeth.setImageResource(R.mipmap.ic_ios_top_blueth);
        } else {
            //是否充电
            if (aliPaymentModel.getBatteryAdd()) {
                battery.setImageResource(R.drawable.battery_green_white);
                dischargeImageView.setVisibility(View.VISIBLE);
            } else {
                dischargeImageView.setVisibility(View.GONE);

                //小于20 显示红色
                if (aliPaymentModel.getBatteryNumBar() < 20) {

                    battery.setImageResource(R.drawable.battery_red_white);
                } else {
                    battery.setImageResource(R.drawable.battery_white);
                }

            }

            switch (aliPaymentModel.getNetworkSignal()) {
                case 10:
                    signal.setImageResource(R.mipmap.ic_ios_white_top_signal1);
                    break;
                case 20:
                    signal.setImageResource(R.mipmap.ic_ios_white_top_signal2);
                    break;
                case 30:
                    signal.setImageResource(R.mipmap.ic_ios_white_top_signal3);
                    break;
                case 40:
                    signal.setImageResource(R.mipmap.ic_ios_white_top_signal4);
                    break;
                case 50:
                    signal.setImageResource(R.mipmap.ic_ios_white_top_signal5);
                    break;
            }

            tvWifi.setImageResource(R.mipmap.ic_ios_white_top_network_wifi);
            tvLocation.setImageResource(R.mipmap.ic_ios_white_top_location);
            tvDir.setImageResource(R.mipmap.ic_ios_white_top_dir);
            tvBlueTeeth.setImageResource(R.mipmap.ic_ios_white_top_blueth);
        }


        LayerDrawable layerDrawableTest = (LayerDrawable) battery.getDrawable();
        clipDrawableTest = (ClipDrawable) layerDrawableTest
                .findDrawableByLayerId(R.id.clipDrawable);


        clipDrawableTest.setLevel(calculateLevel(aliPaymentModel.getBatteryNumBar()));

        tvMobileType.setText(aliPaymentModel.getMobileCarrier());
    }

    /**
     * 根据自己的电池图标做响应的调整
     *
     * @param progress 0-100
     * @return 0-10000
     */
    private int calculateLevel(int progress) {
        int leftOffest = ViewUtils.dip2px(mContext, 2);
        int powerLength = ViewUtils.dip2px(mContext, 26.5f);// 40 px in hdpi
        int totalLength = ViewUtils.dip2px(mContext, 32.5f);// 49 px in hdpi
        int level = (leftOffest + powerLength * progress / 100) * 10000 / totalLength;
        return level;
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
            inflate(getContext(), R.layout.ios_primary_dark, this);
            ButterKnife.bind(this);
        }
        super.onFinishInflate();
    }


}
