package ru.mvlikhachev.stopsmoke.Activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import ru.mvlikhachev.stopsmoke.Database.UserDatabase
import ru.mvlikhachev.stopsmoke.Model.User
import ru.mvlikhachev.stopsmoke.R


class MainActivity : AppCompatActivity() {

    private val TAG = "TAG"

    private lateinit var auth: FirebaseAuth

    private lateinit var userDatabase : UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var user : User? = null
        user?.userName = "Maxim"
        user?.uid = "wdawdawdawda"


//        userDatabase = Room.databaseBuilder()
        val db = Room.databaseBuilder(applicationContext, UserDatabase::class.java, "usersDb").build()

        var currentUser = user?.let { db.userDao().createUser(it) }



//        var newUser = db.userDao().getUserById("wdawdawdawda")
//        var currentUserName = newUser?.userName

//        Log.d("getName", "Name $currentUserName")

//        // Initialize Firebase Auth
//        auth = Firebase.auth
//
////        supportActionBar?.hide()
//        Log.d("getUser", "User ID " + auth.currentUser!!.uid)



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