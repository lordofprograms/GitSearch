package com.lordofprograms.gitsearch.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import android.view.inputmethod.EditorInfo
import com.bumptech.glide.Glide
import com.lordofprograms.gitsearch.R
import com.lordofprograms.gitsearch.presentation.main.MainPresenter
import com.lordofprograms.gitsearch.presentation.main.MainView
import com.lordofprograms.gitsearch.ui.history.HistoryFragment
import com.lordofprograms.gitsearch.utils.Injections
import com.lordofprograms.gitsearch.utils.inflate
import com.lordofprograms.gitsearch.utils.snackBar
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment: Fragment(), MainView {

    private var presenter: MainPresenter? = null

    private var imageUrl: String? = ""

    private val AVATAR_URL = "avatar_url"
    private val USER_NAME = "user_name"
    private val PUBLIC_REPOS = "pubic_repos"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        setHasOptionsMenu(true)

        if(presenter == null){
            presenter = MainPresenter(Injections.getMainInteractor(), this)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.main_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        with(mainToolbar as Toolbar) {
            title = getString(R.string.search)
            with(activity as AppCompatActivity) {
                setSupportActionBar(mainToolbar as Toolbar)
            }
        }
        setListener()
        setInfo(savedInstanceState)
    }

    override fun showGithubUser(avatarUrl: String?, name: String?, publicRepos: String?) {
        imageUrl = avatarUrl
        Glide.with(activity).load(avatarUrl).into(userImage)
        userNameText.text = name
        publicReposText.text = publicRepos
    }

    override fun showSnackbar(text: String){
        snackBar(mainFragmentContainer, text)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (isVisible) {
            outState?.putString(AVATAR_URL, imageUrl)
            outState?.putString(USER_NAME, userNameText.text.toString())
            outState?.putString(PUBLIC_REPOS, publicReposText.text.toString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.history){
           activity.supportFragmentManager.beginTransaction()
                   .replace(R.id.container, HistoryFragment())
                   .addToBackStack(null)
                   .commit()
        }
        return true
    }

    private fun setListener(){
        searchAccount.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if(searchAccount.text.toString().trim().isNotEmpty()) {
                    presenter?.searchByName(searchAccount.text.toString())
                }
            }

            return@setOnEditorActionListener true
        }
    }

    private fun setInfo(savedInstanceState: Bundle?){
        if(savedInstanceState != null){
            showGithubUser(savedInstanceState.getString(AVATAR_URL), savedInstanceState.getString(USER_NAME),
                    savedInstanceState.getString(PUBLIC_REPOS))
        }
    }

}
