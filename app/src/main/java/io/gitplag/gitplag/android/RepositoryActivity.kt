package io.gitplag.gitplag.android

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class RepositoryActivity : Activity() {

    private val gitplagApiService by lazy {
        GitplagClient.create()
    }
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repository)
        val nameTextView = findViewById<TextView>(R.id.repository__repository_name)
        val languageTextView = findViewById<TextView>(R.id.repository__repository_language)
        val serviceTextView = findViewById<TextView>(R.id.repository__repository_service)
        val id = intent.getLongExtra("id", -1)
        disposable = gitplagApiService.getRepository(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                nameTextView.text = result.name
                languageTextView.text = result.language
                serviceTextView.text = result.gitService
            }
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}