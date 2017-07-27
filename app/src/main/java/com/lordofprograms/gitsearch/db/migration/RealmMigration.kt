package com.lordofprograms.gitsearch.db.migration

import io.realm.DynamicRealm

/**
 * Created by Михаил on 16.07.2017.
 */

class RealmMigration : io.realm.RealmMigration {

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val schema = realm.schema

            schema.create("Github")
                    .addField("name", String::class.java)
                    .addField("public_repos", Int::class.java)
                    .addField("avatar", String::class.java)
    }

    override fun hashCode(): Int {
        return RealmMigration::class.java.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return other != null && other is RealmMigration
    }
}
