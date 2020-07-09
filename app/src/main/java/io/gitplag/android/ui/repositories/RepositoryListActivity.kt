package io.gitplag.android.ui.repositories

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import io.gitplag.android.model.Repository
import io.gitplag.android.ui.base.BaseActivity
import io.gitplag.android.ui.repository.RepositoryActivity
import io.gitplag.android.util.data.Status
import io.gitplag.android.util.viewmodel.viewModelOf
import io.gitplag.gitplag.android.databinding.RepositoryListBinding

class RepositoryListActivity : BaseActivity<RepositoryListViewModel, RepositoryListBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.getRepositories()
            .observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.LOADING -> {
                        }
                        Status.SUCCESS -> {
                            val repositoryListView = binding.repositoriesList
                            repositoryListView.setHasFixedSize(true)
                            repositoryListView.layoutManager = LinearLayoutManager(this)
                            repositoryListView.adapter =
                                resource.data?.let { list -> RepositoryListAdapter(list, onItemClick) }
                        }
                        Status.ERROR -> {
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
    }

    private val onItemClick: (i: Repository) -> Unit = {
        val intent = Intent()
        intent.setClass(this, RepositoryActivity::class.java)
        intent.putExtra("id", it.id)
        startActivity(intent)
    }

    override fun initViewBinding(): RepositoryListBinding = RepositoryListBinding.inflate(layoutInflater)

    override fun initViewModel(): RepositoryListViewModel = viewModelOf(viewModelProvider)

}