package com.sb.app.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by banditcat on 2017/7/16.
 */

public class EditModel implements Parcelable {

    private int handleAction;
    private String hintText;
    private String text;
    private int maxLength;
    private int inputType;
    private int maxLines=1;
    private String title;

    public int getHandleAction() {
        return handleAction;
    }

    public void setHandleAction(int handleAction) {
        this.handleAction = handleAction;
    }

    public String getHintText() {
        return hintText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getInputType() {
        return inputType;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    public int getMaxLines() {
        return maxLines;
    }

    public void setMaxLines(int maxLines) {
        this.maxLines = maxLines;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EditModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.handleAction);
        dest.writeString(this.hintText);
        dest.writeString(this.text);
        dest.writeInt(this.maxLength);
        dest.writeInt(this.inputType);
        dest.writeInt(this.maxLines);
        dest.writeString(this.title);
    }

    protected EditModel(Parcel in) {
        this.handleAction = in.readInt();
        this.hintText = in.readString();
        this.text = in.readString();
        this.maxLength = in.readInt();
        this.inputType = in.readInt();
        this.maxLines = in.readInt();
        this.title = in.readString();
    }

    public static final Creator<EditModel> CREATOR = new Creator<EditModel>() {
        @Override
        public EditModel createFromParcel(Parcel source) {
            return new EditModel(source);
        }

        @Override
        public EditModel[] newArray(int size) {
            return new EditModel[size];
        }
    };
}
