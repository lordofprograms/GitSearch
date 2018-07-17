package com.lordofprograms.gitsearch.model.data.db.migration

import io.realm.DynamicRealm

class RealmMigration : io.realm.RealmMigration {

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val schema = realm.schema

            schema.create("GithubUser")
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
