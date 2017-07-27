package com.lordofprograms.gitsearch.screens.history.core

import com.lordofprograms.gitsearch.db.models.Github
import com.lordofprograms.gitsearch.screens.history.HistoryActivity
import com.lordofprograms.gitsearch.screens.history.adapter.HistoryAdapter
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_history.*
import rx.Subscription
import rx.subscriptions.CompositeSubscription
import java.util.*

/**
 * Created by Михаил on 23.07.2017.
 */
class HistoryPresenter(val activity: HistoryActivity, val model: HistoryModel, val view: HistoryView) {

    val subscriptions = CompositeSubscription()
    val historyList = ArrayList<Github>()

    fun onCreate(){
        Realm.init(activity)
        subscriptions.add(loadRecyclerView())
    }

    fun loadRecyclerView(): Subscription {
        val adapter = HistoryAdapter(activity)
        return model.buildDBService().getAll(Github::class.java)
                .doOnCompleted{showEmpty()}
                .subscribe { models ->
                    historyList += models
                    Collections.reverse(historyList)

                    adapter.setList(historyList)
                    activity.rv.adapter = adapter
                }
    }

    fun showEmpty(){
        if (historyList.isEmpty()) {
            view.showEmptyText()
        }
        else {
            view.hideEmptyText()
        }
    }

    fun onDestroy(){
        subscriptions.clear()
    }

}