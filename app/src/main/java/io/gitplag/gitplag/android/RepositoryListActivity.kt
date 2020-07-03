package io.gitplag.gitplag.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryListActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var gitplagApiService: GitplagClient

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
        listView.onItemClickListener = AdapterView.OnItemClickListener { _: AdapterView<*>?, v: View?,
                                                                         _: Int, id: Long ->
            v?.apply {
                val intent = Intent()
                intent.setClass(context, RepositoryActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

}