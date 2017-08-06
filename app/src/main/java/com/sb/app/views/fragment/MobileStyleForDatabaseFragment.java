package com.sb.app.views.fragment;

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
import com.sb.app.utils.TimeUtils;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.base.BaseFragment;
import com.sb.app.views.listeners.MobileChangeListener;
import com.sb.common.fontawesom.typeface.BaseFontAwesome;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.MobileStyleRealm;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import io.realm.Realm;

import static com.sb.app.constant.AppConstant.CARRIER_CHINA_DX;
import static com.sb.app.constant.AppConstant.CARRIER_CHINA_LT;
import static com.sb.app.constant.AppConstant.CARRIER_CHINA_YD;
import static com.sb.app.constant.AppConstant.MOBILE_ANDROID;
import static com.sb.app.constant.AppConstant.MOBILE_IOS;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link MobileStyleForDatabaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MobileStyleForDatabaseFragment extends BaseFragment implements TimePickerDialog
        .OnTimeSetListener {

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
    Realm mRealm;

    MobileStyleRealm mMobileStyleRealm;

    public MobileStyleForDatabaseFragment() {
        mRealm = Realm.getDefaultInstance();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PaymentMenuFragment.
     */

    public static MobileStyleForDatabaseFragment newInstance() {
        MobileStyleForDatabaseFragment fragment = new MobileStyleForDatabaseFragment();

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
                    mergerBatteryNumber(progress);
                } else if (progress > 0 && progress <= 4) {
                    mergerBatteryNumber(4);
                } else if (progress > 4 && progress <= 8) {
                    mergerBatteryNumber(8);
                } else if (progress > 8 && progress <= 12) {
                    mergerBatteryNumber(12);
                } else if (progress > 12 && progress <= 16) {
                    mergerBatteryNumber(16);
                } else if (progress > 16 && progress <= 20) {
                    mergerBatteryNumber(20);
                } else if (progress > 20 && progress <= 24) {
                    mergerBatteryNumber(24);
                } else if (progress > 24 && progress <= 28) {
                    mergerBatteryNumber(28);
                } else if (progress > 28 && progress <= 32) {
                    mergerBatteryNumber(32);
                } else if (progress > 32 && progress <= 36) {
                    mergerBatteryNumber(36);
                } else if (progress > 36 && progress <= 40) {
                    mergerBatteryNumber(40);
                } else if (progress > 40 && progress <= 44) {
                    mergerBatteryNumber(44);
                } else if (progress > 44 && progress <= 48) {
                    mergerBatteryNumber(48);
                } else if (progress > 48 && progress <= 52) {
                    mergerBatteryNumber(52);
                } else if (progress > 52 && progress <= 56) {
                    mergerBatteryNumber(56);
                } else if (progress > 56 && progress <= 60) {
                    mergerBatteryNumber(60);
                } else if (progress > 60 && progress <= 64) {
                    mergerBatteryNumber(44);
                } else if (progress > 64 && progress <= 68) {
                    mergerBatteryNumber(48);
                } else if (progress > 68 && progress <= 72) {
                    mergerBatteryNumber(72);
                } else if (progress > 72 && progress <= 76) {
                    mergerBatteryNumber(76);
                } else if (progress > 76 && progress <= 80) {
                    mergerBatteryNumber(80);
                } else if (progress > 80 && progress <= 84) {
                    mergerBatteryNumber(84);
                } else if (progress > 84 && progress <= 88) {
                    mergerBatteryNumber(88);
                } else if (progress > 88 && progress <= 92) {
                    mergerBatteryNumber(92);
                } else if (progress > 92 && progress <= 99) {
                    mergerBatteryNumber(96);
                } else if (progress == 100) {
                    mergerBatteryNumber(progress);
                }
                tvLeftBattery.setText(String.format(tvLeftBattery.getTag().toString(),
                        mMobileStyleRealm
                                .getBatteryNumBar()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        loadViewData();

    }


    public void loadViewData() {

        mMobileStyleRealm = mRealm.where(MobileStyleRealm.class).findFirst();

        tvCarrier.setText(mMobileStyleRealm.getMobileCarrier());

        tvCreateTime.setText(TimeUtils.millis2String(mMobileStyleRealm.getTopTime(), TimeUtils
                .DEFAULT_PATTERN_4));


        switch (mMobileStyleRealm.getTopToolStyle()) {
            case TextConstant.TOOL_STYLE_SYSTEM:
                tvTopStyle.setText(AppConstant.TOOL_STYLE_SYSTEM);
                break;
            case TextConstant.TOOL_STYLE_CUSTOMER:
                tvTopStyle.setText(AppConstant.TOOL_STYLE_CUSTOM);
                break;
        }


        switch (mMobileStyleRealm.getMobileVersion()) {
            case TextConstant.MOBILE_VERSION_IOS:
                tvMobileType.setText(MOBILE_IOS);
                carrierRelativeLayout.setVisibility(View.VISIBLE);
                iosContainer.setVisibility(View.VISIBLE);


                break;
            case TextConstant.MOBILE_VERSION_ANDROID_4:
                tvMobileType.setText(MOBILE_ANDROID);
                iosContainer.setVisibility(View.GONE);
                carrierRelativeLayout.setVisibility(View.GONE);
                break;
        }

        switch (mMobileStyleRealm.getNetworkSignal()) {
            case TextConstant.NETWORK_SIGNAL_1:
                tvSignal.setText(AppConstant.NETWORK_SIGNAL_1);
                break;
            case TextConstant.NETWORK_SIGNAL_2:
                tvSignal.setText(AppConstant.NETWORK_SIGNAL_2);
                break;
            case TextConstant.NETWORK_SIGNAL_3:
                tvSignal.setText(AppConstant.NETWORK_SIGNAL_3);
                break;
            case TextConstant.NETWORK_SIGNAL_4:
                tvSignal.setText(AppConstant.NETWORK_SIGNAL_4);
                break;
            case TextConstant.NETWORK_SIGNAL_5:
                tvSignal.setText(AppConstant.NETWORK_SIGNAL_5);
                break;
        }

        switch (mMobileStyleRealm.getNetworkType()) {
            case TextConstant.NETWORK_TYPE_WIFI:
                tvNetwork.setText(AppConstant.NETWORK_WIFI);
                break;
            case TextConstant.NETWORK_TYPE_G:
                tvNetwork.setText(AppConstant.NETWORK_G);
                break;
            case TextConstant.NETWORK_TYPE_E:
                tvNetwork.setText(AppConstant.NETWORK_E);
                break;
            case TextConstant.NETWORK_TYPE_3G:
                tvNetwork.setText(AppConstant.NETWORK_3G);
                break;
            case TextConstant.NETWORK_TYPE_4G:
                tvNetwork.setText(AppConstant.NETWORK_4G);
                break;
        }

        tvLeftBattery.setText(String.format(tvLeftBattery.getTag().toString(), mMobileStyleRealm
                .getBatteryNumBar()));

        seekBarBatteryNum.setProgress(mMobileStyleRealm
                .getBatteryNumBar());

        ckDir.setChecked(mMobileStyleRealm.getDir());
        ckBattery.setChecked(mMobileStyleRealm.getBatteryAdd());
        ckBatteryNum.setChecked(mMobileStyleRealm.getBatteryNum());

        ckBlueTeeth.setChecked(mMobileStyleRealm.getBlueTeeth());
        ckLocation.setChecked(mMobileStyleRealm.getLocation());

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
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mMobileStyleRealm.getTopTime());
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get
                        (Calendar.DAY_OF_MONTH),
                hourOfDay, minute, second);


        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mMobileStyleRealm.setTopTime(calendar.getTimeInMillis());
                if (mModelMobileChangeListener != null) {

                    mModelMobileChangeListener.onItemClickListener(mMobileStyleRealm);
                }
            }
        });


        tvCreateTime.setText(TimeUtils.millis2String(calendar.getTimeInMillis(), TimeUtils
                .DEFAULT_PATTERN_4));
    }


    @OnClick(R.id.tvCreateTime2)
    void onBankHandleOverTimeClick() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mMobileStyleRealm.getTopTime());
        mTimePickerDialog = TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get
                        (Calendar.MINUTE), true);
        mTimePickerDialog.show(getActivity().getFragmentManager(), "mTimePickerDialog30");

    }

    @OnCheckedChanged(R.id.ckType)
    void onChecked(final boolean checked) {

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mMobileStyleRealm.setDate24TimeStyle(checked);
                if (mModelMobileChangeListener != null) {

                    mModelMobileChangeListener.onItemClickListener(mMobileStyleRealm);
                }
            }
        });
    }

    @OnCheckedChanged(R.id.ckDir)
    void onDirChecked(final boolean checked) {


        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mMobileStyleRealm.setDir(checked);
                if (mModelMobileChangeListener != null) {

                    mModelMobileChangeListener.onItemClickListener(mMobileStyleRealm);
                }
            }
        });
    }

    @OnCheckedChanged(R.id.ckBattery)
    void onBatteryChecked(final boolean checked) {


        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mMobileStyleRealm.setBatteryAdd(checked);
                if (mModelMobileChangeListener != null) {

                    mModelMobileChangeListener.onItemClickListener(mMobileStyleRealm);
                }
            }
        });
    }


    @OnCheckedChanged(R.id.ckBatteryNum)
    void onBatteryNumChecked(final boolean checked) {


        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mMobileStyleRealm.setBatteryNum(checked);
                if (mModelMobileChangeListener != null) {

                    mModelMobileChangeListener.onItemClickListener(mMobileStyleRealm);
                }
            }
        });
    }

    @OnCheckedChanged(R.id.ckBlueTeeth)
    void onBlueTeethChecked(final boolean checked) {


        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mMobileStyleRealm.setBlueTeeth(checked);
                if (mModelMobileChangeListener != null) {

                    mModelMobileChangeListener.onItemClickListener(mMobileStyleRealm);
                }
            }
        });
    }

    @OnCheckedChanged(R.id.ckLocation)
    void onLocationChecked(final boolean checked) {


        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mMobileStyleRealm.setLocation(checked);
                if (mModelMobileChangeListener != null) {

                    mModelMobileChangeListener.onItemClickListener(mMobileStyleRealm);
                }
            }
        });
    }


    @OnClick(R.id.tvTopStyle)
    void onTopStyleClick() {
        if (null == mToolStylePopupMenu) {
            mToolStylePopupMenu = new PopupMenu(getActivity(), tvTopStyle);
            mToolStylePopupMenu.getMenu().setGroupCheckable(0, true, true);

            int menuIndex = 0;
            mToolStylePopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.TOOL_STYLE_SYSTEM)
                    .setTitleCondensed(String.valueOf
                            (TextConstant.TOOL_STYLE_SYSTEM));
            mToolStylePopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.TOOL_STYLE_CUSTOM)
                    .setTitleCondensed(String.valueOf
                            (TextConstant.TOOL_STYLE_CUSTOMER));


            mToolStylePopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {


                    mergerTopToolStyle(Integer.parseInt(item.getTitleCondensed()
                            .toString()));
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


                    mergerMobileCarrier(item.getTitle().toString());
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
                            (TextConstant.MOBILE_VERSION_IOS));
            menuIndex++;
            mMobileTypePopupMenu.getMenu().add(0, menuIndex, 0, MOBILE_ANDROID).setTitleCondensed
                    (String.valueOf
                            (TextConstant.MOBILE_VERSION_ANDROID_4));


            mMobileTypePopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener
                    () {

                @Override
                public boolean onMenuItemClick(MenuItem item) {


                    mergerMobileVersion(Integer.parseInt(item.getTitleCondensed()
                            .toString()));


                    switch (mMobileStyleRealm.getMobileVersion()) {
                        case TextConstant.MOBILE_VERSION_IOS:
                            iosContainer.setVisibility(View.VISIBLE);
                            carrierRelativeLayout.setVisibility(View.VISIBLE);
                            break;
                        case TextConstant.MOBILE_VERSION_ANDROID_4:
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
                            .valueOf(TextConstant.NETWORK_SIGNAL_1));
            menuIndex++;
            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_SIGNAL_2)
                    .setTitleCondensed(String
                            .valueOf(TextConstant.NETWORK_SIGNAL_2));
            menuIndex++;
            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_SIGNAL_3)
                    .setTitleCondensed(String
                            .valueOf(TextConstant.NETWORK_SIGNAL_3));
            menuIndex++;
            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_SIGNAL_4)
                    .setTitleCondensed(String
                            .valueOf(TextConstant.NETWORK_SIGNAL_4));
            menuIndex++;
            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_SIGNAL_5)
                    .setTitleCondensed(String
                            .valueOf(TextConstant.NETWORK_SIGNAL_5));


            mSignalPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {


                    mergerNetworkSignal(Integer.parseInt(item.getTitleCondensed()
                            .toString()));


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
                            .valueOf(TextConstant.NETWORK_TYPE_WIFI));
            menuIndex++;
            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_G)
                    .setTitleCondensed(String.valueOf
                            (TextConstant.NETWORK_TYPE_G));
            menuIndex++;
            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_E)
                    .setTitleCondensed(String.valueOf
                            (TextConstant.NETWORK_TYPE_E));
            menuIndex++;
            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_3G)
                    .setTitleCondensed(String.valueOf
                            (TextConstant.NETWORK_TYPE_3G));
            menuIndex++;
            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_4G)
                    .setTitleCondensed(String.valueOf
                            (TextConstant.NETWORK_TYPE_4G));


            mNetworkPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {


                    mergerNetworkType(Integer.parseInt(item.getTitleCondensed()
                            .toString()));

                    tvNetwork.setText(item.getTitle());

                    return false;
                }
            });


        }

        mNetworkPopupMenu.show();
    }


    void mergerTopToolStyle(final int value) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mMobileStyleRealm.setTopToolStyle(value);
                if (mModelMobileChangeListener != null) {

                    mModelMobileChangeListener.onItemClickListener(mMobileStyleRealm);
                }
            }
        });
    }

    void mergerMobileCarrier(final String value) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mMobileStyleRealm.setMobileCarrier(value);
                if (mModelMobileChangeListener != null) {

                    mModelMobileChangeListener.onItemClickListener(mMobileStyleRealm);
                }
            }
        });
    }

    void mergerMobileVersion(final int value) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mMobileStyleRealm.setMobileVersion(value);
                if (mModelMobileChangeListener != null) {

                    mModelMobileChangeListener.onItemClickListener(mMobileStyleRealm);
                }
            }
        });
    }


    void mergerNetworkSignal(final int value) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mMobileStyleRealm.setNetworkSignal(value);
                if (mModelMobileChangeListener != null) {

                    mModelMobileChangeListener.onItemClickListener(mMobileStyleRealm);
                }
            }
        });
    }

    void mergerNetworkType(final int value) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mMobileStyleRealm.setNetworkType(value);
                if (mModelMobileChangeListener != null) {

                    mModelMobileChangeListener.onItemClickListener(mMobileStyleRealm);
                }
            }
        });
    }


    void mergerBatteryNumber(final int battery) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mMobileStyleRealm.setBatteryNumBar(battery);

                if (mModelMobileChangeListener != null) {

                    mModelMobileChangeListener.onItemClickListener(mMobileStyleRealm);
                }
            }
        });
    }


    MobileChangeListener<MobileStyleRealm> mModelMobileChangeListener;

    public void setMobileChangeListener(MobileChangeListener<MobileStyleRealm>
                                                modelMobileChangeListener) {
        this.mModelMobileChangeListener = modelMobileChangeListener;
    }

}
