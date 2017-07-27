@file:JvmName("UiUtils")
package com.lordofprograms.gitsearch.utils

import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Михаил on 10.06.2017.
 */
fun snackBar(view: View, msg: CharSequence, duration: Int = Snackbar.LENGTH_LONG){
     Snackbar.make(view , msg , duration).show()
}
fun ViewGroup.inflate(layoutId: Int): View {
     return LayoutInflater.from(context).inflate(layoutId, this, false)
}