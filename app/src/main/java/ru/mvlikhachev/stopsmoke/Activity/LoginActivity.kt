package ru.mvlikhachev.stopsmoke.Activity


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.mvlikhachev.stopsmoke.R


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()
    }
}