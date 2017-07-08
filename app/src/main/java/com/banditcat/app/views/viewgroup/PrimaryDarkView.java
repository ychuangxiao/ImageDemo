package com.banditcat.app.views.viewgroup;


import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.widget.RelativeLayout;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.banditcat.app.R;
import com.banditcat.app.model.AliPaymentModel;
import com.banditcat.app.utils.TimeUtils;

/**
 * 文件名称：{@link PrimaryDarkView}
 * <br/>
 * 功能描述：顶部标题栏视图
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/1 17:08
 * <br/>
 * 修改作者：banditcat
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
    public void binder(AliPaymentModel aliPaymentModel) {


        if (!aliPaymentModel.getDateTimeStyle()) {
            Calendar mCalendar = Calendar.getInstance();
            mCalendar.setTimeInMillis(TimeUtils.millis2millis(aliPaymentModel.getTopTime(),TimeUtils.DEFAULT_PATTERN));
            int apm = mCalendar.get(Calendar.AM_PM);


            int hour = mCalendar.get(Calendar.HOUR_OF_DAY);

            if (hour<1)
            {
                ampmText = "午夜 ";
            }
            else  if (hour<12)
            {
                ampmText = "上午 ";
            }
            else  if (hour<13)
            {
                ampmText = "中午 ";
            }
            else
            {
                ampmText = "下午 ";
            }


            if (apm == 1) {
                topDateTime.setText(ampmText + TimeUtils.millis2String(mCalendar.getTimeInMillis(), TimeUtils.DEFAULT_PATTERN_4_1));

            } else {
                topDateTime.setText(ampmText + TimeUtils.millis2String(mCalendar.getTimeInMillis(), TimeUtils.DEFAULT_PATTERN_4_1));
            }
        } else {
            topDateTime.setText(TimeUtils.millis2String(aliPaymentModel.getTopTime(), TimeUtils.DEFAULT_PATTERN_4));
        }




        switch (aliPaymentModel.getNetworkType())
        {
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



        switch (aliPaymentModel.getNetworkSignal())
        {
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
