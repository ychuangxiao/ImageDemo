package com.sb.data.rest;

/**
 * 文件名称：{@link Endpoint}
 * <br/>
 * 功能描述：URL端点
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：2017/4/18 15:50
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：2017/4/18 15:50
 * <br/>
 * 修改备注：
 */
public class Endpoint {
    private final String endpoint;

    public Endpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
