@file:JvmName("UiUtils")
package com.lordofprograms.gitsearch.utils

import android.app.Activity
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun snackBar(view: View, msg: CharSequence, duration: Int = Snackbar.LENGTH_LONG){
     Snackbar.make(view , msg , duration).show()
}
fun ViewGroup.inflate(layoutId: Int): View {
     return LayoutInflater.from(context).inflate(layoutId, this, false)
}

fun setToolbar(activity: Activity, toolbar: View, toolbarTitle: String, showBackArrow: Boolean = false){
     with(toolbar as Toolbar) {
          title = toolbarTitle
          with(activity as AppCompatActivity) {
               setSupportActionBar(toolbar as Toolbar)
               if(showBackArrow)supportActionBar?.setDisplayHomeAsUpEnabled(true)
          }
          if (showBackArrow)setNavigationOnClickListener { activity.supportFragmentManager.popBackStack() }
     }
}