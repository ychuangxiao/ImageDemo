package com.sb.app.views.activitys.tencent;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sb.app.R;
import com.sb.app.constant.AppConstant;
import com.sb.app.di.components.BizComponent;
import com.sb.app.views.adapters.BankAdapter;
import com.sb.app.views.adapters.WeChatMessageAdapter;
import com.sb.app.views.base.BaseFragment;
import com.sb.app.views.base.BaseFragmentDaggerActivity;
import com.sb.data.entitys.realm.ContactRealm;
import com.sb.data.entitys.realm.WebChatMessageRealm;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmList;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeChatMessageActivityFragment extends BaseFragmentDaggerActivity {


    @BindView(R.id.emojiRecycler)
    RecyclerView emojiRecycler;

    @BindView(R.id.btnEmoji)
    AppCompatImageView btnEmoji;


    @BindView(R.id.recyclerList)
    RecyclerView recyclerList;


    @Inject
    WeChatMessageAdapter mWeChatMessageAdapter;


    Realm mRealm;


    public WeChatMessageActivityFragment() {
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    protected void DestroyView() {
        if (mRealm != null) {
            mRealm.close();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {


        mBaseRecyclerView = recyclerList;
        initLinearLayoutRecyclerView(new LinearLayoutManager(getActivity())).setAdapter(mWeChatMessageAdapter);


        final List<WebChatMessageRealm> webChatMessageRealms = new ArrayList<>();


        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                WebChatMessageRealm webChatMessageRealm;

                ContactRealm me = realm.where(ContactRealm.class).equalTo("isMe", true).findFirst();


                ContactRealm other = realm.where(ContactRealm.class).equalTo("isMe", false).findFirst();


                for (int i = 0; i < 10; i++) {

                    webChatMessageRealm = realm.createObject(WebChatMessageRealm.class, UUID.randomUUID().toString());




                    if (i == 0) {
                        webChatMessageRealm.setUserRealm(me);
                    } else {
                        webChatMessageRealm.setUserRealm(other);
                    }

                    webChatMessageRealm.setMessageType(AppConstant.MESSAGE_TYPE_RED_PACKED);
                   /* if (i / 2 == 0) {
                        webChatMessageRealm.setMessageType(AppConstant.MESSAGE_TYPE_TRANSFER);
                    }
                    else {
                        webChatMessageRealm.setMessageType(AppConstant.MESSAGE_TYPE_RED_PACKED);
                    }*/
                    webChatMessageRealms.add(webChatMessageRealm);

                }
            }
        });


        mWeChatMessageAdapter.setMessageRealms(webChatMessageRealms);
    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_we_chat_message;
    }


    @OnClick(R.id.btnEmoji)
    void onEmojiClick() {
        //emojiRecycler.setVisibility(View.VISIBLE);
    }

    /**
     * 初始化注解
     */
    @Override
    public void initInjector() {
        this.getComponent(BizComponent.class).inject(this);
    }

    /**
     * 初始化中间者
     */
    @Override
    public void initPresenter() {

    }
}
