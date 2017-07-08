package com.banditcat.app.views.viewgroup;


import android.content.Context;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import com.banditcat.app.R;
import com.banditcat.app.model.AliPaymentModel;

/**
 * 文件名称：{@link PrimaryTopTitleView}
 * <br/>
 * 功能描述：顶部导航栏视图
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/1 17:08
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：16/6/1 17:08
 * <br/>
 * 修改备注：
 */
public class PrimaryTopTitleView extends RelativeLayout {


    Context mContext;//上下文
    boolean alreadyInflated = false;




    /**
     * 静态视图绑定
     *
     * @param context 上下文
     * @return
     */
    public static PrimaryTopTitleView build(Context context) {
        PrimaryTopTitleView instance = new PrimaryTopTitleView(context);
        instance.onFinishInflate();
        return instance;
    }

    public PrimaryTopTitleView(Context context) {
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
            inflate(getContext(), R.layout.google_primary, this);
            ButterKnife.bind(this);
        }
        super.onFinishInflate();
    }


}
