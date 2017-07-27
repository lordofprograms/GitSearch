package com.lordofprograms.gitsearch.screens.main.core

import com.lordofprograms.gitsearch.api.GithubApi
import com.lordofprograms.gitsearch.builders.GithubApiService

/**
 * Created by Михаил on 09.06.2017.
 */
class MainModel {

    fun buildGithubService(): GithubApi {
        return GithubApiService().buildApiService()
    }

}