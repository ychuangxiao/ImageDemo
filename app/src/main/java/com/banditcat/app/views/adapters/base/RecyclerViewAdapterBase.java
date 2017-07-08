package com.banditcat.app.views.adapters.base;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 文件名称：RecyclerViewAdapterBase
 * <br/>
 * 功能描述：RecyclerView 基类适配器
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/1 16:52
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：16/6/1 16:52
 * <br/>
 * 修改备注：
 */
public abstract class RecyclerViewAdapterBase<T, V extends View> extends RecyclerView.Adapter<ViewWrapper<V>> {

    protected ArrayList<T> items = new ArrayList<>();


    Context mContext;

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }


    public void setItems(Collection<T> items) {


        setItems(items, false);

    }


    public ArrayList<T> getItems() {
        return items;
    }

    /**
     * remove item
     *
     * @param item item
     */
    public void remove(T item) {
        int position = items.indexOf(item);
        items.remove(position);
        notifyItemRemoved(position);


        this.notifyDataSetChanged();
    }

    public void remove(Integer position) {

        items.remove(position);
        notifyItemRemoved(position);


        this.notifyDataSetChanged();
    }

    public void add(T item, int position) {
        items.add(position, item);
        notifyItemInserted(position);
    }


    public void setItems(Collection<T> tArrayList, boolean isNew) {

        if (isNew) {

            this.items.clear();


        }

        this.items.addAll(tArrayList);


        this.notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ViewWrapper<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewWrapper<V>(onCreateItemView(parent, viewType));
    }

    protected abstract V onCreateItemView(ViewGroup parent, int viewType);
}
