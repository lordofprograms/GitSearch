package com.lordofprograms.gitsearch.screens.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.lordofprograms.gitsearch.R
import com.lordofprograms.gitsearch.db.models.Github
import com.lordofprograms.gitsearch.screens.history.HistoryActivity
import com.lordofprograms.gitsearch.screens.main.core.MainModel
import com.lordofprograms.gitsearch.screens.main.core.MainPresenter
import com.lordofprograms.gitsearch.screens.main.core.MainView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    lateinit var model: MainModel
    lateinit var presenter: MainPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_main)

      model = MainModel()
      presenter = MainPresenter(this, model, this)

      presenter.onCreate()
  }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.history -> startActivity(Intent(this, HistoryActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun updateViews(activity: MainActivity, savedUser: Github?) {
        Glide.with(activity).load(savedUser?.avatarUrl).into(activity.user_image)
        activity.user_name.text = savedUser?.name
        activity.public_repos.text = "Public Repos: ${savedUser?.publicRepos.toString()}"
    }

     override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

}



