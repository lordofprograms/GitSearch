package com.lordofprograms.gitsearch.presentation.history

import com.lordofprograms.gitsearch.entity.GithubUser

interface HistoryView {
    fun setList(usersList: List<GithubUser>)
    fun showEmpty(usersList: List<GithubUser>)
}