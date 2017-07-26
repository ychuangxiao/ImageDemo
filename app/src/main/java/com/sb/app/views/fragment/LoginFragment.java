package com.sb.app.views.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ilogie.android.library.common.util.ArrayUtils;
import com.sb.app.R;
import com.sb.app.di.components.BizComponent;
import com.sb.app.mvp.presenters.user.AutoLoginPresenter;
import com.sb.app.mvp.presenters.user.LoginPresenter;
import com.sb.app.mvp.presenters.user.LogoutPresenter;
import com.sb.app.mvp.views.AutoLoginHandleView;
import com.sb.app.mvp.views.BaseHandleView;
import com.sb.app.mvp.views.LoginView;
import com.sb.app.utils.ViewUtils;
import com.sb.app.views.activitys.user.RegActivity;
import com.sb.app.views.base.BaseFragmentDaggerActivity;
import com.sb.app.views.widget.ClearEditText;
import com.sb.common.fontawesom.typeface.BaseFontAwesome;
import com.sb.data.constant.TextConstant;
import com.sb.data.entitys.realm.UserRealm;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends BaseFragmentDaggerActivity implements LoginView, AutoLoginHandleView,
        BaseHandleView {


    @BindView(R.id.etUsername)
    ClearEditText etUsername;

    @BindView(R.id.etPassword)
    ClearEditText etPassword;

    @BindView(R.id.btnHandle)
    AppCompatButton btnHandle;

    @BindView(R.id.tvReg)
    AppCompatTextView tvReg;

    @BindView(R.id.tvUserName)
    AppCompatTextView tvUserName;

    @BindView(R.id.accountConstraintLayout)
    ConstraintLayout accountConstraintLayout;

    @BindView(R.id.loginLinearLayout)
    LinearLayout loginLinearLayout;

    @BindView(R.id.tvGrade)
    AppCompatTextView tvGrade;

    @BindView(R.id.tvWatermark)
    AppCompatTextView tvWatermark;

    @BindView(R.id.watermarkHandleRelativeLayout)
    RelativeLayout watermarkHandleRelativeLayout;


    @Inject
    LoginPresenter mLoginPresenter;


    @Inject
    AutoLoginPresenter mAutoLoginPresenter;//用户自动登录登录中间者


    @Inject
    LogoutPresenter mLogoutPresenter;


    public LoginFragment() {
        setRetainInstance(true);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MoreFragment.
     */
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
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
        mLoginPresenter.attachView(this);
        mAutoLoginPresenter.attachView(this);
        mLogoutPresenter.attachView(this);
    }

    @Override
    protected void DestroyView() {

    }

    /**
     * 初始化视图，工具条等信息
     */
    @Override
    public void initView() {
        ViewUtils.setCompoundLeftDrawables(getContext(), etUsername, BaseFontAwesome.Icon.icon_user, getResources()
                .getColor(R.color
                        .colorPrimary), 8f);
        ViewUtils.setCompoundLeftDrawables(getContext(), etPassword, BaseFontAwesome.Icon.icon_key, getResources()
                .getColor(R.color
                        .colorPrimary), 8f);
        mProgressDialog.setCanceledOnTouchOutside(false);


        refreshData();
    }


    public void refreshData() {
        mAutoLoginPresenter.autoLogin();
    }


    /**
     * 获得布局视图ID
     *
     * @return 视图ID
     */
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_login;
    }


    /**
     * 导航至首页
     */
    @Override
    public void navigateToHome() {


        tvUserName.setText(getApplicationComponent(getContext().getApplicationContext()).context().sharedpreferences
                .UserId().get());
        loginLinearLayout.setVisibility(View.VISIBLE);
        accountConstraintLayout.setVisibility(View.GONE);
        btnHandle.setVisibility(View.GONE);
        tvReg.setVisibility(View.GONE);

        tvGrade.setText("免费会员");
        tvWatermark.setText("未去水印");

        if (!getApplicationComponent(getContext().getApplicationContext()).context().sharedpreferences.Watermark().get()) {
            tvGrade.setText("永久会员");
            tvWatermark.setText("已去水印");
        }

    }

    /**
     * 自动登录失败，跳转至登录
     */
    @Override
    public void navigateToLogin() {

        etPassword.setText("");
        loginLinearLayout.setVisibility(View.GONE);
        accountConstraintLayout.setVisibility(View.VISIBLE);
        btnHandle.setVisibility(View.VISIBLE);
        tvReg.setVisibility(View.VISIBLE);
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
        showToastMessage(message);
    }

    /**
     * 导航至用户登录
     */
    @Override
    public void navigateLogin() {


    }

    @Override
    public Context context() {
        return this.getActivity().getApplicationContext();
    }

    @OnClick(R.id.btnHandle)
    void onLoginClick() {
        mLoginPresenter.initLoginInfo(etUsername.getText().toString().trim(), etPassword.getText().toString().trim());
        mLoginPresenter.login();
    }

    @OnClick(R.id.btnLogout)
    void onLogoutClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(getResources().getString(R.string.title_setting_confirm)).setPositiveButton
                (getResources().getString(R.string
                        .title_setting_confirm_yes), new DialogInterface.OnClickListener() {// 退出按钮
                    public void onClick(DialogInterface dialog, int i) {


                        mProgressDialog.setMessage(getString(R.string.alert_handle));

                        mLogoutPresenter.logout();


                    }
                }).setNegativeButton(getResources().getString(R.string.title_setting_confirm_no), null).show
                ();// 显示对话框

    }

    /**
     * 成功调用通知
     */
    @Override
    public void noticeSuccess() {


        navigateToLogin();
    }

    @Override
    public void onResume() {
        super.onResume();

        Realm userRealm = Realm.getDefaultInstance();
        RealmResults<UserRealm> users = userRealm.where(UserRealm.class).findAllSorted(TextConstant
                .COLUMN_NAME_FOR_LATESTDATE, Sort.DESCENDING);
        if (null != users && ArrayUtils.isNotEmpty(users)) {
            etUsername.setText(users.first().getUserUid());
            etUsername.setSelection(etUsername.length());
        }
    }

    @OnClick(R.id.tvReg)
    void onRegClick() {
        navigateActivity(new Intent(getActivity(), RegActivity.class));
    }

    @OnClick(R.id.watermarkHandleRelativeLayout)
    void onWatermarkHandleClick() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(watermarkHandleRelativeLayout.getTag().toString());
        intent.setData(content_url);
        startActivity(intent);
    }
}
