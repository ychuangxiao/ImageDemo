package com.sb.app.views.listeners;

/**
 * 文件名称：{@link ContactClickListener}
 * <br/>
 * 功能描述：通讯录单机事件
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：11/1/16 10:36
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：11/1/16 10:36
 * <br/>
 * 修改备注：
 */
public interface ContactClickListener<T> {

    void onItemClickListener(T model);
}
