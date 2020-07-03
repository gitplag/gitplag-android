package io.gitplag.gitplag.android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonOne: Button = findViewById(R.id.buttonOne)
        buttonOne.setOnClickListener {
            val activity2Intent = Intent(applicationContext, ListViewExampleActivity::class.java)
            startActivity(activity2Intent)
        }
    }
}
