package com.sb.data.entitys.realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Administrator on 2017/7/28.
 */

public class ChatGroupRealm extends RealmObject {

    @PrimaryKey
    private String id;

    private String groupName;

    private int topFlag = 0;

    private boolean isPay = false;

    RealmList<ContactRealm> mContactRealms;

    private boolean isGroupChat = false;

    private int groupChatCount = 0;

    private long lastTime=0;

    private String lastMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public RealmList<ContactRealm> getContactRealms() {
        return mContactRealms;
    }

    public void setContactRealms(RealmList<ContactRealm> contactRealms) {
        mContactRealms = contactRealms;
    }

    public int getTopFlag() {
        return topFlag;
    }

    public void setTopFlag(int topFlag) {
        this.topFlag = topFlag;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    public boolean isGroupChat() {
        return isGroupChat;
    }

    public void setGroupChat(boolean groupChat) {
        isGroupChat = groupChat;
    }

    public int getGroupChatCount() {
        return groupChatCount;
    }

    public void setGroupChatCount(int groupChatCount) {
        this.groupChatCount = groupChatCount;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
