package com.sb.data.entitys.rep;


import com.sb.data.entitys.rep.base.BaseReqEntity;

/**
 * 文件名称：{@link LogoutReqEntity}
 * <br/>
 * 功能描述：退出请求实体
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：2017/4/28 13:34
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：2017/4/28 13:34
 * <br/>
 * 修改备注：
 */
public class LogoutReqEntity extends BaseReqEntity {
    private String userId;//用户编号

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
