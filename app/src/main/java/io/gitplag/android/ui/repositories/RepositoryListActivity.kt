package io.gitplag.android.ui.repositories

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import io.gitplag.android.data.repository.RepositoryRepository
import io.gitplag.android.model.Repository
import io.gitplag.android.ui.repository.RepositoryActivity
import io.gitplag.android.util.OnItemClickListener
import io.gitplag.gitplag.android.databinding.RepositoryListBinding
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
        val binding = RepositoryListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        disposable = repositoryRepository.getAllRepositories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                val repositoryListView = binding.repositoriesList
                repositoryListView.setHasFixedSize(true)
                repositoryListView.layoutManager = LinearLayoutManager(this)
                repositoryListView.adapter = RepositoryListAdapter(result, this)
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