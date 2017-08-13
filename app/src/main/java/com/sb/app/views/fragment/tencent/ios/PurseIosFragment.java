package com.sb.app.views.fragment.tencent.ios;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.AppCompatTextView;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.sb.app.R;
import com.sb.app.utils.MathUtils;
import com.sb.app.views.activitys.tencent.money.PocketMoneyActivity;
import com.sb.app.views.base.BaseFragment;
import com.sb.app.views.listeners.MobileChangeListener;
import com.sb.data.entitys.realm.ContactRealm;
import com.sb.data.entitys.realm.MobileStyleRealm;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;

/**
 * A placeholder fragment containing a simple view.
 */
public class PurseIosFragment extends BaseFragment {


    public PurseIosFragment() {
        mRealm = Realm.getDefaultInstance();
    }

    Realm mRealm;

    static BottomNavigationView mBottomNavigationView;

    ContactRealm mContactRealm;

    @BindView(R.id.relativeLayout1)
    RelativeLayout relativeLayout1;


    @BindView(R.id.relativeLayout4)
    RelativeLayout relativeLayout4;


    @BindView(R.id.relativeLayout7)
    RelativeLayout relativeLayout7;


    @BindView(R.id.relativeLayout10)
    RelativeLayout relativeLayout10;

    @BindView(R.id.relativeLayout13)
    RelativeLayout relativeLayout13;

    @BindView(R.id.relativeLayout16)
    RelativeLayout relativeLayout16;

    @BindView(R.id.tvMoney)
    AppCompatTextView tvMoney;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PocketMoneyFragment.
     */
    public static PurseIosFragment newInstance(BottomNavigationView
                                                    bottomNavigationView) {
        PurseIosFragment fragment = new PurseIosFragment();
        mBottomNavigationView = bottomNavigationView;
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


        mContactRealm = mRealm.where(ContactRealm.class).equalTo("isMe", true).findFirst();

        tvMoney.setText(String.format("￥%s", MathUtils.toString(new BigDecimal(mContactRealm.getMoney().toString()))));


        DisplayMetrics dm = new DisplayMetrics();
        //获取屏幕信息

        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        int screenWidth = (dm.widthPixels / 3);

        int screenHeight = (int) (dm.widthPixels / 3 * 0.85);


        ViewGroup.LayoutParams layoutParams = relativeLayout1.getLayoutParams();

        layoutParams.width = screenWidth;
        layoutParams.height = screenHeight;

        relativeLayout1.setLayoutParams(layoutParams);


        layoutParams = relativeLayout4.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = screenHeight;
        relativeLayout4.setLayoutParams(layoutParams);


        layoutParams = relativeLayout7.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = screenHeight;
        relativeLayout7.setLayoutParams(layoutParams);


        layoutParams = relativeLayout10.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = screenHeight;
        relativeLayout10.setLayoutParams(layoutParams);


        layoutParams = relativeLayout13.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = screenHeight;
        relativeLayout13.setLayoutParams(layoutParams);


        layoutParams = relativeLayout16.getLayoutParams();
        layoutParams.width = screenWidth;
        layoutParams.height = screenHeight;
        relativeLayout16.setLayoutParams(layoutParams);

    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_purse_ios;
    }

    @OnClick(R.id.tvPocketMoney)
    void onPocketMoneyClick() {
        navigateActivity(new Intent(getActivity(), PocketMoneyActivity.class));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();

        }
    }

    MobileChangeListener<MobileStyleRealm> mModelMobileChangeListener;

    public void setMobileChangeListener(MobileChangeListener<MobileStyleRealm>
                                                modelMobileChangeListener) {
        this.mModelMobileChangeListener = modelMobileChangeListener;
    }
}
