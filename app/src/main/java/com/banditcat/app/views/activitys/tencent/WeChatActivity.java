package com.banditcat.app.views.activitys.tencent;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;

import com.banditcat.app.R;
import com.banditcat.app.views.base.BaseActivity;
import com.banditcat.app.views.fragment.LoginFragment;
import com.banditcat.app.views.fragment.MainFragment;
import com.banditcat.app.views.fragment.MoreFragment;
import com.banditcat.app.views.fragment.WeChatMeFragment;

import butterknife.BindView;
import butterknife.OnCheckedChanged;

public class WeChatActivity extends BaseActivity {


    WeChatMeFragment mWeChatMeFragment;
    String weChatMeFragmentTag = "WeChatMeFragment";


    @BindView(R.id.rdMessage)
    AppCompatRadioButton rdMessage;

    @BindView(R.id.rdContacts)
    AppCompatRadioButton rdContacts;

    Fragment fragment;

    @Override
    public void initView() {
        setToolTitle(getString(R.string.title_activity_we_chat))
                .setHomeOnClickListener();


    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_we_chat;
    }

    @OnCheckedChanged({R.id.rdMessage, R.id.rdContacts, R.id.rdDiscovery, R.id.rdMe})
    void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

        hideFragment();

        switch (compoundButton.getId()) {
            case R.id.rdMessage:
                break;
            case R.id.rdContacts:
                break;
            case R.id.rdDiscovery:
                break;
            case R.id.rdMe:
                if (mWeChatMeFragment != null) {

                    showFragment(mWeChatMeFragment);

                } else {

                    mWeChatMeFragment = WeChatMeFragment.newInstance("","");
                    addFragment(R.id.content, mWeChatMeFragment, weChatMeFragmentTag);

                }
                break;
        }

    }


    void hideFragment() {

        fragment = getSupportFragmentManager().findFragmentByTag(weChatMeFragmentTag);

        if (fragment != null) {
            mWeChatMeFragment = (WeChatMeFragment) fragment;

            hideFragment(mWeChatMeFragment);
        }


    }

}
