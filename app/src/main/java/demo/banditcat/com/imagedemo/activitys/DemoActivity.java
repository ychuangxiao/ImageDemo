package demo.banditcat.com.imagedemo.activitys;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.OnClick;
import demo.banditcat.com.imagedemo.R;
import demo.banditcat.com.imagedemo.base.BaseActivity;

public class DemoActivity extends BaseActivity {
    @BindView(R.id.fab)
    FloatingActionButton fab;

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {

    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_demo;
    }

    @OnClick(R.id.fab)
    void onFabClick()
    {

    }
}
