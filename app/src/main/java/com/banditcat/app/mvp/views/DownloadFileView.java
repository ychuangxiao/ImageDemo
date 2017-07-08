package com.banditcat.app.mvp.views;


import java.io.File;

/**
 * 文件名称：{@link DownloadFileView}
 * <br/>
 * 功能描述：附件上传视图
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/5 12:40
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：16/6/5 12:40
 * <br/>
 * 修改备注：
 */
public interface DownloadFileView extends BaseView {

    /**
     * 下载成功成功
     */
    void downLoadSuccess(File file);


}
