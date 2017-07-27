package com.lordofprograms.gitsearch.screens.history.core

import com.lordofprograms.gitsearch.db.DBService

/**
 * Created by Михаил on 23.07.2017.
 */
class HistoryModel {

    fun buildDBService(): DBService {
        return DBService()
    }

}