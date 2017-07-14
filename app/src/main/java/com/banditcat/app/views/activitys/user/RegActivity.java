package com.banditcat.app.views.activitys.user;

import android.content.Context;

import com.banditcat.app.R;
import com.banditcat.app.di.components.DaggerBizComponent;
import com.banditcat.app.di.modules.BizModule;
import com.banditcat.app.model.RegModel;
import com.banditcat.app.mvp.presenters.BaseRespPresenter;
import com.banditcat.app.mvp.views.BaseHandleView;
import com.banditcat.app.views.base.BaseDaggerActivity;
import com.banditcat.app.views.widget.ClearEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class RegActivity extends BaseDaggerActivity implements BaseHandleView {


    @BindView(R.id.etUserName)
    ClearEditText etUserName;

    @BindView(R.id.etPwd)
    ClearEditText etPwd;

    @BindView(R.id.etQQ)
    ClearEditText etQQ;


    @Inject
    BaseRespPresenter mBaseRespPresenter;

    /**
     * 初始化注解
     */
    @Override
    public void initInjector() {
        DaggerBizComponent.builder().applicationComponent(getApplicationComponent()).activityModule(getActivityModule
                ()).bizModule(new BizModule())
                .build().inject(this);
    }

    /**
     * 初始化中间者
     */
    @Override
    public void initPresenter() {
        mBaseRespPresenter.attachView(this);
    }

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {

        setToolTitle(getString(R.string.title_activity_reg)).setDisplayHome(true).setHomeOnClickListener();
        mProgressDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.activity_reg;
    }

    /**
     * 成功调用通知
     */
    @Override
    public void noticeSuccess() {

        alertMsg(getString(R.string.alert_handle_reg_success));
        finish();
    }

    /**
     * Show a view with a progress bar indicating a loading process.
     */
    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    /**
     * Hide a loading view.
     */
    @Override
    public void hideLoading() {
        mProgressDialog.dismiss();
    }

    /**
     * Show a retry view in case of an error when retrieving data.
     */
    @Override
    public void showRetry() {

    }

    /**
     * Hide a retry view shown if there was an error when retrieving data.
     */
    @Override
    public void hideRetry() {

    }

    /**
     * Show an error message
     *
     * @param message A string representing an error.
     */
    @Override
    public void showError(String message) {
        alertMsg(message);
    }

    /**
     * 导航至用户登录
     */
    @Override
    public void navigateLogin() {

    }

    /**
     * Get a {@link Context}.
     */
    @Override
    public Context context() {
        return this;
    }

    @OnClick(R.id.btnHandle)
    void onRegClick() {


        if (!validViewEmpty(etUserName, getString(R.string.hint_user_name))) {

            return;
        }

        if (!validViewEmpty(etPwd, getString(R.string.hint_user_name))) {

            return;
        }

        if (etPwd.getText().toString().length() < 6) {
            alertMsg("密码至少6位！");
            return;
        }

        if (!validViewEmpty(etQQ, getString(R.string.hint_user_name))) {

            return;
        }

        RegModel model = new RegModel();
        model.setName(etUserName.getText().toString().trim());
        model.setPwd(etPwd.getText().toString().trim());
        model.setQq(etQQ.getText().toString().trim());

        mBaseRespPresenter.register(model);
    }
}
