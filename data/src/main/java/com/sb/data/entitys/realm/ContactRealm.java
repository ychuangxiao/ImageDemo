package com.sb.data.entitys.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2017/7/23.
 */

public class ContactRealm extends RealmObject {

    private String userNick;
    private String imgPath;

    @PrimaryKey
    private String userId;

    private boolean isMe = false;

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isMe() {
        return isMe;
    }

    public void setMe(boolean me) {
        isMe = me;
    }
}
