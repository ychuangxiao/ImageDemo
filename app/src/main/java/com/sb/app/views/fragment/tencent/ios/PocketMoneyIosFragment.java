package com.sb.app.views.fragment.tencent.ios;

import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.InputType;

import com.sb.app.R;
import com.sb.app.model.EditModel;
import com.sb.app.utils.MathUtils;
import com.sb.app.views.base.BaseFragment;
import com.sb.app.views.fragment.BottomSheetEditSignFragment;
import com.sb.app.views.listeners.ContactClickListener;
import com.sb.app.views.listeners.MobileChangeListener;
import com.sb.data.entitys.realm.ContactRealm;
import com.sb.data.entitys.realm.MobileStyleRealm;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.realm.Realm;


/**
 * 文件名称：{@link PocketMoneyIosFragment}
 * <br/>
 * 功能描述： 零钱（Android）
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：2017/7/19 09:53
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：2017/7/19 09:53
 * <br/>
 * 修改备注：
 */
public class PocketMoneyIosFragment extends BaseFragment implements ContactClickListener<Double> {


    public PocketMoneyIosFragment() {
        mRealm = Realm.getDefaultInstance();
    }

    Realm mRealm;
    static BottomNavigationView mBottomNavigationView;
    @BindView(R.id.tvMoney)
    AppCompatTextView mTvMoney;
    @BindView(R.id.tvPocketMoney)
    AppCompatTextView mTvPocketMoney;
    @BindView(R.id.btnHandle)
    AppCompatButton mBtnHandle;
    @BindView(R.id.btnHandleOut)
    AppCompatButton mBtnHandleOut;
    Unbinder unbinder;
    BottomSheetEditSignFragment mBottomSheetEditSignFragment;


    ContactRealm mContactRealm;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PocketMoneyFragment.
     */
    public static PocketMoneyIosFragment newInstance(BottomNavigationView
                                                          bottomNavigationView) {
        PocketMoneyIosFragment fragment = new PocketMoneyIosFragment();
        mBottomNavigationView = bottomNavigationView;
        return fragment;
    }

    @Override
    protected void DestroyView() {
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();

        }
    }

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {

        mContactRealm = mRealm.where(ContactRealm.class).equalTo("isMe", true).findFirst();

        mEditModel = new EditModel();

        mEditModel.setTitle("修改零钱金额");
        mEditModel.setHintText("零钱");
        mEditModel.setMaxLength(11);
        mEditModel.setText(MathUtils.toString(new BigDecimal(mContactRealm.getMoney().toString())));
        mEditModel.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);


        mTvPocketMoney.setText(String.format("￥%s", MathUtils.toString(new BigDecimal(mContactRealm.getMoney().toString()))));
    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_pocket_money_ios;
    }

    MobileChangeListener<MobileStyleRealm> mModelMobileChangeListener;

    public void setMobileChangeListener(MobileChangeListener<MobileStyleRealm>
                                                modelMobileChangeListener) {
        this.mModelMobileChangeListener = modelMobileChangeListener;
    }


    EditModel mEditModel;

    @OnClick(R.id.tvPocketMoney)
    void onMoneyClick() {

        mEditModel.setText(MathUtils.toString(new BigDecimal(mContactRealm.getMoney().toString())));
        mBottomSheetEditSignFragment = BottomSheetEditSignFragment.newInstance(mEditModel);
        mBottomSheetEditSignFragment.setDoubleContactClickListener(this);
        mBottomSheetEditSignFragment.show(getActivity().getSupportFragmentManager(), "BottomSheetDateTimeFragment");

    }

    @Override
    public void onItemClickListener(final Double model) {


        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mContactRealm.setMoney(model);
            }
        });
        mTvPocketMoney.setText(String.format("￥%s", MathUtils.toString(new BigDecimal(model.toString()))));
    }

}
