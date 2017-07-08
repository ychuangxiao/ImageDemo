package com.banditcat.app.views.listeners;

/**
 * 文件名称：{@link RecyclerClickListener}
 * <br/>
 * 功能描述：Recycler单击事件监听
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：11/1/16 10:36
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：11/1/16 10:36
 * <br/>
 * 修改备注：
 */
public interface RecyclerClickListener<T> {

    void onItemClickListener(T model);
}
