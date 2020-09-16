package ru.mvlikhachev.stopsmoke.Activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import ru.mvlikhachev.stopsmoke.Model.User
import ru.mvlikhachev.stopsmoke.R
import ru.mvlikhachev.stopsmoke.Utils.calculateTimeWithoutSmoke
import ru.mvlikhachev.stopsmoke.Utils.getUserId


class MainActivity : AppCompatActivity() {


    private lateinit var username: String
    private lateinit var days: String
    private lateinit var time: String

    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val globalId: String = "-MHI9dYi7K7bgRlVmEjH"

//        val globalId: String = getUserId(this).toString()

//        var pushedRef = firebase.database().ref('/customers').push({ email: email });
//        console.log(pushedRef.key);

        Log.d("currentuserid", globalId)
//        Log.d("currentuserid", globalIdd)


//        database = Firebase.database.reference


        database = FirebaseDatabase.getInstance().getReference("users").child(globalId)
        auth = Firebase.auth

        val updateDateThread = Thread {
            while (true) {
                try {
                    val postListener = object : ValueEventListener {
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
                    database.addValueEventListener(postListener)
                    Thread.sleep(60000) //1000 - 1 сек
                } catch (ex: InterruptedException) {
                    ex.printStackTrace()
                }
            }
        }
        updateDateThread.start()

        }

    private fun updateUI(user: User?) {
        nameTextView.setText("Здраствуйте, ${user?.userName}")
        var days : String = "14"
        var hours : String = "23"
        var minutes : String = "32"

        var date : Array<String>? = calculateTimeWithoutSmoke(user?.stopDay)

        if (date != null) {
            days = date[0]
            hours = date[1]
            minutes = date[2]

        }
        daysTextView.setText("$days дней")
        timeTextView.setText("$hours:$minutes")
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