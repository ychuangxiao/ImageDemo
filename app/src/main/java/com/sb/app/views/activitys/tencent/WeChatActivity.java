package com.sb.app.views.activitys.tencent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.sb.app.R;
import com.sb.app.di.HasComponent;
import com.sb.app.di.components.BizComponent;
import com.sb.app.di.components.DaggerBizComponent;
import com.sb.app.views.base.BaseActivity;
import com.sb.app.views.fragment.ContactFragment;
import com.sb.app.views.fragment.WeChatFindFragment;
import com.sb.app.views.fragment.WeChatMeFragment;

import butterknife.BindView;
import butterknife.OnCheckedChanged;

public class WeChatActivity extends BaseActivity implements HasComponent<BizComponent> {


    WeChatMeFragment mWeChatMeFragment;
    String weChatMeFragmentTag = "WeChatMeFragment";


    WeChatFindFragment mWeChatFindFragment;
    String weChatFindFragmentTag = "WeChatFindFragment";


    ContactFragment mContactFragment;

    String contactFragmentTag = "ContactFragment";


    @BindView(R.id.rdMessage)
    AppCompatRadioButton rdMessage;

    @BindView(R.id.rdContacts)
    AppCompatRadioButton rdContacts;

    @BindView(R.id.rdMe)
    AppCompatRadioButton rdMe;

    @BindView(R.id.rdDiscovery)
    AppCompatRadioButton rdDiscovery;
    @BindView(R.id.bottomNav)
    RadioGroup mRadioGroup;


    Fragment fragment;

    @Override
    public void initView() {
        setToolTitle(getString(R.string.title_activity_we_chat))
                .setHomeOnClickListener();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        this.mBizComponent = DaggerBizComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();

        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_we_chat;
    }

    @OnCheckedChanged({R.id.rdMessage, R.id.rdContacts, R.id.rdDiscovery, R.id.rdMe})
    void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

        if (!checked) {
            return;
        }

        //所有的都false

        rdContacts.setChecked(false);
        rdDiscovery.setChecked(false);
        rdMe.setChecked(false);
        rdMessage.setChecked(false);


        hideFragment();

        switch (compoundButton.getId()) {
            case R.id.rdMessage:
                rdMessage.requestFocus();
                rdMessage.setChecked(true);
                break;
            case R.id.rdContacts:

                rdContacts.setChecked(true);
                if (mContactFragment != null) {

                    showFragment(mContactFragment);

                } else {

                    mContactFragment = ContactFragment.newInstance("", "");
                    addFragment(R.id.content, mContactFragment, contactFragmentTag);

                }


                break;
            case R.id.rdDiscovery:

                rdDiscovery.setChecked(true);
                if (mWeChatFindFragment != null) {

                    showFragment(mWeChatFindFragment);

                } else {

                    mWeChatFindFragment = WeChatFindFragment.newInstance("", "");
                    addFragment(R.id.content, mWeChatFindFragment, weChatFindFragmentTag);

                }

                break;
            case R.id.rdMe:

                rdMe.setChecked(true);
                if (mWeChatMeFragment != null) {

                    showFragment(mWeChatMeFragment);

                } else {

                    mWeChatMeFragment = WeChatMeFragment.newInstance("", "");
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

        fragment = getSupportFragmentManager().findFragmentByTag(weChatFindFragmentTag);

        if (fragment != null) {
            mWeChatFindFragment = (WeChatFindFragment) fragment;

            hideFragment(mWeChatFindFragment);
        }

        fragment = getSupportFragmentManager().findFragmentByTag(contactFragmentTag);

        if (fragment != null) {
            mContactFragment = (ContactFragment) fragment;

            hideFragment(mContactFragment);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_wechat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            return true;

        }

        return super.onOptionsItemSelected(item);
    }


    BizComponent mBizComponent;


    @Override
    public BizComponent getComponent() {
        return mBizComponent;
    }
}