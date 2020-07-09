package io.gitplag.android.ui.repository

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import io.gitplag.android.model.Analysis
import io.gitplag.android.ui.analysis.AnalysisActivity
import io.gitplag.android.ui.base.BaseActivity
import io.gitplag.android.util.data.Status
import io.gitplag.android.util.viewmodel.viewModelOf
import io.gitplag.gitplag.android.databinding.RepositoryBinding

class RepositoryActivity : BaseActivity<RepositoryViewModel, RepositoryBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val id = intent.getLongExtra("id", -1)
        viewModel.getRepository(id)
            .observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.LOADING -> {
                        }
                        Status.SUCCESS -> {
                            binding.repositoryName.text = resource.data?.name
                            binding.repositoryLanguage.text = resource.data?.language
                            binding.repositoryService.text = resource.data?.gitService
                        }
                        Status.ERROR -> {
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
        viewModel.getAllAnalyzesOfRepository(id)
            .observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.LOADING -> {
                        }
                        Status.SUCCESS -> {
                            val analyzesListView = binding.repositoryAnalyzesList
                            analyzesListView.setHasFixedSize(true)
                            analyzesListView.layoutManager = LinearLayoutManager(this)
                            analyzesListView.adapter =
                                AnalysisListAdapter(resource.data ?: emptyList(), onItemClick)
                        }
                        Status.ERROR -> {
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
    }

    private val onItemClick: (i: Analysis) -> Unit = {
        val intent = Intent()
        intent.setClass(this, AnalysisActivity::class.java)
        intent.putExtra("id", it.id)
        startActivity(intent)
    }

    override fun initViewBinding(): RepositoryBinding = RepositoryBinding.inflate(layoutInflater)

    override fun initViewModel(): RepositoryViewModel = viewModelOf(viewModelProvider)
}