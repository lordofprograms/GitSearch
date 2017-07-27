package com.lordofprograms.gitsearch.api

import com.lordofprograms.gitsearch.db.models.Github
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

/**
 * Created by ahmedrizwan on 15/03/2016.
 *
 */

interface GithubApi {
  @GET("users/{username}")
  fun getGithubUser(@Path("username") username: String): Observable<Github>
}



