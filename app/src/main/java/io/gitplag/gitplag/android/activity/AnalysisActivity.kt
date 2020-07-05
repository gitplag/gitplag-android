package io.gitplag.gitplag.android.activity

import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import dagger.android.support.DaggerAppCompatActivity
import io.gitplag.gitplag.android.R
import io.gitplag.gitplag.android.client.GitplagClient
import io.gitplag.gitplag.android.util.adapter.AnalysisPairListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AnalysisActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var gitplagApiService: GitplagClient

    private var disposableAnalysis: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.analysis)
        val nameTextView = findViewById<TextView>(R.id.analysis__repository_name)
        val analysisPairsListView = findViewById<ListView>(R.id.analysis__pair_list)
        val id = intent.getLongExtra("id", -1)
        disposableAnalysis = gitplagApiService.getAnalysis(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                nameTextView.text = result.repoName
                analysisPairsListView.adapter = AnalysisPairListAdapter(this, result.analysisPairs)
            }
    }

    override fun onPause() {
        super.onPause()
        disposableAnalysis?.dispose()
    }
}