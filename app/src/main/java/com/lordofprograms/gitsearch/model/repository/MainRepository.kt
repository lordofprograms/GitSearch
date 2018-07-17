package com.lordofprograms.gitsearch.model.repository

import com.lordofprograms.gitsearch.entity.GithubUser
import com.lordofprograms.gitsearch.model.data.db.DBService
import com.lordofprograms.gitsearch.model.data.server.GithubApiService
import com.lordofprograms.gitsearch.model.system.SchedulersProvider
import rx.Observable

class MainRepository(private val githubApiService: GithubApiService,
                     private val dbService: DBService,
                     private val schedulers: SchedulersProvider) {

    fun getGithubUser(name: String): Observable<GithubUser> =
            githubApiService.buildApiService().getGithubUser(name)
                    .subscribeOn(schedulers.internet())
                    .observeOn(schedulers.ui())

    fun saveUserToDb(githubUser: GithubUser) = dbService.save(githubUser, GithubUser::class.java)


}