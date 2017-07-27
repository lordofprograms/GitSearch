package com.lordofprograms.gitsearch.screens.history.adapter

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.lordofprograms.gitsearch.R
import com.lordofprograms.gitsearch.db.models.Github
import com.lordofprograms.gitsearch.utils.inflate

/**
 * Created by Михаил on 17.07.2017.
 */
class HistoryAdapter(val context: AppCompatActivity) : RecyclerView.Adapter<HistoryAdapter.HistoryItemViewHolder>(){

    private var historyList: List<Github> = ArrayList()

    fun setList(list: ArrayList<Github>) {
        this.historyList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
        val view = parent.inflate(R.layout.history_item)

        return HistoryItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        holder.name.text = historyList[position].name
        holder.repos.text = "Public Repos: ${historyList[position].publicRepos}"
        Glide.with(context).load(historyList[position].avatarUrl).into(holder.img)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    inner class HistoryItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         val img = view.findViewById(R.id.accountImg) as ImageView
         val name = view.findViewById(R.id.accountName) as TextView
         val repos = view.findViewById(R.id.accountRepos) as TextView
    }

}