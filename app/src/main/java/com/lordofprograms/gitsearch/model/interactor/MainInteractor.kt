package com.lordofprograms.gitsearch.model.interactor

import com.lordofprograms.gitsearch.entity.GithubUser
import com.lordofprograms.gitsearch.model.repository.MainRepository

class MainInteractor(private val repository: MainRepository) {

    fun getUser(name: String) = repository.getGithubUser(name)

    fun saveGithubUser(githubUser: GithubUser) = repository.saveUserToDb(githubUser)

}