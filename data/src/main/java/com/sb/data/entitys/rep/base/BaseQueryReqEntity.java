package com.sb.data.entitys.rep.base;

/**
 * 文件名称：{@link BaseQueryReqEntity}
 * <br/>
 * 功能描述：分页请求基类
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/17 15:31
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/17 15:31
 * <br/>
 * 修改备注：
 */
public class BaseQueryReqEntity extends BaseReqEntity{

    private int pageSize = 10;
    private int page = 1;



    public BaseQueryReqEntity() {
    }

    public BaseQueryReqEntity(int pageSize, int page) {
        this.pageSize = pageSize;
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

}
