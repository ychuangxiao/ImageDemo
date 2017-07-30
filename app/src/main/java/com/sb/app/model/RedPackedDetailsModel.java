package com.sb.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/7/28.
 */

public class RedPackedDetailsModel implements Parcelable {



    private String groupId;
    private String messageId;
    private String sendUserId;
    private String receivedUserId;


    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getReceivedUserId() {
        return receivedUserId;
    }

    public void setReceivedUserId(String receivedUserId) {
        this.receivedUserId = receivedUserId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.groupId);
        dest.writeString(this.messageId);
        dest.writeString(this.sendUserId);
        dest.writeString(this.receivedUserId);
    }

    public RedPackedDetailsModel() {
    }

    protected RedPackedDetailsModel(Parcel in) {
        this.groupId = in.readString();
        this.messageId = in.readString();
        this.sendUserId = in.readString();
        this.receivedUserId = in.readString();
    }

    public static final Creator<RedPackedDetailsModel> CREATOR = new Creator<RedPackedDetailsModel>() {
        @Override
        public RedPackedDetailsModel createFromParcel(Parcel source) {
            return new RedPackedDetailsModel(source);
        }

        @Override
        public RedPackedDetailsModel[] newArray(int size) {
            return new RedPackedDetailsModel[size];
        }
    };
}
