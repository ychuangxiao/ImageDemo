package com.sb.data.entitys.base;

/**
 * 文件名称：{@link BaseRespEntity}
 * <br/>
 * 功能描述：响应基类实体
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/5 18:55
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/5 18:55
 * <br/>
 * 修改备注：
 */
public class BaseRespEntity<T> {

    private T data;//数据
    private boolean success = false;//成功状态
    private String errorCode;//api接口业务调用错误，返回错误枚举值，便于app业务处理
    private String errorDetails;//便于更新操作
    private String msg;//api接口业务调用失败，返回经过梳理的提示语


    private Integer pageSize;//页大小
    private Integer page;//页索引

    private Integer totalPage;//总页数
    private Integer totalCount;//总记录数


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErr() {
        return errorCode;
    }

    public void setErr(String err) {
        this.errorCode = err;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
