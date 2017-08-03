package com.sb.app.views.fragment;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.model.base.BaseMobileModel;
import com.sb.app.utils.TimeUtils;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.base.BaseFragment;
import com.sb.app.views.listeners.MobileChangeListener;
import com.sb.common.fontawesom.typeface.BaseFontAwesome;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

import static com.sb.app.constant.AppConstant.CARRIER_CHINA_DX;
import static com.sb.app.constant.AppConstant.CARRIER_CHINA_LT;
import static com.sb.app.constant.AppConstant.CARRIER_CHINA_YD;
import static com.sb.app.constant.AppConstant.MOBILE_ANDROID;
import static com.sb.app.constant.AppConstant.MOBILE_IOS;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link PaymentMobileStyleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentMobileStyleFragment extends BaseFragment implements TimePickerDialog
        .OnTimeSetListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    BottomNavigationView navigation;

    BaseMobileModel mBaseMobileModel;


    @BindView(R.id.tvCreateTime2)
    AppCompatTextView tvCreateTime;

    @BindView(R.id.tvMobileType)
    AppCompatTextView tvMobileType;

    @BindView(R.id.tvSignal)
    AppCompatTextView tvSignal;

    @BindView(R.id.tvNetwork)
    AppCompatTextView tvNetwork;

    @BindView(R.id.tvTopStyle)
    AppCompatTextView tvTopStyle;

    @BindView(R.id.ckDir)
    AppCompatCheckBox ckDir;
    @BindView(R.id.ckBattery)
    AppCompatCheckBox ckBattery;

    @BindView(R.id.ckBatteryNum)
    AppCompatCheckBox ckBatteryNum;

    @BindView(R.id.ckBlueTeeth)
    AppCompatCheckBox ckBlueTeeth;

    @BindView(R.id.ckLocation)
    AppCompatCheckBox ckLocation;

    @BindView(R.id.iosContainer)
    LinearLayout iosContainer;

    @BindView(R.id.carrierRelativeLayout)
    RelativeLayout carrierRelativeLayout;

    @BindView(R.id.seekBarBatteryNum)
    AppCompatSeekBar seekBarBatteryNum;

    @BindView(R.id.tvLeftBattery)
    AppCompatTextView tvLeftBattery;

    @BindView(R.id.tvCarrier)
    AppCompatTextView tvCarrier;//运营商


    //菜单

    PopupMenu mNetworkPopupMenu;//网络信号弹出框

    PopupMenu mSignalPopupMenu;//网络信号强度弹出框

    PopupMenu mMobileTypePopupMenu;//手机型号弹出框

    PopupMenu mToolStylePopupMenu;//顶部工具栏样式

    PopupMenu mCarrierPopupMenu;//运营商样式弹出框


    TimePickerDialog mTimePickerDialog;

    public PaymentMobileStyleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PaymentMenuFragment.
     */

    public static PaymentMobileStyleFragment newInstance(BaseMobileModel
                                                                 aliPaymentModel) {
        PaymentMobileStyleFragment fragment = new PaymentMobileStyleFragment();
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


        ViewUtils.setCompoundRightDrawables(getContext(), tvCreateTime, BaseFontAwesome.Icon
                .icon_right, getResources
                ().getColor(R.color
                .colorRightTitle), 4f);

        ViewUtils.setCompoundRightDrawables(getContext(), tvMobileType, BaseFontAwesome.Icon
                .icon_right, getResources
                ().getColor(R.color
                .colorRightTitle), 4f);

        ViewUtils.setCompoundRightDrawables(getContext(), tvSignal, BaseFontAwesome.Icon
                .icon_right, getResources()
                .getColor(R.color
                        .colorRightTitle), 4f);

        ViewUtils.setCompoundRightDrawables(getContext(), tvNetwork, BaseFontAwesome.Icon
                .icon_right, getResources()
                .getColor(R.color
                        .colorRightTitle), 4f);


        seekBarBatteryNum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                if (progress == 0) {
                    mBaseMobileModel.setBatteryNumBar(progress);
                } else if (progress > 0 && progress <= 4) {
                    mBaseMobileModel.setBatteryNumBar(4);
                } else if (progress > 4 && progress <= 8) {
                    mBaseMobileModel.setBatteryNumBar(8);
                } else if (progress > 8 && progress <= 12) {
                    mBaseMobileModel.setBatteryNumBar(12);
                } else if (progress > 12 && progress <= 16) {
                    mBaseMobileModel.setBatteryNumBar(16);
                } else if (progress > 16 && progress <= 20) {
                    mBaseMobileModel.setBatteryNumBar(20);
                } else if (progress > 20 && progress <= 24) {
                    mBaseMobileModel.setBatteryNumBar(24);
                } else if (progress > 24 && progress <= 28) {
                    mBaseMobileModel.setBatteryNumBar(28);
                } else if (progress > 28 && progress <= 32) {
                    mBaseMobileModel.setBatteryNumBar(32);
                } else if (progress > 32 && progress <= 36) {
                    mBaseMobileModel.setBatteryNumBar(36);
                } else if (progress > 36 && progress <= 40) {
                    mBaseMobileModel.setBatteryNumBar(40);
                } else if (progress > 40 && progress <= 44) {
                    mBaseMobileModel.setBatteryNumBar(44);
                } else if (progress > 44 && progress <= 48) {
                    mBaseMobileModel.setBatteryNumBar(48);
                } else if (progress > 48 && progress <= 52) {
                    mBaseMobileModel.setBatteryNumBar(52);
                } else if (progress > 52 && progress <= 56) {
                    mBaseMobileModel.setBatteryNumBar(56);
                } else if (progress > 56 && progress <= 60) {
                    mBaseMobileModel.setBatteryNumBar(60);
                } else if (progress > 60 && progress <= 64) {
                    mBaseMobileModel.setBatteryNumBar(44);
                } else if (progress > 64 && progress <= 68) {
                    mBaseMobileModel.setBatteryNumBar(48);
                } else if (progress > 68 && progress <= 72) {
                    mBaseMobileModel.setBatteryNumBar(72);
                } else if (progress > 72 && progress <= 76) {
                    mBaseMobileModel.setBatteryNumBar(76);
                } else if (progress > 76 && progress <= 80) {
                    mBaseMobileModel.setBatteryNumBar(80);
                } else if (progress > 80 && progress <= 84) {
                    mBaseMobileModel.setBatteryNumBar(84);
                } else if (progress > 84 && progress <= 88) {
                    mBaseMobileModel.setBatteryNumBar(88);
                } else if (progress > 88 && progress <= 92) {
                    mBaseMobileModel.setBatteryNumBar(92);
                } else if (progress > 92 && progress <= 99) {
                    mBaseMobileModel.setBatteryNumBar(96);
                } else if (progress == 100) {
                    mBaseMobileModel.setBatteryNumBar(progress);
                }


                tvLeftBattery.setText(String.format(tvLeftBattery.getTag().toString(),
                        mBaseMobileModel
                                .getBatteryNumBar()));

                if (mModelMobileChangeListener != null) {

                    mModelMobileChangeListener.onItemClickListener(mBaseMobileModel);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        loadViewData((BaseMobileModel) getArguments().getSerializable(ARG_PARAM1));

    }

    public void loadViewData(BaseMobileModel model) {
        if (model == null) {
            return;
        }

        mBaseMobileModel = model;


        tvCarrier.setText(mBaseMobileModel.getMobileCarrier());

        tvCreateTime.setText(TimeUtils.millis2String(mBaseMobileModel.getTopTime(), TimeUtils
                .DEFAULT_PATTERN_4));


        switch (mBaseMobileModel.getTopToolStyle()) {
            case AppConstant.ACTION_10:
                tvTopStyle.setText(AppConstant.TOOL_STYLE_CUSTOM);
                break;
            case AppConstant.ACTION_20:
                tvTopStyle.setText(AppConstant.TOOL_STYLE_SYSTEM);
                break;
        }


        switch (mBaseMobileModel.getMobileType()) {
            case AppConstant.ACTION_10:
                tvMobileType.setText(MOBILE_IOS);
                carrierRelativeLayout.setVisibility(View.VISIBLE);
                iosContainer.setVisibility(View.VISIBLE);


                break;
            case AppConstant.ACTION_20:
                tvMobileType.setText(MOBILE_ANDROID);
                iosContainer.setVisibility(View.GONE);
                carrierRelativeLayout.setVisibility(View.GONE);
                break;
        }

        switch (mBaseMobileModel.getNetworkSignal()) {
            case AppConstant.ACTION_10:
                tvSignal.setText(AppConstant.NETWORK_SIGNAL_1);
                break;
            case AppConstant.ACTION_20:
                tvSignal.setText(AppConstant.NETWORK_SIGNAL_2);
                break;
            case AppConstant.ACTION_30:
                tvSignal.setText(AppConstant.NETWORK_SIGNAL_3);
                break;
            case AppConstant.ACTION_40:
                tvSignal.setText(AppConstant.NETWORK_SIGNAL_4);
                break;
            case AppConstant.ACTION_50:
                tvSignal.setText(AppConstant.NETWORK_SIGNAL_5);
                break;
        }

        switch (mBaseMobileModel.getNetworkType()) {
            case AppConstant.ACTION_10:
                tvNetwork.setText(AppConstant.NETWORK_WIFI);
                break;
            case AppConstant.ACTION_20:
                tvNetwork.setText(AppConstant.NETWORK_G);
                break;
            case AppConstant.ACTION_30:
                tvNetwork.setText(AppConstant.NETWORK_E);
                break;
            case AppConstant.ACTION_40:
                tvNetwork.setText(AppConstant.NETWORK_3G);
                break;
            case AppConstant.ACTION_50:
                tvNetwork.setText(AppConstant.NETWORK_4G);
                break;
        }

        tvLeftBattery.setText(String.format(tvLeftBattery.getTag().toString(), mBaseMobileModel
                .getBatteryNumBar()));

        seekBarBatteryNum.setProgress(mBaseMobileModel
                .getBatteryNumBar());

        ckDir.setChecked(mBaseMobileModel.getDir());
        ckBattery.setChecked(mBaseMobileModel.getBatteryAdd());
        ckBatteryNum.setChecked(mBaseMobileModel.getBatteryNum());

        ckBlueTeeth.setChecked(mBaseMobileModel.getBlueTeeth());
        ckLocation.setChecked(mBaseMobileModel.getLocation());

    }


    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_mobile_style;
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
        calendar.setTimeInMillis(mBaseMobileModel.getTopTime());
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get
                        (Calendar.DAY_OF_MONTH),
                hourOfDay, minute, second);

        mBaseMobileModel.setTopTime(calendar.getTimeInMillis());
        if (mModelMobileChangeListener != null) {

            mModelMobileChangeListener.onItemClickListener(mBaseMobileModel);
        }


        tvCreateTime.setText(TimeUtils.millis2String(calendar.getTimeInMillis(), TimeUtils
                .DEFAULT_PATTERN_4));
    }


    @OnClick(R.id.tvCreateTime2)
    void onBankHandleOverTimeClick() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mBaseMobileModel.getTopTime());
        mTimePickerDialog = TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get
                        (Calendar.MINUTE), true);
        mTimePickerDialog.show(getActivity().getFragmentManager(), "mTimePickerDialog30");


    }

    @OnCheckedChanged(R.id.ckType)
    void onChecked(boolean checked) {

        mBaseMobileModel.setDateTimeStyle(checked);
        if (mModelMobileChangeListener != null) {

            mModelMobileChangeListener.onItemClickListener(mBaseMobileModel);
        }
    }

    @OnCheckedChanged(R.id.ckDir)
    void onDirChecked(boolean checked) {

        mBaseMobileModel.setDir(checked);
        if (mModelMobileChangeListener != null) {

            mModelMobileChangeListener.onItemClickListener(mBaseMobileModel);
        }
    }

    @OnCheckedChanged(R.id.ckBattery)
    void onBatteryChecked(boolean checked) {

        mBaseMobileModel.setBatteryAdd(checked);
        if (mModelMobileChangeListener != null) {

            mModelMobileChangeListener.onItemClickListener(mBaseMobileModel);
        }
    }


    @OnCheckedChanged(R.id.ckBatteryNum)
    void onBatteryNumChecked(boolean checked) {

        mBaseMobileModel.setBatteryNum(checked);
        if (mModelMobileChangeListener != null) {

            mModelMobileChangeListener.onItemClickListener(mBaseMobileModel);
        }
    }

    @OnCheckedChanged(R.id.ckBlueTeeth)
    void onBlueTeethChecked(boolean checked) {

        mBaseMobileModel.setBlueTeeth(checked);
        if (mModelMobileChangeListener != null) {

            mModelMobileChangeListener.onItemClickListener(mBaseMobileModel);
        }
    }

    @OnCheckedChanged(R.id.ckLocation)
    void onLocationChecked(boolean checked) {

        mBaseMobileModel.setLocation(checked);
        if (mModelMobileChangeListener != null) {

            mModelMobileChangeListener.onItemClickListener(mBaseMobileModel);
        }
    }


    @OnClick(R.id.tvTopStyle)
    void onTopStyleClick() {
        if (null == mToolStylePopupMenu) {
            mToolStylePopupMenu = new PopupMenu(getActivity(), tvTopStyle);
            mToolStylePopupMenu.getMenu().setGroupCheckable(0, true, true);

            int menuIndex = 0;
            mToolStylePopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.TOOL_STYLE_SYSTEM)
                    .setTitleCondensed(String.valueOf
                            (AppConstant.ACTION_20));
            mToolStylePopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.TOOL_STYLE_CUSTOM)
                    .setTitleCondensed(String.valueOf
                            (AppConstant.ACTION_10));


            mToolStylePopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {


                    mBaseMobileModel.setTopToolStyle(Integer.parseInt(item.getTitleCondensed()
                            .toString()));
                    if (mModelMobileChangeListener != null) {

                        mModelMobileChangeListener.onItemClickListener(mBaseMobileModel);
                    }

                    tvTopStyle.setText(item.getTitle());

                    return false;
                }
            });


        }
        mToolStylePopupMenu.show();
    }


    @OnClick(R.id.tvCarrier)
    void onTopCarrierClick() {
        if (null == mCarrierPopupMenu) {
            mCarrierPopupMenu = new PopupMenu(getActivity(), tvCarrier);
            mCarrierPopupMenu.getMenu().setGroupCheckable(0, true, true);

            int menuIndex = 0;

            mCarrierPopupMenu.getMenu().add(0, menuIndex, 0, CARRIER_CHINA_YD);
            mCarrierPopupMenu.getMenu().add(0, menuIndex, 0, CARRIER_CHINA_LT);
            mCarrierPopupMenu.getMenu().add(0, menuIndex, 0, CARRIER_CHINA_DX);


            mCarrierPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {


                    mBaseMobileModel.setMobileCarrier(item.getTitle().toString());
                    if (mModelMobileChangeListener != null) {

                        mModelMobileChangeListener.onItemClickListener(mBaseMobileModel);
                    }

                    tvCarrier.setText(item.getTitle());

                    return false;
                }
            });


        }
        mCarrierPopupMenu.show();
    }


    @OnClick(R.id.tvMobileType)
    void onMobileTypeClick() {


        if (null == mMobileTypePopupMenu) {
            mMobileTypePopupMenu = new PopupMenu(getActivity(), tvMobileType);
            mMobileTypePopupMenu.getMenu().setGroupCheckable(0, true, true);

            int menuIndex = 0;

            mMobileTypePopupMenu.getMenu().add(0, menuIndex, 0, MOBILE_IOS).setTitleCondensed
                    (String.valueOf
                            (AppConstant.ACTION_10));
            menuIndex++;
            mMobileTypePopupMenu.getMenu().add(0, menuIndex, 0, MOBILE_ANDROID).setTitleCondensed
                    (String.valueOf
                            (AppConstant.ACTION_20));


            mMobileTypePopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener
                    () {

                @Override
                public boolean onMenuItemClick(MenuItem item) {


                    mBaseMobileModel.setMobileType(Integer.parseInt(item.getTitleCondensed()
                            .toString()));
                    if (mModelMobileChangeListener != null) {

                        mModelMobileChangeListener.onItemClickListener(mBaseMobileModel);
                    }


                    switch (mBaseMobileModel.getMobileType()) {
                        case AppConstant.ACTION_10:
                            iosContainer.setVisibility(View.VISIBLE);
                            carrierRelativeLayout.setVisibility(View.VISIBLE);
                            break;
                        case AppConstant.ACTION_20:
                            iosContainer.setVisibility(View.GONE);
                            carrierRelativeLayout.setVisibility(View.GONE);
                            break;
                    }


                    tvMobileType.setText(item.getTitle());

                    return false;
                }
            });


        }

        mMobileTypePopupMenu.show();
    }


    @OnClick(R.id.tvSignal)
    void onSignalClick() {


        if (null == mSignalPopupMenu) {
            mSignalPopupMenu = new PopupMenu(getActivity(), tvSignal);
            mSignalPopupMenu.getMenu().setGroupCheckable(0, true, true);

            int menuIndex = 0;

            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_SIGNAL_1)
                    .setTitleCondensed(String
                            .valueOf(AppConstant.ACTION_10));
            menuIndex++;
            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_SIGNAL_2)
                    .setTitleCondensed(String
                            .valueOf(AppConstant.ACTION_20));
            menuIndex++;
            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_SIGNAL_3)
                    .setTitleCondensed(String
                            .valueOf(AppConstant.ACTION_30));
            menuIndex++;
            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_SIGNAL_4)
                    .setTitleCondensed(String
                            .valueOf(AppConstant.ACTION_40));
            menuIndex++;
            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_SIGNAL_5)
                    .setTitleCondensed(String
                            .valueOf(AppConstant.ACTION_50));


            mSignalPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {


                    mBaseMobileModel.setNetworkSignal(Integer.parseInt(item.getTitleCondensed()
                            .toString()));
                    if (mModelMobileChangeListener != null) {

                        mModelMobileChangeListener.onItemClickListener(mBaseMobileModel);
                    }

                    tvSignal.setText(item.getTitle());

                    return false;
                }
            });


        }

        mSignalPopupMenu.show();
    }


    @OnClick(R.id.tvNetwork)
    void onNetworkClick() {
        if (null == mNetworkPopupMenu) {
            mNetworkPopupMenu = new PopupMenu(getActivity(), tvNetwork);
            mNetworkPopupMenu.getMenu().setGroupCheckable(0, true, true);

            int menuIndex = 0;

            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_WIFI)
                    .setTitleCondensed(String
                            .valueOf(AppConstant.ACTION_10));
            menuIndex++;
            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_G)
                    .setTitleCondensed(String.valueOf
                            (AppConstant.ACTION_20));
            menuIndex++;
            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_E)
                    .setTitleCondensed(String.valueOf
                            (AppConstant.ACTION_30));
            menuIndex++;
            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_3G)
                    .setTitleCondensed(String.valueOf
                            (AppConstant.ACTION_40));
            menuIndex++;
            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_4G)
                    .setTitleCondensed(String.valueOf
                            (AppConstant.ACTION_50));


            mNetworkPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {


                    mBaseMobileModel.setNetworkType(Integer.parseInt(item.getTitleCondensed()
                            .toString()));
                    if (mModelMobileChangeListener != null) {

                        mModelMobileChangeListener.onItemClickListener(mBaseMobileModel);
                    }

                    tvNetwork.setText(item.getTitle());

                    return false;
                }
            });


        }

        mNetworkPopupMenu.show();
    }


    MobileChangeListener<BaseMobileModel> mModelMobileChangeListener;

    public void setMobileChangeListener(MobileChangeListener<BaseMobileModel>
                                                modelMobileChangeListener) {
        this.mModelMobileChangeListener = modelMobileChangeListener;
    }


}
