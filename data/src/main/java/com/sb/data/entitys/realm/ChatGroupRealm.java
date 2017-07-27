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

    RealmList<ContactRealm> mContactRealms;

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
}
