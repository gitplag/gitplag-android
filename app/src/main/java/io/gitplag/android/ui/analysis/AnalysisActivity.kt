package io.gitplag.android.ui.analysis

import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.support.DaggerAppCompatActivity
import io.gitplag.android.data.repository.AnalysisRepository
import io.gitplag.gitplag.android.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AnalysisActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var analysisRepository: AnalysisRepository

    private var disposableAnalysis: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.analysis)
        val nameTextView = findViewById<TextView>(R.id.analysis__repository_name)
        val analysisPairsListView = findViewById<RecyclerView>(R.id.analysis__pair_list)
        val id = intent.getLongExtra("id", -1)
        disposableAnalysis = analysisRepository.getAnalysis(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                nameTextView.text = result.repoName
                analysisPairsListView.setHasFixedSize(true)
                analysisPairsListView.layoutManager = LinearLayoutManager(this)
                analysisPairsListView.adapter =
                    AnalysisPairListAdapter(result.analysisPairs)
            }
    }

    override fun onPause() {
        super.onPause()
        disposableAnalysis?.dispose()
    }
}