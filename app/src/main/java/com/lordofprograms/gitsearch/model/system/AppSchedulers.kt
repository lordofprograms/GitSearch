package com.lordofprograms.gitsearch.model.system

import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AppSchedulers : SchedulersProvider {

    private val backgroundExecutor: ExecutorService = Executors.newCachedThreadPool()
    private val BACKGROUND_SCHEDULERS: Scheduler = Schedulers.from(backgroundExecutor)
    private val internetExecutor: ExecutorService = Executors.newCachedThreadPool()
    private val INTERNET_SCHEDULERS: Scheduler = Schedulers.from(internetExecutor)

    override fun runOnBackground(): Scheduler = BACKGROUND_SCHEDULERS

    override fun io(): Scheduler = Schedulers.io()

    override fun compute(): Scheduler = Schedulers.computation()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun internet(): Scheduler = INTERNET_SCHEDULERS

}