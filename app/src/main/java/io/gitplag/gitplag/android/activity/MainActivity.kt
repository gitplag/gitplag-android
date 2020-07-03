package io.gitplag.gitplag.android.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import dagger.android.support.DaggerAppCompatActivity
import io.gitplag.gitplag.android.R

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonOne: Button = findViewById(R.id.buttonOne)
        buttonOne.setOnClickListener {
            val activity2Intent = Intent(applicationContext, RepositoryListActivity::class.java)
            startActivity(activity2Intent)
        }
    }
}
