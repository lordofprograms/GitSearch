package com.lordofprograms.gitsearch.model.repository

import com.lordofprograms.gitsearch.entity.GithubUser
import com.lordofprograms.gitsearch.model.data.db.DBService

class HistoryRepository(private val dbService: DBService) {

    fun getAll() = dbService.getAll(GithubUser::class.java)

}