package com.lordofprograms.gitsearch.db

import com.lordofprograms.gitsearch.db.models.Github
import com.lordofprograms.gitsearch.db.migration.RealmMigration
import io.realm.*

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * Created by Михаил on 16.07.2017.
 */

class DBService {

    private val config = RealmConfiguration.Builder()
            .schemaVersion(1)
            .migration(RealmMigration())
            .build()

    fun <T : RealmObject> save(`object`: T, clazz: Class<T>): Observable<T> {
        val realm = Realm.getInstance(config)

        var id: Long

        try {
            id = (realm.where(clazz).max("id").toInt() + 1).toLong()
        } catch (e: Exception) {
            id = 0L
        }

        (`object` as Github).id = id

        return Observable.just(`object`)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { t ->
                    Observable.just(t as T)
                            .doOnSubscribe(realm::beginTransaction)
                            .doOnUnsubscribe {
                                realm.commitTransaction()
                                realm.close()
                            }
                            .doOnNext { realm.copyToRealm(it) }
                }
    }

    fun <T : RealmObject> getAll(clazz: Class<T>): Observable<List<T>> {
        val realm = Realm.getInstance(config)

        return Observable.just(clazz)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { t ->
                    Observable.just(t)
                            .doOnSubscribe(realm::beginTransaction)
                            .doOnUnsubscribe {
                                realm.commitTransaction()
                            }
                            .map { type -> realm.where(type).findAll() }
                }
    }

}

