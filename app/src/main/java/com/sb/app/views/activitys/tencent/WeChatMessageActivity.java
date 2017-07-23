package com.sb.app.views.activitys.tencent;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.views.base.BaseActivity;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.ContactRealm;

import io.realm.Realm;

public class WeChatMessageActivity extends BaseActivity {

    private String userId;


    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mRealm = Realm.getDefaultInstance();
        super.onCreate(savedInstanceState);



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

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {


        injectExtras();

        //获取用户信息

        ContactRealm contactRealm = mRealm.where(ContactRealm.class).equalTo(TextConstant
                .COLUMN_NAME_FOR_USERID_CONTACTREALM, userId).findFirst();

        if (contactRealm == null || StringUtils.isEmpty(contactRealm.getUserId())) {
            finish();
            return;
        }


        setToolTitle(contactRealm.getUserNick()).setDisplayHome(true)
                .setHomeOnClickListener();

    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_we_chat_message;
    }

}
