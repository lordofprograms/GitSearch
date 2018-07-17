package com.lordofprograms.gitsearch.model.system

import rx.Scheduler

interface SchedulersProvider {

    fun runOnBackground(): Scheduler
    fun io(): Scheduler
    fun compute(): Scheduler
    fun ui(): Scheduler
    fun internet(): Scheduler

}