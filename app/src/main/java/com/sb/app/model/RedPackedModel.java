package com.sb.app.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/7/28.
 */

public class RedPackedModel implements Parcelable {


    private BigDecimal amount;
    private String content;
    private int source;
    private int sendType;

    private String userId;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getSendType() {
        return sendType;
    }

    public void setSendType(int sendType) {
        this.sendType = sendType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public RedPackedModel() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.amount);
        dest.writeString(this.content);
        dest.writeInt(this.source);
        dest.writeInt(this.sendType);
        dest.writeString(this.userId);
    }

    protected RedPackedModel(Parcel in) {
        this.amount = (BigDecimal) in.readSerializable();
        this.content = in.readString();
        this.source = in.readInt();
        this.sendType = in.readInt();
        this.userId = in.readString();
    }

    public static final Creator<RedPackedModel> CREATOR = new Creator<RedPackedModel>() {
        @Override
        public RedPackedModel createFromParcel(Parcel source) {
            return new RedPackedModel(source);
        }

        @Override
        public RedPackedModel[] newArray(int size) {
            return new RedPackedModel[size];
        }
    };
}
