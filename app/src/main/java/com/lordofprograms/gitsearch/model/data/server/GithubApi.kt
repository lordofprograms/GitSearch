package com.lordofprograms.gitsearch.model.data.server

import com.lordofprograms.gitsearch.entity.GithubUser
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

interface GithubApi {
  @GET("users/{username}")
  fun getGithubUser(@Path("username") username: String): Observable<GithubUser>
}



