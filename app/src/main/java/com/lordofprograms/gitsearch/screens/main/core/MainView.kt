package com.lordofprograms.gitsearch.screens.main.core

import com.lordofprograms.gitsearch.db.models.Github
import com.lordofprograms.gitsearch.screens.main.MainActivity

/**
 * Created by Михаил on 09.06.2017.
 */
interface MainView{
    fun updateViews(activity: MainActivity, savedUser: Github?)
}