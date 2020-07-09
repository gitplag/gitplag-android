package io.gitplag.android.ui.analysis

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import io.gitplag.android.ui.base.BaseActivity
import io.gitplag.android.util.data.Status
import io.gitplag.android.util.viewmodel.viewModelOf
import io.gitplag.gitplag.android.databinding.AnalysisBinding

class AnalysisActivity : BaseActivity<AnalysisViewModel, AnalysisBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val id = intent.getLongExtra("id", -1)
        viewModel.getAnalysis(id)
            .observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.LOADING -> {
                        }
                        Status.SUCCESS -> {
                            binding.repositoryName.text = resource.data?.repoName
                            val analysisPairListView = binding.analyzesPairsList
                            analysisPairListView.setHasFixedSize(true)
                            analysisPairListView.layoutManager = LinearLayoutManager(this)
                            analysisPairListView.adapter = resource.data?.analysisPairs?.let { it1 ->
                                AnalysisPairListAdapter(it1)
                            }
                        }
                        Status.ERROR -> {
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
    }

    override fun initViewBinding(): AnalysisBinding = AnalysisBinding.inflate(layoutInflater)

    override fun initViewModel(): AnalysisViewModel = viewModelOf(viewModelProvider)
}