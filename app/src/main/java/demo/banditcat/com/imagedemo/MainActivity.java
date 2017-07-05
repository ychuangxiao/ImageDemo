package demo.banditcat.com.imagedemo;


import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.BindView;
import demo.banditcat.com.imagedemo.base.BaseActivity;
import demo.banditcat.com.imagedemo.fragment.MainFragment;

public class MainActivity extends BaseActivity {

    MainFragment mMainFragment;
    String mainFragmentTag = "MainFragment";
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

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navigation.setSelectedItemId(R.id.navigation_home);

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

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView
            .OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


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





                    return true;
            }
            return false;
        }

    };

}
