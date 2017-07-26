package com.sb.data.entitys.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2017/7/23.
 */

public class WebChatMessageRealm extends RealmObject {


    @PrimaryKey
    private String id;

    private Integer messageType=0;


    private ContactRealm mUserRealm;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }


    public ContactRealm getUserRealm() {
        return mUserRealm;
    }

    public void setUserRealm(ContactRealm userRealm) {
        mUserRealm = userRealm;
    }
}


