package com.sb.app.views.viewgroup;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RelativeLayout;

import com.sb.common.fontawesom.IconicsDrawable;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.sb.app.R;
import com.sb.app.model.menu.HomeHandleModel;

/**
 * 文件名称：{@link HomeItemView}
 * <br/>
 * 功能描述：首页视图
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
public class MainItemView extends RelativeLayout {



    @BindView(R.id.btnHandle)
    AppCompatTextView btnHandle;

    @BindView(R.id.rl_container)
    RelativeLayout mRelativeLayout;

    @BindView(R.id.topImage)
    AppCompatImageView mLogoImageView;




    private boolean alreadyInflated = false;

    public RelativeLayout getRelativeLayout() {
        return mRelativeLayout;
    }

    Context mContext;//上下文

    /**
     * 静态视图绑定
     *
     * @param context 上下文
     * @return
     */
    public static MainItemView build(Context context) {
        MainItemView instance = new MainItemView(context);
        instance.onFinishInflate();
        return instance;
    }

    public MainItemView(Context context) {
        super(context);
        mContext = context;
    }


    /**
     * 绑定按钮标题
     *
     * @param homeHandleModel 操作视图模型
     */
    public void binder(HomeHandleModel homeHandleModel) {
        btnHandle.setText(homeHandleModel.getHandleText());

        if (homeHandleModel.getIcon() != null)
        {
            Drawable drawable = new IconicsDrawable(mContext
                    , homeHandleModel.getIcon()).actionBar(18F)
                    .color(mContext.getResources().getColor(homeHandleModel.getColor())).paddingDp(2);

            mLogoImageView.setImageDrawable(drawable);
            mLogoImageView.setVisibility(View.VISIBLE);
        }

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
            inflate(getContext(), R.layout.item_main, this);
            ButterKnife.bind(this);
        }
        super.onFinishInflate();
    }


}
