package com.sb.app.views.activitys.tencent;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;

import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.base.BaseActivity;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.ContactRealm;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;


public class ChangeFaceActivity extends BaseActivity {
    @BindView(R.id.headerImage)
    AppCompatImageView mHeaderImage;
    @BindView(R.id.etFriendName)
    AppCompatEditText etFriendName;
    @BindView(R.id.btnHandleFace)
    AppCompatButton mBtnHandleFace;
    @BindView(R.id.btnHandleName)
    AppCompatButton mBtnHandleName;
    private String userId;


    Realm mRealm;


    ContactRealm mContactRealm;

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {

        injectExtras();
        setToolTitle(getString(R.string.title_activity_change_face)).setDisplayHome(true)
                .setHomeOnClickListener();
        mRealm = Realm.getDefaultInstance();


        mContactRealm = mRealm.where(ContactRealm.class).equalTo(TextConstant.COLUMN_NAME_FOR_USERID_CONTACTREALM,
                userId).findFirst();

        etFriendName.setText(mContactRealm.getUserNick());

        if (mContactRealm.isSystem()) {
            mHeaderImage.setImageResource(ViewUtils.getDefaultFace()[mContactRealm
                    .getImageIndex()]);
        }

    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_change_face;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();

        }
    }


    /**
     * 初始化参数
     */
    private void injectExtras() {
        Bundle extras_ = getIntent().getExtras();
        if (extras_ == null) {

            finish();
            return;
        }

        if (!extras_.containsKey(AppConstant.EXTRA_NO)) {
            finish();
            return;
        }


        userId = extras_.getString(AppConstant.EXTRA_NO);


        if (StringUtils.isEmpty(userId)) {
            finish();
            return;
        }


    }

    @OnClick(R.id.btnHandleName)
    void onHandleNameClick() {


        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                mContactRealm.setUserNick(ViewUtils.getDefaultNick()[ViewUtils.getRandomIndex(28)]);

                etFriendName.setText(mContactRealm.getUserNick());


            }
        });
    }


    @OnClick(R.id.btnHandleFace)
    void onHandleFaveClick() {

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                mContactRealm.setSystem(true);
                mContactRealm.setImageIndex(ViewUtils.getRandomIndex(28));
                mHeaderImage.setImageResource(ViewUtils.getDefaultFace()[mContactRealm.getImageIndex()]);


            }
        });

    }

    @OnClick(R.id.btnHandleSave)
    void onSaveClick() {

        if (StringUtils.isEmpty(etFriendName.getText().toString().trim()))
        {
            alertMsg("好友昵称不能为空！");
            return;
        }
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {


                mContactRealm.setUserNick(etFriendName.getText().toString().trim());


            }
        });

        alertMsg("保存成功！");
    }


}
