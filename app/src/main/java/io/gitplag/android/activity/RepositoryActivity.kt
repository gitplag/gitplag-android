package io.gitplag.android.activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerAppCompatActivity
import io.gitplag.android.data.AnalysisRepository
import io.gitplag.android.data.RepositoryRepository
import io.gitplag.android.model.Analysis
import io.gitplag.android.util.OnItemClickListener
import io.gitplag.android.util.adapter.AnalysisListAdapter
import io.gitplag.gitplag.android.R
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
        setContentView(R.layout.repository)
        val nameTextView = findViewById<TextView>(R.id.repository__repository_name)
        val languageTextView = findViewById<TextView>(R.id.repository__repository_language)
        val serviceTextView = findViewById<TextView>(R.id.repository__repository_service)
        val analyzesListView = findViewById<RecyclerView>(R.id.repository__analysis_list)
        val id = intent.getLongExtra("id", -1)
        disposableRepository = repositoryRepository.getRepository(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                nameTextView.text = result.name
                languageTextView.text = result.language
                serviceTextView.text = result.gitService
            }
        disposableAnalyzes = analysisRepository.getAllAnalyzesOfRepository(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
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