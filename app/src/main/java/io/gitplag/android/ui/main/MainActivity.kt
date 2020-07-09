package io.gitplag.android.ui.main

import android.content.Intent
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import io.gitplag.android.ui.repositories.RepositoryListActivity
import io.gitplag.gitplag.android.databinding.ActivityMainBinding

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            val activity2Intent = Intent(applicationContext, RepositoryListActivity::class.java)
            startActivity(activity2Intent)
        }
    }
}
