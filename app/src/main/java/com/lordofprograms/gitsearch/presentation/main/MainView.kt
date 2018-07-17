package com.lordofprograms.gitsearch.presentation.main

/**
 * Created by Михаил on 09.06.2017.
 */
interface MainView{

    fun showGithubUser(avatarUrl: String?, name: String?, publicRepos: String?)
    fun showSnackbar(text: String)

}