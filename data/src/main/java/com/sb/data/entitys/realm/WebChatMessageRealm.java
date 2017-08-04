package com.sb.data.entitys.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Administrator on 2017/7/23.
 */
@RealmClass
public class WebChatMessageRealm extends RealmObject {


    @PrimaryKey
    private String id;

    private String groupId;//组ID

    private Integer messageType = 0;
    private String message;//聊天内容：红包的描述 转账的描述等等
    private String subMessage;//转账详情
    private Double amount;//金额
    private Integer amountStatus;//转账、红包 状态；ME 已转  对方 已收/未收
    private ContactRealm mContactRealm;//实际操作者（接受者）
    private String sourceMessage;
    private Long sendTime;
    private ContactRealm sendContact;//发送者

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getAmountStatus() {
        return amountStatus;
    }

    public void setAmountStatus(Integer amountStatus) {
        this.amountStatus = amountStatus;
    }

    public ContactRealm getContactRealm() {
        return mContactRealm;
    }

    public void setContactRealm(ContactRealm contactRealm) {
        mContactRealm = contactRealm;
    }

    public String getSourceMessage() {
        return sourceMessage;
    }

    public void setSourceMessage(String sourceMessage) {
        this.sourceMessage = sourceMessage;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public String getSubMessage() {
        return subMessage;
    }

    public void setSubMessage(String subMessage) {
        this.subMessage = subMessage;
    }

    public ContactRealm getSendContact() {
        return sendContact;
    }

    public void setSendContact(ContactRealm sendContact) {
        this.sendContact = sendContact;
    }
}


