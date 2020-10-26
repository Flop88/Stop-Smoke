package ru.mvlikhachev.stopsmoke.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import ru.mvlikhachev.stopsmoke.Model.User
import ru.mvlikhachev.stopsmoke.R

class SettingActivity : AppCompatActivity() {

    private lateinit var username: String
    private lateinit var days: String
    private lateinit var time: String


    private lateinit var globalId : String

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        auth = Firebase.auth
        globalId = auth.currentUser?.uid.toString()

        database = FirebaseDatabase.getInstance().getReference("users").child(globalId)


                    val userListener = object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            // Get User object and use the values to update the UI
                            val user = dataSnapshot.getValue(User::class.java)
                            updateUI(user)
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                            // Getting User failed, log a message
                            Log.w("TAG", "loadUser:onCancelled", databaseError.toException())
                        }
                    }

                    database.addValueEventListener(userListener)
    }

    private fun updateUI(user: User?) {
        TODO("Not yet implemented")
//        renameTextInputLayout.getEditText()?.getText().toString();
//        renameTextInputEditText.setText("123")
    }

    fun onclick(view: View) {}
    fun saveNewData(view: View) {}
}

