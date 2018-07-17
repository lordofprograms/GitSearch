package com.lordofprograms.gitsearch.model.interactor

import com.lordofprograms.gitsearch.model.repository.HistoryRepository

class HistoryInteractor(private val repository: HistoryRepository) {

    fun getAll() = repository.getAll()

}