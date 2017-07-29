package com.sb.app.views.activitys.tencent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ilogie.android.library.common.util.ArrayUtils;
import com.ilogie.android.library.common.util.StringUtils;
import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.utils.LogUtils;
import com.sb.app.views.base.BaseActivity;
import com.sb.data.entitys.realm.ChatGroupRealm;
import com.sb.data.entitys.realm.ContactRealm;

import java.util.UUID;

import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class ContactDetailActivity extends BaseActivity {


    private String userId;


    Realm mRealm;
    String groupUid = UUID.randomUUID()
            .toString();
    ContactRealm meContactRealm = null;
    ContactRealm otherContactRealm = null;

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

    @Override
    public void initView() {


        setToolTitle(getString(R.string.title_activity_contact_detail)).setDisplayHome(true)
                .setHomeOnClickListener();
        mRealm = Realm.getDefaultInstance();
        injectExtras();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_contact_detail;
    }

    @OnClick(R.id.btnHandle)
    void onHandleClick() {


        meContactRealm = mRealm.where(ContactRealm.class).equalTo("isMe", true).findFirst();
        otherContactRealm = mRealm.where(ContactRealm.class).equalTo("userId", userId).findFirst();

        RealmResults<ChatGroupRealm> chatGroupRealms = mRealm.where(ChatGroupRealm.class)
                .equalTo("mContactRealms.userId", meContactRealm.getUserId())
                .findAll()
                .where()
                .equalTo("mContactRealms.userId", userId)
                .findAll();


        if (ArrayUtils.isEmpty(chatGroupRealms)) {


            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    ChatGroupRealm chatGroupRealm = realm.createObject(ChatGroupRealm.class, groupUid);


                    RealmList<ContactRealm> contactRealms = new RealmList<>();
                    contactRealms.add(meContactRealm);
                    contactRealms.add(otherContactRealm);

                    chatGroupRealm.setContactRealms(contactRealms);
                }
            });
        } else {


            for (ChatGroupRealm chatGroupRealm : chatGroupRealms) {
                if (chatGroupRealm.getContactRealms().size() == 2) {
                    groupUid = chatGroupRealm.getId();
                    break;
                }
            }
        }


        LogUtils.d("ceshi",groupUid);

        Intent intent = new Intent(this, WeChatMessageActivity.class);


        intent.putExtra(AppConstant.EXTRA_NO, groupUid);

        navigateActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();

        }
    }

}
