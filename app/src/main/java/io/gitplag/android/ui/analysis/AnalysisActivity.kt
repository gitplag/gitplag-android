package io.gitplag.android.ui.analysis

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.DaggerAppCompatActivity
import io.gitplag.android.data.repository.AnalysisRepository
import io.gitplag.android.model.AnalysisPair
import io.gitplag.android.util.OnItemClickListener
import io.gitplag.gitplag.android.databinding.AnalysisBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AnalysisActivity : DaggerAppCompatActivity(), OnItemClickListener<AnalysisPair> {

    @Inject
    lateinit var analysisRepository: AnalysisRepository

    private var disposableAnalysis: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = AnalysisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getLongExtra("id", -1)
        disposableAnalysis = analysisRepository.getAnalysis(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                binding.repositoryName.text = result.repoName
                val analysisPairListView = binding.analyzesPairsList
                analysisPairListView.setHasFixedSize(true)
                analysisPairListView.layoutManager = LinearLayoutManager(this)
                analysisPairListView.adapter = AnalysisPairListAdapter(result.analysisPairs, this)
            }
    }


    override fun onPause() {
        super.onPause()
        disposableAnalysis?.dispose()
    }

    override fun onItemClick(item: AnalysisPair) {
    }
}