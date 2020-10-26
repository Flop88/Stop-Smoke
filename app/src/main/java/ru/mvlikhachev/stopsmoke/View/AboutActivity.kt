package ru.mvlikhachev.stopsmoke.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_about.*
import ru.mvlikhachev.stopsmoke.R


class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar!!.hide()

        vkImageView.setOnClickListener { view ->
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/mv.likhachev"))
            startActivity(browserIntent)
        }

        instagramImageView.setOnClickListener { view ->
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/mv.likhachev"))
            startActivity(browserIntent)
        }
        telegramImageView.setOnClickListener { view ->
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/mlikhachev"))
            startActivity(browserIntent)
        }

    }


}