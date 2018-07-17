package com.lordofprograms.gitsearch.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.lordofprograms.gitsearch.R
import io.realm.Realm

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      Realm.init(applicationContext)
      setContentView(R.layout.activity_main)

      if(savedInstanceState == null){
          supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment()).commit()
      }
  }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount == 0){
            super.onBackPressed()
        } else supportFragmentManager.popBackStack()
    }

}