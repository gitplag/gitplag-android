package io.gitplag.android.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerAppCompatActivity
import io.gitplag.android.data.RepositoryRepository
import io.gitplag.android.model.Repository
import io.gitplag.android.util.OnItemClickListener
import io.gitplag.android.util.adapter.RepositoryListAdapter
import io.gitplag.gitplag.android.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryListActivity : DaggerAppCompatActivity(), OnItemClickListener<Repository> {

    @Inject
    lateinit var repositoryRepository: RepositoryRepository

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repository_list)
        val listView = findViewById<RecyclerView>(R.id.repositoriesListRecyclerView)
        disposable = repositoryRepository.getAllRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                listView.setHasFixedSize(true)
                listView.layoutManager = LinearLayoutManager(this)
                listView.adapter = RepositoryListAdapter(result, this)
            }
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }

    override fun onItemClick(item: Repository) {
        val intent = Intent()
        intent.setClass(this, RepositoryActivity::class.java)
        intent.putExtra("id", item.id)
        startActivity(intent)
    }

}