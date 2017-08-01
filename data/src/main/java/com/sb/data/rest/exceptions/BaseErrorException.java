package com.sb.data.rest.exceptions;


/**
 * 文件名称：{@link BaseErrorException}
 * <br/>
 * 功能描述： Wrapper around Exceptions used to manage errors in the repository.
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/5 18:52
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/5 18:52
 * <br/>
 * 修改备注：
 */
public class BaseErrorException extends Exception {

    private String errorCode;//错误代码
    private String errorDetails;//错误详情，主要针对更新

    /**
     * 公开构造函数
     * @param errorCode
     */
    public BaseErrorException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    /**
     * 公开构造函数
     *
     * @param message 异常信息
     * @param errorCode
     */
    public BaseErrorException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseErrorException(String message, String errorCode,String errorDetails) {
        super(message);
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
    }




    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDetails() {
        return errorDetails;
    }
}
