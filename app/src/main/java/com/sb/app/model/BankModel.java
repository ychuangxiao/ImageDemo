package com.sb.app.model;

import java.io.Serializable;

/**
 * Created by banditcat-pc on 2017/7/2.
 */

public class BankModel implements Serializable{

    private String bankName;
    private Integer type;

    public BankModel(String bankName, Integer type) {
        this.bankName = bankName;
        this.type = type;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
