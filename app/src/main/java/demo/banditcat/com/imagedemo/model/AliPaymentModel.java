package demo.banditcat.com.imagedemo.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by banditcat-pc on 2017/7/2.
 */

public class AliPaymentModel implements Serializable {


    private Integer topToolStyle;//工具栏样式
    private Integer mobileType;//手机类型
    private Integer networkType;//网络类型
    private Integer networkSignal;//信号强度

    private String receiptUserName;
    private String bankNo;

    private BigDecimal receiptMoney;

    private BankModel mBankModel;

    private Boolean finish;

    private String mRemark;//转账说明

    private String paymentType;//付款方式

    private Long topTime;//顶部时间
    private Long paymentTime;//付款成功时间
    private Long lastTime;//到账时间

    private Boolean dateTimeStyle=false;//是否24小时
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

    public BankModel getBankModel() {
        return mBankModel;
    }

    public void setBankModel(BankModel bankModel) {
        mBankModel = bankModel;
    }

    public Long getTopTime() {
        return topTime;
    }

    public void setTopTime(Long topTime) {
        this.topTime = topTime;
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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getReceiptMoney() {
        return receiptMoney;
    }

    public void setReceiptMoney(BigDecimal receiptMoney) {
        this.receiptMoney = receiptMoney;
    }

    public String getRemark() {
        return mRemark;
    }

    public void setRemark(String remark) {
        mRemark = remark;
    }

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    public Boolean getDateTimeStyle() {
        return dateTimeStyle;
    }

    public void setDateTimeStyle(Boolean dateTimeStyle) {
        this.dateTimeStyle = dateTimeStyle;
    }

    public Integer getNetworkType() {
        return networkType;
    }

    public void setNetworkType(Integer networkType) {
        this.networkType = networkType;
    }

    public Integer getNetworkSignal() {
        return networkSignal;
    }

    public void setNetworkSignal(Integer networkSignal) {
        this.networkSignal = networkSignal;
    }

    public Integer getMobileType() {
        return mobileType;
    }

    public void setMobileType(Integer mobileType) {
        this.mobileType = mobileType;
    }

    public Integer getTopToolStyle() {
        return topToolStyle;
    }

    public void setTopToolStyle(Integer topToolStyle) {
        this.topToolStyle = topToolStyle;
    }
}
