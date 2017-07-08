package com.banditcat.data.rest.exceptions;


/**
 * 文件名称：{@link BaseErrorException}
 * <br/>
 * 功能描述： Wrapper around Exceptions used to manage errors in the repository.
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/5 18:52
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：16/6/5 18:52
 * <br/>
 * 修改备注：
 */
public class BaseErrorException extends Exception {

    private String errorCode;//错误代码

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


    public String getErrorCode() {
        return errorCode;
    }
}
