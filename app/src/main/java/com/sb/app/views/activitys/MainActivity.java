package com.sb.app.views.activitys;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.sb.app.BuildConfig;
import com.sb.app.R;
import com.sb.app.di.HasComponent;
import com.sb.app.di.components.BizComponent;
import com.sb.app.di.components.DaggerBizComponent;
import com.sb.app.mvp.presenters.BaseRespPresenter;
import com.sb.app.mvp.views.BaseHandleView;
import com.sb.app.utils.TimeUtils;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.base.BaseActivity;
import com.sb.app.views.base.BaseDaggerActivity;
import com.sb.app.views.fragment.LoginFragment;
import com.sb.app.views.fragment.MainFragment;
import com.sb.app.views.fragment.MoreFragment;
import com.sb.common.fontawesom.typeface.BaseFontAwesome;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.AppRealm;
import com.sb.data.entitys.realm.UserRealm;
import com.ilogie.android.library.common.util.StringUtils;

import javax.inject.Inject;

import butterknife.BindView;
import io.realm.Realm;


public class MainActivity extends BaseDaggerActivity implements HasComponent<BizComponent>, BaseHandleView {

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



    @Inject
    BaseRespPresenter mBaseRespPresenter;

    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRealm = Realm.getDefaultInstance();


    }

    @Override
    public void initInjector() {
        this.mBizComponent = DaggerBizComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();

        this.mBizComponent.inject(this);
    }

    @Override
    public void initPresenter() {
        mBaseRespPresenter.attachView(this);
    }

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {

        setToolTitle(getString(R.string.title_activity_main)).setToolTitleGravity(Gravity.CENTER);

        getApplicationComponent().context().sharedpreferences.ApkUpdated().put(true);
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
                                checkUserInfo();
                            } else {

                                mMainFragment = MainFragment.newInstance();
                                addFragment(R.id.content, mMainFragment, mainFragmentTag);

                            }

                            return true;
                        case R.id.navigation_dashboard:


                            if (mLoginFragment != null) {

                                showFragment(mLoginFragment);
                                mLoginFragment.refreshData();

                            } else {
                                mLoginFragment = LoginFragment.newInstance();
                                addFragment(R.id.content, mLoginFragment, loginFragmentTag);
                            }

                            return true;
                        case R.id.navigation_notifications:


                            if (mMoreFragment != null) {

                                showFragment(mMoreFragment);
                                checkUserInfo();
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


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mRealm != null) {
            mRealm.close();
        }
    }

    Long time = 0L;

    @Override
    protected void onResume() {
        super.onResume();


        checkUserInfo();


    }

    private void checkUserInfo() {
        if (!ViewUtils.isNetworkAvailable(this)) {
            return;
        }





        UserRealm userRealm = mRealm.where(UserRealm.class).findFirst();

        if (userRealm != null && StringUtils.isNotEmpty(userRealm.getPassword()) && userRealm.getActive() ==
                TextConstant.APP_ACTIVE_20) {
            // check app login status

            mBaseRespPresenter.checkToken();
            return;
        } else {

            if (getApplicationComponent().context().sharedpreferences.ApkUpdated().get()) {

                //check is has  new  version
                mBaseRespPresenter.checkVer();
            }

        }


        if (!BuildConfig.HAS_WATERMAR) {
            return;
        }

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                AppRealm appRealm = realm.where(AppRealm.class).findFirst();

                UserRealm userRealm = realm.where(UserRealm.class).findFirst();

                if (userRealm != null && userRealm.getActive() == TextConstant.APP_ACTIVE_20) {
                    time = -1L;
                } else {
                    if (appRealm == null || appRealm.getInitTime() < 0L) {

                        appRealm = realm.createObject(AppRealm.class);
                        appRealm.setInitTime(System.currentTimeMillis());
                    } else {

                        time = appRealm.getInitTime();

                    }
                }


            }
        });


        if (time != null && time != null && time > 0L
                && TimeUtils.daysBetween(time, System.currentTimeMillis()) >= 2) {
            getApplicationComponent().context().sharedpreferences.Watermark().put(true);
        } else {
            getApplicationComponent().context().sharedpreferences.Watermark().put(false);
        }
    }

    @Override
    public void noticeSuccess() {
        if (!getApplicationComponent().context().sharedpreferences.ApkUpdated().get()) {
            return;
        }

        //check is has  new  version
        mBaseRespPresenter.checkVer();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {
        alertMsg(message);
    }

    @Override
    public void navigateLogin() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage(getResources().getString(R.string.title_un_login_confirm)).setPositiveButton
                (getResources().getString(R.string
                        .title_confirm_login), new DialogInterface.OnClickListener() {// 退出按钮
                    public void onClick(DialogInterface dialog, int i) {
                        navigation.setSelectedItemId(R.id.navigation_dashboard);
                    }
                }).setNegativeButton(getResources().getString(R.string.title_setting_confirm_no), null).show();// 显示对话框
    }

    @Override
    public Context context() {
        return this;
    }
}
