package demo.banditcat.com.imagedemo.adapters.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import demo.banditcat.com.imagedemo.listeners.RecyclerClickListener;
import demo.banditcat.com.imagedemo.model.MenuModel;
import demo.banditcat.com.imagedemo.viewgroup.PaymentMenuItemView;


/**
 * 文件名称：{@link PaymentMenuAdapter}
 * <br/>
 * 功能描述：收货选项适配器
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：2017/4/19 17:12
 * <br/>
 * 修改作者：banditcat
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
