package com.sb.app.base;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * 文件名称：{@link Migration}
 * <br/>
 * 功能描述：
 * <br/>
 * 创建作者：administrator
 * <br/>
 * 创建时间：16/6/3 16:24
 * <br/>
 * 修改作者：administrator
 * <br/>
 * 修改时间：16/6/3 16:24
 * <br/>
 * 修改备注：
 */
public class Migration implements RealmMigration {
    /**
     * This method will be called if a migration is needed. The entire method is wrapped in a
     * write transaction so it is possible to create, update or delete any existing objects
     * without wrapping it in your own transaction.
     *
     * @param realm      the Realm schema on which to perform the migration.
     * @param oldVersion the schema version of the Realm at the start of the migration.
     * @param newVersion the schema version of the Realm after executing the migration.
     */
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        if (oldVersion == 1) {


            //删除数据

            realm.where("ContactRealm").findAll().deleteAllFromRealm();
            realm.where("ChatGroupRealm").findAll().deleteAllFromRealm();
            realm.where("WebChatMessageRealm").findAll().deleteAllFromRealm();


            //添加消息接受者
            schema.get("WebChatMessageRealm")

                    .addField("sendTransferTime", Long.class)
                    .addField("receiveTransferTime", Long.class)
                    .addRealmObjectField("sendContact", schema.get("ContactRealm"));


            schema.get("ContactRealm")
                    .addField("money", Double.class)
                    .addField("weChatNo", String.class);


            //手机外观

            schema.create("MobileStyleRealm")
                    .addField("topStatusColor", Integer.class)
                    .addField("topToolStyle", Integer.class)
                    .addField("mobileVersion", Integer.class)
                    .addField("networkType", Integer.class)
                    .addField("networkSignal", Integer.class)
                    .addField("mobileCarrier", String.class)
                    .addField("topTime", Long.class)
                    .addField("date24TimeStyle", Boolean.class)
                    .addField("isLocation", Boolean.class)
                    .addField("isBlueTeeth", Boolean.class)
                    .addField("isBatteryNum", Boolean.class)
                    .addField("isBatteryAdd", Boolean.class)
                    .addField("isDir", Boolean.class)
                    .addField("batteryNumBar", Integer.class);

            oldVersion++;
        }


    }
}
