package com.banditcat.app.views.fragment;

import android.os.Bundle;
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

import com.banditcat.app.R;
import com.banditcat.app.constant.AppConstant;
import com.banditcat.app.model.AliPaymentModel;
import com.banditcat.app.utils.TimeUtils;
import com.banditcat.app.utils.ViewUtils;
import com.banditcat.app.views.base.BaseFragment;
import com.banditcat.app.views.listeners.MobileChangeListener;
import com.banditcat.common.fontawesom.typeface.BaseFontAwesome;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

import static com.banditcat.app.constant.AppConstant.MOBILE_ANDROID;
import static com.banditcat.app.constant.AppConstant.MOBILE_IOS;
import static com.banditcat.app.constant.AppConstant.TOOL_STYLE_CUSTOM;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link PaymentMobileStyleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaymentMobileStyleFragment extends BaseFragment implements TimePickerDialog.OnTimeSetListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    AliPaymentModel mAliPaymentModel;


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


    //菜单

    PopupMenu mNetworkPopupMenu;//网络信号弹出框

    PopupMenu mSignalPopupMenu;//网络信号强度弹出框

    PopupMenu mMobileTypePopupMenu;//手机型号弹出框

    PopupMenu mToolStylePopupMenu;//顶部工具栏样式


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

    public static PaymentMobileStyleFragment newInstance(AliPaymentModel aliPaymentModel) {
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


        ViewUtils.setCompoundRightDrawables(getContext(), tvCreateTime, BaseFontAwesome.Icon.icon_right, getResources
                ().getColor(R.color
                .colorRightTitle), 4f);

        ViewUtils.setCompoundRightDrawables(getContext(), tvMobileType, BaseFontAwesome.Icon.icon_right, getResources
                ().getColor(R.color
                .colorRightTitle), 4f);

        ViewUtils.setCompoundRightDrawables(getContext(), tvSignal, BaseFontAwesome.Icon.icon_right, getResources()
                .getColor(R.color
                        .colorRightTitle), 4f);

        ViewUtils.setCompoundRightDrawables(getContext(), tvNetwork, BaseFontAwesome.Icon.icon_right, getResources()
                .getColor(R.color
                        .colorRightTitle), 4f);


        seekBarBatteryNum.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                if (progress == 0) {
                    mAliPaymentModel.setBatteryNumBar(progress);
                } else if (progress > 0 && progress <= 4) {
                    mAliPaymentModel.setBatteryNumBar(4);
                } else if (progress > 4 && progress <= 8) {
                    mAliPaymentModel.setBatteryNumBar(8);
                } else if (progress > 8 && progress <= 12) {
                    mAliPaymentModel.setBatteryNumBar(12);
                } else if (progress > 12 && progress <= 16) {
                    mAliPaymentModel.setBatteryNumBar(16);
                } else if (progress > 16 && progress <= 20) {
                    mAliPaymentModel.setBatteryNumBar(20);
                } else if (progress > 20 && progress <= 24) {
                    mAliPaymentModel.setBatteryNumBar(24);
                } else if (progress > 24 && progress <= 28) {
                    mAliPaymentModel.setBatteryNumBar(28);
                } else if (progress > 28 && progress <= 32) {
                    mAliPaymentModel.setBatteryNumBar(32);
                } else if (progress > 32 && progress <= 36) {
                    mAliPaymentModel.setBatteryNumBar(36);
                } else if (progress > 36 && progress <= 40) {
                    mAliPaymentModel.setBatteryNumBar(40);
                } else if (progress > 40 && progress <= 44) {
                    mAliPaymentModel.setBatteryNumBar(44);
                } else if (progress > 44 && progress <= 48) {
                    mAliPaymentModel.setBatteryNumBar(48);
                } else if (progress > 48 && progress <= 52) {
                    mAliPaymentModel.setBatteryNumBar(52);
                } else if (progress > 52 && progress <= 56) {
                    mAliPaymentModel.setBatteryNumBar(56);
                } else if (progress > 56 && progress <= 60) {
                    mAliPaymentModel.setBatteryNumBar(60);
                } else if (progress > 60 && progress <= 64) {
                    mAliPaymentModel.setBatteryNumBar(44);
                } else if (progress > 64 && progress <= 68) {
                    mAliPaymentModel.setBatteryNumBar(48);
                } else if (progress > 68 && progress <= 72) {
                    mAliPaymentModel.setBatteryNumBar(72);
                } else if (progress > 72 && progress <= 76) {
                    mAliPaymentModel.setBatteryNumBar(76);
                } else if (progress > 76 && progress <= 80) {
                    mAliPaymentModel.setBatteryNumBar(80);
                } else if (progress > 80 && progress <= 84) {
                    mAliPaymentModel.setBatteryNumBar(84);
                } else if (progress > 84 && progress <= 88) {
                    mAliPaymentModel.setBatteryNumBar(88);
                } else if (progress > 88 && progress <= 92) {
                    mAliPaymentModel.setBatteryNumBar(92);
                } else if (progress > 92 && progress <= 99) {
                    mAliPaymentModel.setBatteryNumBar(96);
                } else if (progress == 100) {
                    mAliPaymentModel.setBatteryNumBar(progress);
                }


                tvLeftBattery.setText(String.format(tvLeftBattery.getTag().toString(), mAliPaymentModel
                        .getBatteryNumBar()));

                if (mModelMobileChangeListener != null) {

                    mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        loadViewData((AliPaymentModel) getArguments().getSerializable(ARG_PARAM1));

    }

    public void loadViewData(AliPaymentModel model) {
        if (model == null) {
            return;
        }

        mAliPaymentModel = model;


        tvCreateTime.setText(TimeUtils.millis2String(mAliPaymentModel.getTopTime(), TimeUtils.DEFAULT_PATTERN_4));


        switch (mAliPaymentModel.getTopToolStyle()) {
            case AppConstant.ACTION_10:
                tvTopStyle.setText(AppConstant.TOOL_STYLE_CUSTOM);
                break;
        }


        switch (mAliPaymentModel.getMobileType()) {
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

        switch (mAliPaymentModel.getNetworkSignal()) {
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

        switch (mAliPaymentModel.getNetworkType()) {
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

        tvLeftBattery.setText(String.format(tvLeftBattery.getTag().toString(), mAliPaymentModel
                .getBatteryNumBar()));

        seekBarBatteryNum.setProgress(mAliPaymentModel
                .getBatteryNumBar());

        ckDir.setChecked(mAliPaymentModel.getDir());
        ckBattery.setChecked(mAliPaymentModel.getBatteryAdd());
        ckBatteryNum.setChecked(mAliPaymentModel.getBatteryNum());

        ckBlueTeeth.setChecked(mAliPaymentModel.getBlueTeeth());
        ckLocation.setChecked(mAliPaymentModel.getLocation());

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
        calendar.setTimeInMillis(mAliPaymentModel.getTopTime());
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                hourOfDay, minute, second);

        mAliPaymentModel.setTopTime(calendar.getTimeInMillis());
        if (mModelMobileChangeListener != null) {

            mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
        }


        tvCreateTime.setText(TimeUtils.millis2String(calendar.getTimeInMillis(), TimeUtils.DEFAULT_PATTERN_4));
    }


    @OnClick(R.id.tvCreateTime2)
    void onBankHandleOverTimeClick() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mAliPaymentModel.getTopTime());
        mTimePickerDialog = TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get
                (Calendar.MINUTE), true);
        mTimePickerDialog.show(getActivity().getFragmentManager(), "mTimePickerDialog30");


    }

    @OnCheckedChanged(R.id.ckType)
    void onChecked(boolean checked) {

        mAliPaymentModel.setDateTimeStyle(checked);
        if (mModelMobileChangeListener != null) {

            mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
        }
    }

    @OnCheckedChanged(R.id.ckDir)
    void onDirChecked(boolean checked) {

        mAliPaymentModel.setDir(checked);
        if (mModelMobileChangeListener != null) {

            mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
        }
    }

    @OnCheckedChanged(R.id.ckBattery)
    void onBatteryChecked(boolean checked) {

        mAliPaymentModel.setBatteryAdd(checked);
        if (mModelMobileChangeListener != null) {

            mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
        }
    }


    @OnCheckedChanged(R.id.ckBatteryNum)
    void onBatteryNumChecked(boolean checked) {

        mAliPaymentModel.setBatteryNum(checked);
        if (mModelMobileChangeListener != null) {

            mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
        }
    }

    @OnCheckedChanged(R.id.ckBlueTeeth)
    void onBlueTeethChecked(boolean checked) {

        mAliPaymentModel.setBlueTeeth(checked);
        if (mModelMobileChangeListener != null) {

            mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
        }
    }

    @OnCheckedChanged(R.id.ckLocation)
    void onLocationChecked(boolean checked) {

        mAliPaymentModel.setLocation(checked);
        if (mModelMobileChangeListener != null) {

            mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
        }
    }


    @OnClick(R.id.tvTopStyle)
    void onTopStyleClick() {
        if (null == mToolStylePopupMenu) {
            mToolStylePopupMenu = new PopupMenu(getActivity(), tvTopStyle);
            mToolStylePopupMenu.getMenu().setGroupCheckable(0, true, true);

            int menuIndex = 0;

            mToolStylePopupMenu.getMenu().add(0, menuIndex, 0, TOOL_STYLE_CUSTOM).setTitleCondensed(String.valueOf
                    (AppConstant.ACTION_10));


            mToolStylePopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {


                    mAliPaymentModel.setTopToolStyle(Integer.parseInt(item.getTitleCondensed().toString()));
                    if (mModelMobileChangeListener != null) {

                        mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
                    }

                    tvTopStyle.setText(item.getTitle());

                    return false;
                }
            });


        }
        mToolStylePopupMenu.show();
    }


    @OnClick(R.id.tvMobileType)
    void onMobileTypeClick() {


        if (null == mMobileTypePopupMenu) {
            mMobileTypePopupMenu = new PopupMenu(getActivity(), tvMobileType);
            mMobileTypePopupMenu.getMenu().setGroupCheckable(0, true, true);

            int menuIndex = 0;

            mMobileTypePopupMenu.getMenu().add(0, menuIndex, 0, MOBILE_IOS).setTitleCondensed(String.valueOf
                    (AppConstant.ACTION_10));
            menuIndex++;
            mMobileTypePopupMenu.getMenu().add(0, menuIndex, 0, MOBILE_ANDROID).setTitleCondensed(String.valueOf
                    (AppConstant.ACTION_20));


            mMobileTypePopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {


                    mAliPaymentModel.setMobileType(Integer.parseInt(item.getTitleCondensed().toString()));
                    if (mModelMobileChangeListener != null) {

                        mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
                    }

                    switch (mAliPaymentModel.getMobileType()) {
                        case AppConstant.ACTION_10:
                            iosContainer.setVisibility(View.VISIBLE);
                            break;
                        case AppConstant.ACTION_20:
                            iosContainer.setVisibility(View.GONE);
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

            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_SIGNAL_1).setTitleCondensed(String
                    .valueOf(AppConstant.ACTION_10));
            menuIndex++;
            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_SIGNAL_2).setTitleCondensed(String
                    .valueOf(AppConstant.ACTION_20));
            menuIndex++;
            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_SIGNAL_3).setTitleCondensed(String
                    .valueOf(AppConstant.ACTION_30));
            menuIndex++;
            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_SIGNAL_4).setTitleCondensed(String
                    .valueOf(AppConstant.ACTION_40));
            menuIndex++;
            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_SIGNAL_5).setTitleCondensed(String
                    .valueOf(AppConstant.ACTION_50));


            mSignalPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {


                    mAliPaymentModel.setNetworkSignal(Integer.parseInt(item.getTitleCondensed().toString()));
                    if (mModelMobileChangeListener != null) {

                        mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
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

            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_WIFI).setTitleCondensed(String
                    .valueOf(AppConstant.ACTION_10));
            menuIndex++;
            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_G).setTitleCondensed(String.valueOf
                    (AppConstant.ACTION_20));
            menuIndex++;
            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_E).setTitleCondensed(String.valueOf
                    (AppConstant.ACTION_30));
            menuIndex++;
            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_3G).setTitleCondensed(String.valueOf
                    (AppConstant.ACTION_40));
            menuIndex++;
            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_4G).setTitleCondensed(String.valueOf
                    (AppConstant.ACTION_50));


            mNetworkPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {


                    mAliPaymentModel.setNetworkType(Integer.parseInt(item.getTitleCondensed().toString()));
                    if (mModelMobileChangeListener != null) {

                        mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
                    }

                    tvNetwork.setText(item.getTitle());

                    return false;
                }
            });


        }

        mNetworkPopupMenu.show();
    }


    MobileChangeListener<AliPaymentModel> mModelMobileChangeListener;

    public void setMobileChangeListener(MobileChangeListener<AliPaymentModel> modelMobileChangeListener) {
        this.mModelMobileChangeListener = modelMobileChangeListener;
    }


}
