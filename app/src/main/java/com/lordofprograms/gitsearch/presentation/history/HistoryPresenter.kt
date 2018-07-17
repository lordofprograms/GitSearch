package com.lordofprograms.gitsearch.presentation.history

import com.lordofprograms.gitsearch.entity.GithubUser
import com.lordofprograms.gitsearch.model.interactor.HistoryInteractor
import com.lordofprograms.gitsearch.ui.history.adapter.HistoryAdapter
import rx.Subscription
import rx.subscriptions.CompositeSubscription
import java.util.*

class HistoryPresenter(private val interactor: HistoryInteractor, private val view: HistoryView) {

    private val historyList = ArrayList<GithubUser>()

    fun loadRecyclerView() {
        interactor.getAll()
                .doOnCompleted { view.showEmpty(historyList) }
                .subscribe { models ->
                    if (historyList.isEmpty()) {
                        historyList += models
                        historyList.reverse()
                    }
                    view.setList(historyList)
                }
    }

}