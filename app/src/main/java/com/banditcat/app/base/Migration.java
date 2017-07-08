package com.banditcat.app.base;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * 文件名称：{@link Migration}
 * <br/>
 * 功能描述：
 * <br/>
 * 创建作者：banditcat
 * <br/>
 * 创建时间：16/6/3 16:24
 * <br/>
 * 修改作者：banditcat
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


    }
}
