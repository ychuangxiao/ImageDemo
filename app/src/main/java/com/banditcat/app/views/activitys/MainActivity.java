package com.banditcat.app.views.activitys;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.banditcat.app.R;
import com.banditcat.app.views.base.BaseActivity;
import com.banditcat.app.views.fragment.MainFragment;
import com.banditcat.app.views.fragment.MoreFragment;
import com.banditcat.common.fontawesom.typeface.BaseFontAwesome;

import butterknife.BindView;


public class MainActivity extends BaseActivity {

    MainFragment mMainFragment;
    String mainFragmentTag = "MainFragment";


    MoreFragment mMoreFragment;
    String moreFragmentTag = "MoreFragment";

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


    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView
            .OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            hideFragment();
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    fragment = getSupportFragmentManager().findFragmentByTag(mainFragmentTag);

                    if (fragment != null) {
                        mMainFragment = (MainFragment) fragment;
                        showFragment(mMainFragment);

                    } else {

                        mMainFragment = MainFragment.newInstance();
                        addFragment(R.id.content, mMainFragment, mainFragmentTag);

                    }

                    return true;
                case R.id.navigation_dashboard:


                    return true;
                case R.id.navigation_notifications:


                    fragment = getSupportFragmentManager().findFragmentByTag(moreFragmentTag);

                    if (fragment != null) {
                        mMoreFragment = (MoreFragment) fragment;
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

}
