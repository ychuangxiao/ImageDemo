package com.banditcat.app.views.viewgroup;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.widget.RelativeLayout;


import com.banditcat.common.fontawesom.IconicsDrawable;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.banditcat.app.R;
import com.banditcat.app.model.MenuModel;

/**
 * 文件名称：{@link PaymentMenuItemView}
 * <br/>
 * 功能描述：选项视图
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：2017/4/19 17:14
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：2017/4/19 17:14
 * <br/>
 * 修改备注：
 */
public class PaymentMenuItemView extends RelativeLayout {

    private boolean alreadyInflated = false;


    @BindView(R.id.tv_title)
    AppCompatTextView mTitleView;//标题控件
    Context mContext;//上下文

    public PaymentMenuItemView(Context context) {
        super(context);
        mContext = context;
    }


    public AppCompatTextView getTitleView() {
        return mTitleView;
    }

    /**
     * 静态视图绑定
     *
     * @param context 上下文
     * @return
     */
    public static PaymentMenuItemView build(Context context) {
        PaymentMenuItemView instance = new PaymentMenuItemView(context);
        instance.onFinishInflate();
        return instance;
    }

    Drawable drawableLeft;
    Drawable drawableRight;

    /**
     * 绑定按钮标题
     *
     * @param menuModel 菜单视图模型
     */
    public void binder(MenuModel menuModel) {

        mTitleView.setTag(menuModel.getId());
        mTitleView.setText(menuModel.getTitle());


        drawableLeft = new IconicsDrawable(mContext, menuModel.getIcon()).actionBar(8F).color(mContext.getResources().getColor(R.color
                .colorPrimary)).paddingDp(2);

        drawableRight = new IconicsDrawable(mContext, menuModel.getRightIcon()).actionBar(6F).color(mContext.getResources().getColor(R.color
                .colorPrimary)).paddingDp(2);

        mTitleView.setCompoundDrawables(drawableLeft, null, drawableRight, null);


    }


    @Override
    public void onFinishInflate() {
        if (!alreadyInflated) {
            alreadyInflated = true;
            inflate(getContext(), R.layout.item_menu, this);
            ButterKnife.bind(this);
        }
        super.onFinishInflate();
    }
}
