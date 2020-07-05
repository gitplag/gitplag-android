package io.gitplag.gitplag.android.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import dagger.android.support.DaggerAppCompatActivity
import io.gitplag.gitplag.android.R
import io.gitplag.gitplag.android.client.GitplagClient
import io.gitplag.gitplag.android.util.adapter.AnalysisListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepositoryActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var gitplagApiService: GitplagClient

    private var disposableRepository: Disposable? = null
    private var disposableAnalyzes: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repository)
        val nameTextView = findViewById<TextView>(R.id.repository__repository_name)
        val languageTextView = findViewById<TextView>(R.id.repository__repository_language)
        val serviceTextView = findViewById<TextView>(R.id.repository__repository_service)
        val analyzesListView = findViewById<ListView>(R.id.repository__analysis_list)
        val id = intent.getLongExtra("id", -1)
        disposableRepository = gitplagApiService.getRepository(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                nameTextView.text = result.name
                languageTextView.text = result.language
                serviceTextView.text = result.gitService
            }
        disposableAnalyzes = gitplagApiService.getRepositoryAnalyzes(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                analyzesListView.adapter = AnalysisListAdapter(this, result)
                analyzesListView.onItemClickListener = AdapterView.OnItemClickListener { _: AdapterView<*>?, v: View?,
                                                                                         _: Int, id: Long ->
                    v?.apply {
                        val intent = Intent()
                        intent.setClass(context, AnalysisActivity::class.java)
                        intent.putExtra("id", id)
                        startActivity(intent)
                    }
                }
            }
    }

    override fun onPause() {
        super.onPause()
        disposableRepository?.dispose()
        disposableAnalyzes?.dispose()
    }
}