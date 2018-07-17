package com.lordofprograms.gitsearch.utils

import com.lordofprograms.gitsearch.model.data.db.DBService
import com.lordofprograms.gitsearch.model.data.server.GithubApiService
import com.lordofprograms.gitsearch.model.interactor.HistoryInteractor
import com.lordofprograms.gitsearch.model.interactor.MainInteractor
import com.lordofprograms.gitsearch.model.repository.HistoryRepository
import com.lordofprograms.gitsearch.model.repository.MainRepository
import com.lordofprograms.gitsearch.model.system.AppSchedulers

object Injections {

    fun getMainInteractor(): MainInteractor =
            MainInteractor(MainRepository(GithubApiService(), DBService(), AppSchedulers()))

    fun getHistoryInteractor(): HistoryInteractor = HistoryInteractor(HistoryRepository( DBService()))

}