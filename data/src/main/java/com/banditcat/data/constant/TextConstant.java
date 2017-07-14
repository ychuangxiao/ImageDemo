package com.banditcat.data.constant;


/**
 * 文件名称：{@link TextConstant}
 * <br/>
 * 功能描述： 文本常量
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/7/13 09:53
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：16/7/13 09:53
 * <br/>
 * 修改备注：
 */
public class TextConstant {
    /**
     * 认证头
     */
    public static final String AUTHOR_NAME = "Authorization";

    /**
     * api key
     */
    public static final String API_KEY = "667e238b14812c1672f96187086df894";

    public static final String BASE_ERROR_MSG = "网络异常,请稍后重试!";

    public static final String BASE_ERROR_UN_AUTHORIZATION_MSG = "您未登录,请登录后重试!";

    public static final String BASE_SERVER_ERROR_MSG = "服务器异常,请稍后重试!";

    public static final String BASE_NO_DATA_ERROR_MSG = "未获取到数据！,请稍后重试!";

    public static final String APP_VERSION_SERVER = "E95000";//版本更新代码

    public static final String HEADER_NAME_ONE = "Ver1";
    public static final String HEADER_NAME_TWO = "Ver2";
    public static final String HEADER_CONNECTION = "Connection";//接收类型

    public static final String HEADER_ACCEPT = "Accept";//接收类型
    public static final String HEADER_CONTENT_TYPE = "Content-Type";//请求内容类型

    public static final String HEADER_CONNECTION_VALUE = "close";//close

    public static final String HEADER_APPLICATION_JSON_VALUE = "application/json";//json类型

    public static final String COLUMN_NAME_FOR_PASSWORD = "password";//认证信息字段
    public static final String COLUMN_NAME_FOR_LATESTDATE = "latestDate";//最后登录时间戳字段
    public static final String TABLE_COLUMN_ID = "id"; //服务器配置地址ID
    public static final String TABLE_COLUMN_SERVER_LAST_UPDATE_TIME = "latestUpdateDate"; //服务器配置最后更新时间

    /**
     * 更新地址扩展参数
     */
    public static final String UPDATE_APP_ADDRESS_EXTRA = "update_app_address_extra";



    public static final String ACTION_LOGOUT="ACTION_LOGOUT";
    public static final String ACTION_REG="ACTION_REG";


}