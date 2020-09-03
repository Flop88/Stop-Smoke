package ru.mvlikhachev.stopsmoke.Activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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



        var newUser = db.userDao().getUserById("wdawdawdawda")
        var currentUserName = newUser?.userName

        Log.d("getName", "Name $currentUserName")

//        // Initialize Firebase Auth
//        auth = Firebase.auth
//
////        supportActionBar?.hide()
//        Log.d("getUser", "User ID " + auth.currentUser!!.uid)



    }
}