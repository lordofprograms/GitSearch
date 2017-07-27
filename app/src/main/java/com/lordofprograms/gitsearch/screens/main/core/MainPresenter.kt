package com.lordofprograms.gitsearch.screens.main.core

import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import com.lordofprograms.gitsearch.db.DBService
import com.lordofprograms.gitsearch.db.models.Github
import com.lordofprograms.gitsearch.screens.main.MainActivity
import com.lordofprograms.gitsearch.utils.snackBar
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import java.util.concurrent.TimeUnit

/**
 * Created by Михаил on 09.06.2017.
 */

class MainPresenter(val activity: MainActivity, val model: MainModel, val view: MainView) {

    val subscriptions: CompositeSubscription = CompositeSubscription()
    var name: String = ""

    fun onCreate() {
        Realm.init(activity.applicationContext)
        subscriptions.add(getUserInfo())
    }

    fun onDestroy() {
        subscriptions.clear()
    }

    fun getUserInfo(): Subscription {
        var sub = secondarySub(name)

        activity.searchAccount.setOnEditorActionListener { _, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                sub = secondarySub(activity.searchAccount.text.toString())
            }

            return@setOnEditorActionListener true
        }

        return sub
    }


    fun secondarySub(name: String): Subscription{
        return model.buildGithubService().getGithubUser(name)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(
                        { user ->
                            val dbService = DBService()
                            user.name = activity.searchAccount.text.toString()
                            user.publicRepos = user.publicRepos
                            user.avatarUrl = user.avatarUrl
                            dbService.save(user, Github::class.java)
                                    .subscribe{snackBar(activity.container, "Successfully saved to DB")}
                            view.updateViews(activity, user)
                        },
                        { error ->
                            Log.e("Error", error.message)
                            if(activity.searchAccount.text.toString().trim() != "" || error.message != "HTTP 404 Not Found")
                            snackBar(activity.container, "${error.message}")
                        })
    }

}
