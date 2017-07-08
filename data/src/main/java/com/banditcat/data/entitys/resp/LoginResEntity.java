package com.banditcat.data.entitys.resp;


/**
 * 文件名称：{@link LoginResEntity}
 * <br/>
 * 功能描述：用户登录响应实体
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/5 18:55
 * <br/>
 * 修改作者：banditcat
 * <br/>
 * 修改时间：16/6/5 18:55
 * <br/>
 * 修改备注：
 */
public class LoginResEntity {


    private String authCode;//认证代码

    /**用户信息*/
    private String userCode;//用户名
    private String userNick;//姓名
    private String corp;//公司代码
    private String corpText;//公司名称
    private String orgn;//机构代码
    private String orgnText;//机构名称
    private String ware;//仓库代码
    private String wareText;//仓库名称
    private String lang;//语言


    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public String getCorpText() {
        return corpText;
    }

    public void setCorpText(String corpText) {
        this.corpText = corpText;
    }

    public String getOrgn() {
        return orgn;
    }

    public void setOrgn(String orgn) {
        this.orgn = orgn;
    }

    public String getOrgnText() {
        return orgnText;
    }

    public void setOrgnText(String orgnText) {
        this.orgnText = orgnText;
    }

    public String getWare() {
        return ware;
    }

    public void setWare(String ware) {
        this.ware = ware;
    }

    public String getWareText() {
        return wareText;
    }

    public void setWareText(String wareText) {
        this.wareText = wareText;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
