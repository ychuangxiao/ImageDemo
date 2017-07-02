package demo.banditcat.com.imagedemo.adapters;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


import demo.banditcat.com.imagedemo.adapters.base.RecyclerViewAdapterBase;
import demo.banditcat.com.imagedemo.adapters.base.ViewWrapper;
import demo.banditcat.com.imagedemo.listeners.RecyclerClickListener;
import demo.banditcat.com.imagedemo.model.BankModel;
import demo.banditcat.com.imagedemo.viewgroup.HomeItemView;

/**
 * 文件名称：{@link BankAdapter}
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
public class BankAdapter extends RecyclerViewAdapterBase<BankModel, HomeItemView> {


    private final String TAG = getClass().getName();
    Context mContext;

    private Integer defaultBank;

    public BankAdapter(Context context, Integer defaultBank) {
        this.mContext = context;
        this.defaultBank = defaultBank;
    }

    @Override
    protected HomeItemView onCreateItemView(ViewGroup parent, int viewType) {

        return HomeItemView.build(mContext);
    }


    @Override
    public void onBindViewHolder(ViewWrapper<HomeItemView> viewHolder, final int position) {
        HomeItemView view = viewHolder.getView();
        view.binder(items.get(position), defaultBank);
        if (null != mRecyclerClickListener) {

            view.getHandleTitle().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mRecyclerClickListener.onItemClickListener(items.get(position));
                }
            });

        }

    }


    RecyclerClickListener<BankModel> mRecyclerClickListener;

    public void setRecyclerClickListener(RecyclerClickListener<BankModel> recyclerClickListener) {
        this.mRecyclerClickListener = recyclerClickListener;
    }
}
