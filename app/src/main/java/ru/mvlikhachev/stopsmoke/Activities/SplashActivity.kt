package ru.mvlikhachev.stopsmoke.Activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.mvlikhachev.stopsmoke.Database.UserDatabase
import ru.mvlikhachev.stopsmoke.R


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Hide ActionBar
        supportActionBar?.hide()
//        UserDatabase.get(application)

        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(5000)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    startActivity(
                        Intent(
                            this@SplashActivity,
                            LoginActivity::class.java
                        )
                    )
                }
            }
        }
        thread.start()
    }
}