package io.gitplag.android.ui.repository

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import io.gitplag.android.data.repository.AnalysisRepository
import io.gitplag.android.data.repository.RepositoryRepository
import io.gitplag.android.model.Analysis
import io.gitplag.android.ui.analysis.AnalysisActivity
import io.gitplag.android.util.OnItemClickListener
import io.gitplag.gitplag.android.databinding.RepositoryBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryActivity : DaggerAppCompatActivity(), OnItemClickListener<Analysis> {

    @Inject
    lateinit var repositoryRepository: RepositoryRepository

    @Inject
    lateinit var analysisRepository: AnalysisRepository

    private var disposableRepository: Disposable? = null
    private var disposableAnalyzes: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = RepositoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getLongExtra("id", -1)
        disposableRepository = repositoryRepository.getRepository(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                binding.repositoryRepositoryName.text = result.name
                binding.repositoryRepositoryLanguage.text = result.language
                binding.repositoryRepositoryService.text = result.gitService
            }
        disposableAnalyzes = analysisRepository.getAllAnalyzesOfRepository(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                val analyzesListView = binding.repositoryAnalysisList
                analyzesListView.setHasFixedSize(true)
                analyzesListView.layoutManager = LinearLayoutManager(this)
                analyzesListView.adapter = AnalysisListAdapter(result, this)
            }
    }

    override fun onPause() {
        super.onPause()
        disposableRepository?.dispose()
        disposableAnalyzes?.dispose()
    }

    override fun onItemClick(item: Analysis) {
        val intent = Intent()
        intent.setClass(this, AnalysisActivity::class.java)
        intent.putExtra("id", item.id)
        startActivity(intent)
    }
}