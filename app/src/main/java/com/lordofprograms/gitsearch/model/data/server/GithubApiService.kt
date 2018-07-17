package com.lordofprograms.gitsearch.model.data.server

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.realm.RealmObject
import retrofit.Retrofit

class GithubApiService {

    private val BASE_URL = "https://api.github.com/"

    private fun buildGson(): Gson = GsonBuilder().setExclusionStrategies(object : ExclusionStrategy {
        override fun shouldSkipField(f: FieldAttributes): Boolean {
            return f.declaringClass == RealmObject::class.java
        }

        override fun shouldSkipClass(clazz: Class<*>): Boolean {
            return false
        }
    }).create()

    fun buildApiService(): GithubApi = Retrofit.Builder()
            .addCallAdapterFactory(retrofit.RxJavaCallAdapterFactory.create())
            .addConverterFactory(retrofit.GsonConverterFactory.create(buildGson()))
            .baseUrl(BASE_URL)
            .build()
            .create(GithubApi::class.java)

}