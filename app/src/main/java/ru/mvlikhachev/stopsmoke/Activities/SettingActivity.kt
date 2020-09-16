package ru.mvlikhachev.stopsmoke.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.mvlikhachev.stopsmoke.R

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
    }

    fun onclick(view: View) {}
    fun saveNewData(view: View) {}
}