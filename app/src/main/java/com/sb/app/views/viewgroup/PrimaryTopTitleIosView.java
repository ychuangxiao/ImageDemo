package com.sb.app.views.viewgroup;


import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.sb.app.R;
import com.sb.app.model.AliPaymentModel;

/**
 * 文件名称：{@link PrimaryTopTitleIosView}
 * <br/>
 * 功能描述：顶部导航栏视图(IOS)
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/1 17:08
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/1 17:08
 * <br/>
 * 修改备注：
 */
public class PrimaryTopTitleIosView extends RelativeLayout {


    Context mContext;//上下文
    boolean alreadyInflated = false;

    @BindView(R.id.tvBackHome)
    AppCompatTextView tvBackHome;


    public AppCompatTextView getTvBackHome() {
        return tvBackHome;
    }

    /**
     * 静态视图绑定
     *
     * @param context 上下文
     * @return
     */
    public static PrimaryTopTitleIosView build(Context context) {
        PrimaryTopTitleIosView instance = new PrimaryTopTitleIosView(context);
        instance.onFinishInflate();
        return instance;
    }

    public PrimaryTopTitleIosView(Context context) {
        super(context);
        mContext = context;
    }


    public void binder(AliPaymentModel aliPaymentModel) {

    }


    /**
     * The malreadyInflated hack is needed because of an Android bug
     * which leads to infinite calls of onFinishInflate()
     * when inflating a layout with a parent and using
     * the <merge /> tag.
     */
    @Override
    public void onFinishInflate() {
        if (!alreadyInflated) {
            alreadyInflated = true;
            inflate(getContext(), R.layout.ios_primary, this);
            ButterKnife.bind(this);
        }
        super.onFinishInflate();
    }


}
