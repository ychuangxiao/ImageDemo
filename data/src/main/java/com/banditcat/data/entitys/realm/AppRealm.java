package com.banditcat.data.entitys.realm;

import io.realm.RealmObject;

/**
 * Created by banditcat on 2017/7/15.
 */

public class AppRealm extends RealmObject {

    Long initTime;

    public Long getInitTime() {
        return initTime;
    }

    public void setInitTime(Long initTime) {
        this.initTime = initTime;
    }
}
