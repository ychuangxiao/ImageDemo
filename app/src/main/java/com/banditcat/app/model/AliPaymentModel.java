package com.banditcat.app.model;

import com.banditcat.app.model.base.BaseMobileModel;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by banditcat-pc on 2017/7/2.
 */

public class AliPaymentModel extends BaseMobileModel implements Serializable {

    private String receiptUserName;
    private String bankNo;
    private BigDecimal receiptMoney;
    private BankModel mBankModel;
    private Boolean finish;
    private String mRemark;//转账说明
    private String paymentType;//付款方式


    private Long paymentTime;//付款成功时间
    private Long lastTime;//到账时间
    private Long createTime;//创建时间


    public String getReceiptUserName() {
        return receiptUserName;
    }

    public void setReceiptUserName(String receiptUserName) {
        this.receiptUserName = receiptUserName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public BigDecimal getReceiptMoney() {
        return receiptMoney;
    }

    public void setReceiptMoney(BigDecimal receiptMoney) {
        this.receiptMoney = receiptMoney;
    }

    public BankModel getBankModel() {
        return mBankModel;
    }

    public void setBankModel(BankModel bankModel) {
        mBankModel = bankModel;
    }

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    public String getRemark() {
        return mRemark;
    }

    public void setRemark(String remark) {
        mRemark = remark;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Long getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Long paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Long getLastTime() {
        return lastTime;
    }

    public void setLastTime(Long lastTime) {
        this.lastTime = lastTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
