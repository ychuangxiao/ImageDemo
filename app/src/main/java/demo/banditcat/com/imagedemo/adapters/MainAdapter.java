package demo.banditcat.com.imagedemo.adapters;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import demo.banditcat.com.imagedemo.adapters.base.RecyclerViewAdapterBase;
import demo.banditcat.com.imagedemo.adapters.base.ViewWrapper;
import demo.banditcat.com.imagedemo.listeners.RecyclerClickListener;
import demo.banditcat.com.imagedemo.model.menu.HomeHandleModel;
import demo.banditcat.com.imagedemo.viewgroup.MainItemView;


/**
 * 文件名称：{@link MainAdapter}
 * <br/>
 * 功能描述：首页操作按钮适配器
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/1 17:04
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：16/6/1 17:04
 * <br/>
 * 修改备注：
 */
public class MainAdapter extends RecyclerViewAdapterBase<HomeHandleModel, MainItemView> {


    private final String TAG = getClass().getName();
    Context mContext;


    public MainAdapter(Context context) {
        this.mContext = context;

    }

    @Override
    protected MainItemView onCreateItemView(ViewGroup parent, int viewType) {

        return MainItemView.build(mContext);
    }


    @Override
    public void onBindViewHolder(ViewWrapper<MainItemView> viewHolder, final int position) {
        MainItemView view = viewHolder.getView();
        view.binder(items.get(position));

        if (null != mRecyclerClickListener && items.get(position).getEnable()) {



            view.getRelativeLayout().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecyclerClickListener.onItemClickListener(items.get(position));
                }
            });

        }

    }


    RecyclerClickListener<HomeHandleModel> mRecyclerClickListener;

    public void setRecyclerClickListener(RecyclerClickListener<HomeHandleModel> recyclerClickListener) {
        this.mRecyclerClickListener = recyclerClickListener;
    }
}
