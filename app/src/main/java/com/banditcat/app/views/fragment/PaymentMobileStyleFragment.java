package com.banditcat.app.views.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;

import com.banditcat.common.fontawesom.typeface.BaseFontAwesome;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import com.banditcat.app.R;
import com.banditcat.app.views.base.BaseFragment;
import com.banditcat.app.constant.AppConstant;
import com.banditcat.app.views.listeners.MobileChangeListener;
import com.banditcat.app.model.AliPaymentModel;
import com.banditcat.app.utils.TimeUtils;
import com.banditcat.app.utils.ViewUtils;

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


        ViewUtils.setCompoundRightDrawables(getContext(), tvCreateTime, BaseFontAwesome.Icon.icon_right, getResources().getColor(R.color
                .colorRightTitle), 4f);

        ViewUtils.setCompoundRightDrawables(getContext(), tvMobileType, BaseFontAwesome.Icon.icon_right, getResources().getColor(R.color
                .colorRightTitle), 4f);

        ViewUtils.setCompoundRightDrawables(getContext(), tvSignal, BaseFontAwesome.Icon.icon_right, getResources().getColor(R.color
                .colorRightTitle), 4f);

        ViewUtils.setCompoundRightDrawables(getContext(), tvNetwork, BaseFontAwesome.Icon.icon_right, getResources().getColor(R.color
                .colorRightTitle), 4f);


        loadViewData((AliPaymentModel) getArguments().getSerializable(ARG_PARAM1));

    }

    public void loadViewData(AliPaymentModel model) {
        if (model == null) {
            return;
        }

        mAliPaymentModel = model;


        tvCreateTime.setText(TimeUtils.millis2String(mAliPaymentModel.getTopTime(), TimeUtils.DEFAULT_PATTERN_4));



        switch (mAliPaymentModel.getTopToolStyle())
        {
            case AppConstant.ACTION_10:
                tvTopStyle.setText(AppConstant.TOOL_STYLE_CUSTOM);
                break;
        }


        switch (mAliPaymentModel.getMobileType()) {
            case AppConstant.ACTION_10:
                tvMobileType.setText(MOBILE_IOS);
                break;
            case AppConstant.ACTION_20:
                tvMobileType.setText(MOBILE_ANDROID);
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
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), hourOfDay, minute, second);

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
        mTimePickerDialog = TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
        mTimePickerDialog.show(getActivity().getFragmentManager(), "mTimePickerDialog30");


    }

    @OnCheckedChanged(R.id.ckType)
    void onChecked(boolean checked) {

        mAliPaymentModel.setDateTimeStyle(checked);
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

            mToolStylePopupMenu.getMenu().add(0, menuIndex, 0, TOOL_STYLE_CUSTOM).setTitleCondensed(String.valueOf(AppConstant.ACTION_10));


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

            mMobileTypePopupMenu.getMenu().add(0, menuIndex, 0, MOBILE_IOS).setTitleCondensed(String.valueOf(AppConstant.ACTION_10));
            menuIndex++;
            mMobileTypePopupMenu.getMenu().add(0, menuIndex, 0, MOBILE_ANDROID).setTitleCondensed(String.valueOf(AppConstant.ACTION_20));


            mMobileTypePopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {


                    mAliPaymentModel.setMobileType(Integer.parseInt(item.getTitleCondensed().toString()));
                    if (mModelMobileChangeListener != null) {

                        mModelMobileChangeListener.onItemClickListener(mAliPaymentModel);
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

            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_SIGNAL_1).setTitleCondensed(String.valueOf(AppConstant.ACTION_10));
            menuIndex++;
            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_SIGNAL_2).setTitleCondensed(String.valueOf(AppConstant.ACTION_20));
            menuIndex++;
            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_SIGNAL_3).setTitleCondensed(String.valueOf(AppConstant.ACTION_30));
            menuIndex++;
            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_SIGNAL_4).setTitleCondensed(String.valueOf(AppConstant.ACTION_40));
            menuIndex++;
            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_SIGNAL_5).setTitleCondensed(String.valueOf(AppConstant.ACTION_50));


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

            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_WIFI).setTitleCondensed(String.valueOf(AppConstant.ACTION_10));
            menuIndex++;
            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_G).setTitleCondensed(String.valueOf(AppConstant.ACTION_20));
            menuIndex++;
            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_E).setTitleCondensed(String.valueOf(AppConstant.ACTION_30));
            menuIndex++;
            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_3G).setTitleCondensed(String.valueOf(AppConstant.ACTION_40));
            menuIndex++;
            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, AppConstant.NETWORK_4G).setTitleCondensed(String.valueOf(AppConstant.ACTION_50));


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
