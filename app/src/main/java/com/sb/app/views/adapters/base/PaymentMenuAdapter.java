package com.sb.app.views.adapters.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.sb.app.views.listeners.RecyclerClickListener;
import com.sb.app.model.MenuModel;
import com.sb.app.views.viewgroup.PaymentMenuItemView;


/**
 * 文件名称：{@link PaymentMenuAdapter}
 * <br/>
 * 功能描述：收货选项适配器
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：2017/4/19 17:12
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：2017/4/19 17:12
 * <br/>
 * 修改备注：
 */
public class PaymentMenuAdapter extends RecyclerViewAdapterBase<MenuModel, PaymentMenuItemView> {


    Context mContext;


    public PaymentMenuAdapter(Context context) {
        this.mContext = context;

    }

    @Override
    protected PaymentMenuItemView onCreateItemView(ViewGroup parent, int viewType) {
        return PaymentMenuItemView.build(mContext);
    }


    @Override
    public void onBindViewHolder(ViewWrapper<PaymentMenuItemView> viewHolder, final int position) {
        PaymentMenuItemView view = viewHolder.getView();
        view.binder(items.get(position));

        if (null != mRecyclerClickListener) {

            view.getTitleView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecyclerClickListener.onItemClickListener(items.get(position));
                }
            });

        }
    }


    RecyclerClickListener<MenuModel> mRecyclerClickListener;

    public void setRecyclerClickListener(RecyclerClickListener<MenuModel> recyclerClickListener) {
        this.mRecyclerClickListener = recyclerClickListener;
    }
}
