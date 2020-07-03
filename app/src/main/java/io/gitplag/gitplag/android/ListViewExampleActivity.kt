package io.gitplag.gitplag.android

import android.app.Activity
import android.os.Bundle
import android.widget.ListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ListViewExampleActivity : Activity() {

    private val gitplagApiService by lazy {
        GitplagClient.create()
    }
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repository_list)
        val listView = findViewById<ListView>(R.id.repository_list)
        disposable = gitplagApiService.getRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                listView.adapter = RepositoryListAdapter(this, result)
            }
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}