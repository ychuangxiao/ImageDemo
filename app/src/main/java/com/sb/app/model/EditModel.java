package com.sb.app.model;

/**
 * Created by banditcat on 2017/7/16.
 */

public class EditModel {

    private int handleAction;
    private String hintText;
    private String text;
    private int maxLength;
    private int inputType;
    private int maxLines=1;

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
}
