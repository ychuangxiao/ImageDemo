package demo.banditcat.com.imagedemo.activitys.ali;

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
import demo.banditcat.com.imagedemo.R;
import demo.banditcat.com.imagedemo.base.BaseFragment;
import demo.banditcat.com.imagedemo.listeners.MobileChangeListener;
import demo.banditcat.com.imagedemo.model.AliPaymentModel;
import demo.banditcat.com.imagedemo.utils.TimeUtils;
import demo.banditcat.com.imagedemo.utils.ViewUtils;

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


    //菜单

    PopupMenu mNetworkPopupMenu;//网络信号弹出框

    PopupMenu mSignalPopupMenu;//网络信号强度弹出框

    PopupMenu mMobileTypePopupMenu;//手机型号弹出框


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



        switch (getAliPaymentModel().getMobileType())
        {
            case 10:
                tvMobileType.setText("苹果");
                break;
            case 20:
                tvMobileType.setText("谷歌");
                break;
        }

        switch (getAliPaymentModel().getNetworkSignal())
        {
            case 10:
                tvSignal.setText("1格");
                break;
            case 20:
                tvSignal.setText("2格");
                break;
            case 30:
                tvSignal.setText("3格");
                break;
            case 40:
                tvSignal.setText("4格");
                break;
            case 50:
                tvSignal.setText("5格");
                break;
        }

        switch (getAliPaymentModel().getNetworkType())
        {
            case 10:
                tvNetwork.setText("Wifi");
                break;
            case 20:
                tvNetwork.setText("G");
                break;
            case 30:
                tvNetwork.setText("E");
                break;
            case 40:
                tvNetwork.setText("3G");
                break;
            case 50:
                tvNetwork.setText("4G");
                break;
        }

    }


    public AliPaymentModel getAliPaymentModel() {
        return mAliPaymentModel;
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




    @OnClick(R.id.tvMobileType)
    void onMobileTypeClick() {


        if (null == mMobileTypePopupMenu) {
            mMobileTypePopupMenu = new PopupMenu(getActivity(), tvMobileType);
            mMobileTypePopupMenu.getMenu().setGroupCheckable(0, true, true);

            int menuIndex = 0;

            mMobileTypePopupMenu.getMenu().add(0, menuIndex, 0, "苹果").setTitleCondensed("10");
            menuIndex++;
            mMobileTypePopupMenu.getMenu().add(0, menuIndex, 0, "谷歌").setTitleCondensed("20");


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

            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, "1格").setTitleCondensed("10");
            menuIndex++;
            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, "2格").setTitleCondensed("20");
            menuIndex++;
            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, "3格").setTitleCondensed("30");
            menuIndex++;
            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, "4格").setTitleCondensed("40");
            menuIndex++;
            mSignalPopupMenu.getMenu().add(0, menuIndex, 0, "5格").setTitleCondensed("50");


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

            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, "Wifi").setTitleCondensed("10");
            menuIndex++;
            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, "G").setTitleCondensed("20");
            menuIndex++;
            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, "E").setTitleCondensed("30");
            menuIndex++;
            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, "3G").setTitleCondensed("40");
            menuIndex++;
            mNetworkPopupMenu.getMenu().add(0, menuIndex, 0, "4G").setTitleCondensed("50");


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
