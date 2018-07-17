package com.lordofprograms.gitsearch.presentation.main

import com.lordofprograms.gitsearch.entity.GithubUser
import com.lordofprograms.gitsearch.model.interactor.MainInteractor
import java.util.concurrent.TimeUnit

class MainPresenter(private val interactor: MainInteractor, private val view: MainView) {

    fun searchByName(name: String) {
        interactor.getUser(name)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribe(
                        { onNext(it) },
                        { view.showSnackbar(it.message ?: "Ups something went wrong") }
                )
    }

    private fun onNext(user: GithubUser) {
        view.showGithubUser(user.avatarUrl, user.name, "Public Repos: ${user.publicRepos}")
        interactor.saveGithubUser(user).subscribe { view.showSnackbar("Successfully saved to DB") }
    }

}
