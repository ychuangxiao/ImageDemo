package com.banditcat.app.views.activitys;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.banditcat.app.R;
import com.banditcat.app.di.HasComponent;
import com.banditcat.app.di.components.BizComponent;
import com.banditcat.app.di.components.DaggerBizComponent;
import com.banditcat.app.views.base.BaseActivity;
import com.banditcat.app.views.fragment.LoginFragment;
import com.banditcat.app.views.fragment.MainFragment;
import com.banditcat.app.views.fragment.MoreFragment;
import com.banditcat.common.fontawesom.typeface.BaseFontAwesome;

import butterknife.BindView;


public class MainActivity extends BaseActivity implements HasComponent<BizComponent> {

    MainFragment mMainFragment;
    String mainFragmentTag = "MainFragment";


    MoreFragment mMoreFragment;
    String moreFragmentTag = "MoreFragment";

    LoginFragment mLoginFragment;
    String loginFragmentTag = "LoginFragment";

    Fragment fragment;
    @BindView(R.id.content)
    FrameLayout content;


    @BindView(R.id.navigation)
    BottomNavigationView navigation;


    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {
        this.initializeInjector();

        setToolTitle(getString(R.string.title_activity_main)).setToolTitleGravity(Gravity.CENTER);


        setLeftMenu();
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigation.setSelectedItemId(R.id.navigation_home);

    }


    /**
     * 设置抽屉菜单
     */
    void setLeftMenu() {

        Float size = 10F;


        MenuItem menuItem = navigation.getMenu().findItem(R.id.navigation_home);
        setMenu(menuItem, BaseFontAwesome.Icon.icon_nva_home, size);


        menuItem = navigation.getMenu().findItem(R.id.navigation_dashboard);
        setMenu(menuItem, BaseFontAwesome.Icon.icon_nva_me, size);


        menuItem = navigation.getMenu().findItem(R.id.navigation_notifications);
        setMenu(menuItem, BaseFontAwesome.Icon.icon_nav_more, size);


    }


    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }


    void hideFragment() {

        fragment = getSupportFragmentManager().findFragmentByTag(mainFragmentTag);

        if (fragment != null) {
            mMainFragment = (MainFragment) fragment;

            hideFragment(mMainFragment);
        }


        fragment = getSupportFragmentManager().findFragmentByTag(moreFragmentTag);

        if (fragment != null) {
            mMoreFragment = (MoreFragment) fragment;

            hideFragment(mMoreFragment);
        }


        fragment = getSupportFragmentManager().findFragmentByTag(loginFragmentTag);

        if (fragment != null) {
            mLoginFragment = (LoginFragment) fragment;
            hideFragment(mLoginFragment);
        }


    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new
            BottomNavigationView
                    .OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    hideFragment();
                    switch (item.getItemId()) {
                        case R.id.navigation_home:


                            if (mMainFragment != null) {

                                showFragment(mMainFragment);

                            } else {

                                mMainFragment = MainFragment.newInstance();
                                addFragment(R.id.content, mMainFragment, mainFragmentTag);

                            }

                            return true;
                        case R.id.navigation_dashboard:


                            if (mLoginFragment != null) {

                                showFragment(mLoginFragment);
                            } else {
                                mLoginFragment = LoginFragment.newInstance();
                                addFragment(R.id.content, mLoginFragment, loginFragmentTag);
                            }

                            return true;
                        case R.id.navigation_notifications:


                            if (mMoreFragment != null) {

                                showFragment(mMoreFragment);

                            } else {

                                mMoreFragment = MoreFragment.newInstance();
                                addFragment(R.id.content, mMoreFragment, moreFragmentTag);

                            }


                            return true;
                    }
                    return false;
                }

            };

    @Override
    public BizComponent getComponent() {
        return mBizComponent;
    }

    BizComponent mBizComponent;

    private void initializeInjector() {
        this.mBizComponent = DaggerBizComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }
}
