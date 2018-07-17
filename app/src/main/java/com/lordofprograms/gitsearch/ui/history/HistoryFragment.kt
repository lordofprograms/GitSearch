package com.lordofprograms.gitsearch.ui.history

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lordofprograms.gitsearch.R
import com.lordofprograms.gitsearch.entity.GithubUser
import com.lordofprograms.gitsearch.presentation.history.HistoryPresenter
import com.lordofprograms.gitsearch.presentation.history.HistoryView
import com.lordofprograms.gitsearch.ui.history.adapter.HistoryAdapter
import com.lordofprograms.gitsearch.utils.Injections
import com.lordofprograms.gitsearch.utils.inflate
import kotlinx.android.synthetic.main.history_fragment.*

class HistoryFragment : Fragment(), HistoryView {

    private var presenter: HistoryPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

        if (presenter == null) {
            presenter = HistoryPresenter(Injections.getHistoryInteractor(), this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.history_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(historyToolbar as Toolbar) {
            title = getString(R.string.history)
            with(activity as AppCompatActivity) {
                setSupportActionBar(historyToolbar as Toolbar)
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
            setNavigationOnClickListener { activity.supportFragmentManager.popBackStack() }
        }

        presenter?.loadRecyclerView()
    }

    override fun setList(usersList: List<GithubUser>) {
        rv.layoutManager = LinearLayoutManager(activity)
        rv.setHasFixedSize(true)
        val adapter = HistoryAdapter()
        adapter.setList(usersList)
        rv.adapter = adapter
    }


    override fun showEmpty(usersList: List<GithubUser>) = when (usersList.isEmpty()) {
        true -> {
            emptyText.visibility = View.VISIBLE
            rv.visibility = View.GONE
        }
        false -> {
            emptyText.visibility = View.GONE
            rv.visibility = View.VISIBLE
        }
    }

}