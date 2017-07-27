package com.lordofprograms.gitsearch.builders

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.lordofprograms.gitsearch.api.GithubApi
import io.realm.RealmObject
import retrofit.Retrofit

/**
 * Created by Михаил on 09.06.2017.
 */
class GithubApiService {

    private val BASE_URL = "https://api.github.com/"

    fun buildGson(): Gson {
        val gson = GsonBuilder().setExclusionStrategies(object : ExclusionStrategy {
            override fun shouldSkipField(f: FieldAttributes): Boolean {
                return f.declaringClass == RealmObject::class.java
            }

            override fun shouldSkipClass(clazz: Class<*>): Boolean {
                return false
            }

        }).create()
        return gson
    }

    fun buildApiService(): GithubApi {
        val retrofit: Retrofit = Retrofit.Builder()
                .addCallAdapterFactory(retrofit.RxJavaCallAdapterFactory.create())
                .addConverterFactory(retrofit.GsonConverterFactory.create(buildGson()))
                .baseUrl(BASE_URL)
                .build()

        return retrofit.create(GithubApi::class.java)
    }

}