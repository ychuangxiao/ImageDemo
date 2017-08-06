package com.sb.app.views.viewgroup;


import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RelativeLayout;

import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.model.base.BaseMobileModel;
import com.sb.app.utils.TimeUtils;
import com.sb.app.utils.ViewUtils;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.MobileStyleRealm;

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

    @BindView(R.id.topStatusContainer)
    ConstraintLayout mTopStatusContainer;
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


    public void binder(MobileStyleRealm mobileStyleRealm) {
        if (!mobileStyleRealm.getDate24TimeStyle()) {
            Calendar mCalendar = Calendar.getInstance();
            mCalendar.setTimeInMillis(TimeUtils.millis2millis(mobileStyleRealm.getTopTime(),
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
            topDateTime.setText(TimeUtils.millis2String(mobileStyleRealm.getTopTime(), TimeUtils
                    .DEFAULT_PATTERN_4));
        }


        if (mobileStyleRealm.getDir()) {
            tvDir.setVisibility(View.VISIBLE);
        } else {
            tvDir.setVisibility(View.GONE);
        }

        if (mobileStyleRealm.getLocation()) {
            tvLocation.setVisibility(View.VISIBLE);
        } else {
            tvLocation.setVisibility(View.GONE);
        }


        if (mobileStyleRealm.getBlueTeeth()) {
            tvBlueTeeth.setVisibility(View.VISIBLE);
        } else {
            tvBlueTeeth.setVisibility(View.GONE);
        }
        if (mobileStyleRealm.getBatteryNum()) {
            batteryNum.setText(String.valueOf(mobileStyleRealm.getBatteryNumBar()) + "%");
            batteryNum.setVisibility(View.VISIBLE);
        } else {
            batteryNum.setVisibility(View.GONE);
        }


        //判断是白色 还是 黑色

        if (mobileStyleRealm.getTopStatusColor() == R.color.colorWhite) {


            mTopStatusContainer.setBackground(mContext.getResources().getDrawable(R.color.colorWhite));

            if (mobileStyleRealm.getBatteryAdd()) {

                if (mobileStyleRealm.getBatteryNumBar() < 20) {

                    battery.setImageResource(R.drawable.battery_red_black);
                } else {
                    //是否充电
                    battery.setImageResource(R.drawable.battery_green_black);
                }
                dischargeImageView.setImageResource(R.mipmap.ic_discharge_black);
                dischargeImageView.setVisibility(View.VISIBLE);
            } else {
                dischargeImageView.setVisibility(View.GONE);


                if (mobileStyleRealm.getBatteryNumBar() < 20) {

                    battery.setImageResource(R.drawable.battery_red_black);
                } else {
                    battery.setImageResource(R.drawable.battery_black);
                }


            }


            switch (mobileStyleRealm.getNetworkSignal()) {
                case TextConstant.NETWORK_SIGNAL_1:
                    signal.setImageResource(R.mipmap.ic_ios_top_signal1);
                    break;
                case TextConstant.NETWORK_SIGNAL_2:
                    signal.setImageResource(R.mipmap.ic_ios_top_signal1);
                    break;
                case TextConstant.NETWORK_SIGNAL_3:
                    signal.setImageResource(R.mipmap.ic_ios_top_signal3);
                    break;
                case TextConstant.NETWORK_SIGNAL_4:
                    signal.setImageResource(R.mipmap.ic_ios_top_signal4);
                    break;
                case TextConstant.NETWORK_SIGNAL_5:
                    signal.setImageResource(R.mipmap.ic_ios_top_signal5);
                    break;
            }
            switch (mobileStyleRealm.getNetworkType()) {
                case TextConstant.NETWORK_TYPE_WIFI:
                    tvWifi.setImageResource(R.mipmap.ic_top_black_network_wifi);
                    break;
                case TextConstant.NETWORK_TYPE_G:
                    tvWifi.setImageResource(R.mipmap.ic_top_black_network_g);
                    break;
                case TextConstant.NETWORK_TYPE_E:
                    tvWifi.setImageResource(R.mipmap.ic_top_black_network_e);
                    break;
                case TextConstant.NETWORK_TYPE_3G:
                    tvWifi.setImageResource(R.mipmap.ic_top_black_network_3g);
                    break;
                case TextConstant.NETWORK_TYPE_4G:
                    tvWifi.setImageResource(R.mipmap.ic_top_black_network_4g);
                    break;
            }
            tvLocation.setImageResource(R.mipmap.ic_ios_top_location);
            tvDir.setImageResource(R.mipmap.ic_ios_top_dir);
            tvBlueTeeth.setImageResource(R.mipmap.ic_ios_top_blueth);

            tvMobileType.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
            batteryNum.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
            topDateTime.setTextColor(mContext.getResources().getColor(R.color.colorBlack));

        } else {



            topDateTime.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            tvMobileType.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            batteryNum.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            dischargeImageView.setImageResource(R.mipmap.ic_discharge_white);
            //是否充电
            if (mobileStyleRealm.getBatteryAdd()) {

                dischargeImageView.setVisibility(View.VISIBLE);

                if (mobileStyleRealm.getBatteryNumBar() < 20) {

                    battery.setImageResource(R.drawable.battery_red_white);
                } else {
                    //是否充电
                    battery.setImageResource(R.drawable.battery_green_white);
                }


            } else {
                dischargeImageView.setVisibility(View.GONE);

                //小于20 显示红色
                if (mobileStyleRealm.getBatteryNumBar() < 20) {

                    battery.setImageResource(R.drawable.battery_red_white);
                } else {
                    battery.setImageResource(R.drawable.battery_white);
                }

            }
            switch (mobileStyleRealm.getNetworkSignal()) {
                case TextConstant.NETWORK_SIGNAL_1:
                    signal.setImageResource(R.mipmap.ic_ios_white_top_signal1);
                    break;
                case TextConstant.NETWORK_SIGNAL_2:
                    signal.setImageResource(R.mipmap.ic_ios_white_top_signal1);
                    break;
                case TextConstant.NETWORK_SIGNAL_3:
                    signal.setImageResource(R.mipmap.ic_ios_white_top_signal3);
                    break;
                case TextConstant.NETWORK_SIGNAL_4:
                    signal.setImageResource(R.mipmap.ic_ios_white_top_signal4);
                    break;
                case TextConstant.NETWORK_SIGNAL_5:
                    signal.setImageResource(R.mipmap.ic_ios_white_top_signal5);
                    break;
            }
            switch (mobileStyleRealm.getNetworkType()) {
                case TextConstant.NETWORK_TYPE_WIFI:
                    tvWifi.setImageResource(R.mipmap.ic_ios_white_top_network_wifi);
                    break;
                case TextConstant.NETWORK_TYPE_G:
                    tvWifi.setImageResource(R.mipmap.ic_ios_white_top_network_g);
                    break;
                case TextConstant.NETWORK_TYPE_E:
                    tvWifi.setImageResource(R.mipmap.ic_ios_white_top_network_e);
                    break;
                case TextConstant.NETWORK_TYPE_3G:
                    tvWifi.setImageResource(R.mipmap.ic_ios_white_top_network_3g);
                    break;
                case TextConstant.NETWORK_TYPE_4G:
                    tvWifi.setImageResource(R.mipmap.ic_ios_white_top_network_4g);
                    break;
            }
            tvLocation.setImageResource(R.mipmap.ic_ios_white_top_location);
            tvDir.setImageResource(R.mipmap.ic_ios_white_top_dir);
            tvBlueTeeth.setImageResource(R.mipmap.ic_ios_white_top_blueth);
        }

        mTopStatusContainer.setBackground(mContext.getResources().getDrawable(mobileStyleRealm.getTopStatusColor()));
        LayerDrawable layerDrawableTest = (LayerDrawable) battery.getDrawable();
        clipDrawableTest = (ClipDrawable) layerDrawableTest
                .findDrawableByLayerId(R.id.clipDrawable);


        clipDrawableTest.setLevel(calculateLevel(mobileStyleRealm.getBatteryNumBar()));

        tvMobileType.setText(mobileStyleRealm.getMobileCarrier());
    }

    public void binder(BaseMobileModel aliPaymentModel) {


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

        if (aliPaymentModel.getTopStatusColor().compareTo(AppConstant.ACTION_10) == 0) {


            mTopStatusContainer.setBackground(mContext.getResources().getDrawable(R.color.colorWhite));

            if (aliPaymentModel.getBatteryAdd()) {

                if (aliPaymentModel.getBatteryNumBar() < 20) {

                    battery.setImageResource(R.drawable.battery_red_black);
                } else {
                    //是否充电
                    battery.setImageResource(R.drawable.battery_green_black);
                }
                dischargeImageView.setImageResource(R.mipmap.ic_discharge_black);
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
                    signal.setImageResource(R.mipmap.ic_ios_top_signal1);
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
            switch (aliPaymentModel.getNetworkType()) {
                case 10:
                    tvWifi.setImageResource(R.mipmap.ic_top_black_network_wifi);
                    break;
                case 20:
                    tvWifi.setImageResource(R.mipmap.ic_top_black_network_g);
                    break;
                case 30:
                    tvWifi.setImageResource(R.mipmap.ic_top_black_network_e);
                    break;
                case 40:
                    tvWifi.setImageResource(R.mipmap.ic_top_black_network_3g);
                    break;
                case 50:
                    tvWifi.setImageResource(R.mipmap.ic_top_black_network_4g);
                    break;
            }
            tvLocation.setImageResource(R.mipmap.ic_ios_top_location);
            tvDir.setImageResource(R.mipmap.ic_ios_top_dir);
            tvBlueTeeth.setImageResource(R.mipmap.ic_ios_top_blueth);

            tvMobileType.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
            batteryNum.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
            topDateTime.setTextColor(mContext.getResources().getColor(R.color.colorBlack));

        } else {


            mTopStatusContainer.setBackground(mContext.getResources().getDrawable(R.color.colorPrimaryForWeChat));
            topDateTime.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            tvMobileType.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            batteryNum.setTextColor(mContext.getResources().getColor(R.color.colorWhite));
            dischargeImageView.setImageResource(R.mipmap.ic_discharge_white);
            //是否充电
            if (aliPaymentModel.getBatteryAdd()) {

                dischargeImageView.setVisibility(View.VISIBLE);

                if (aliPaymentModel.getBatteryNumBar() < 20) {

                    battery.setImageResource(R.drawable.battery_red_white);
                } else {
                    //是否充电
                    battery.setImageResource(R.drawable.battery_green_white);
                }


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

            switch (aliPaymentModel.getNetworkType()) {
                case 10:
                    tvWifi.setImageResource(R.mipmap.ic_ios_white_top_network_wifi);
                    break;
                case 20:
                    tvWifi.setImageResource(R.mipmap.ic_ios_white_top_network_g);
                    break;
                case 30:
                    tvWifi.setImageResource(R.mipmap.ic_ios_white_top_network_e);
                    break;
                case 40:
                    tvWifi.setImageResource(R.mipmap.ic_ios_white_top_network_3g);
                    break;
                case 50:
                    tvWifi.setImageResource(R.mipmap.ic_ios_white_top_network_4g);
                    break;
            }
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
