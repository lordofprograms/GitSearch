package com.lordofprograms.gitsearch.screens.history

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.lordofprograms.gitsearch.R
import com.lordofprograms.gitsearch.screens.history.core.HistoryModel
import com.lordofprograms.gitsearch.screens.history.core.HistoryPresenter
import com.lordofprograms.gitsearch.screens.history.core.HistoryView
import kotlinx.android.synthetic.main.activity_history.*

/**
 * Created by Михаил on 17.07.2017.
 */

class HistoryActivity : AppCompatActivity(), HistoryView {

    val presenter = HistoryPresenter(this, HistoryModel(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        supportActionBar?.title = getString(R.string.history)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter.onCreate()
    }

    override fun showEmptyText() {
        emptyText.visibility = View.VISIBLE
        rv.visibility = View.GONE
    }

    override fun hideEmptyText() {
        emptyText.visibility = View.GONE
        rv.visibility = View.VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> super.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}