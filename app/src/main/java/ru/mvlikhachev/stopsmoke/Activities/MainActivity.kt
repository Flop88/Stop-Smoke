package ru.mvlikhachev.stopsmoke.Activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import ru.mvlikhachev.stopsmoke.Model.User
import ru.mvlikhachev.stopsmoke.R


class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }

    // Create menu
    override fun
            onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

            when (item.getItemId()) {
                R.id.sign_out -> {
                    val intent = Intent(this@MainActivity, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    FirebaseAuth.getInstance().signOut()
                    startActivity(intent)
                    finish()

                    true
                }
                R.id.about_program -> {
                    startActivity(Intent(this@MainActivity, AboutActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
        // хз зачем, но без нее не работает
       return super.onOptionsItemSelected(item)
    }
}