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
import java.text.ParseException
import java.text.SimpleDateFormat


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
//        database = Firebase.database.reference
        database = FirebaseDatabase.getInstance().getReference("users").child(globalId)
        auth = Firebase.auth


        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val post = dataSnapshot.getValue(User::class.java)
                Log.d("firebaseData", "Username " + post?.userName)
                Log.d("firebaseData", "Date " + post?.stopDay)
                Log.d("firebaseData", "time " + post?.userName)

                updateUI(post)
                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        database.addValueEventListener(postListener)



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

    fun calculateTimeWithoutSmoke(date: String?): Array<String>? {
        val result = arrayOf("10", "00", "00")
        val format = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        var timeUp: Long = 0
        try {
            timeUp = format.parse(date).getTime()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val diff = System.currentTimeMillis() - timeUp
        val diffMinutes = diff / (60 * 1000) % 60
        val diffHours = diff / (60 * 60 * 1000) % 24
        val diffDays = diff / (24 * 60 * 60 * 1000)

        // Проверка если минуты и секунды меньше 10 - выполняем форматирование, чтоб красиво отображалось во вью
        var hoursString = ""
        var minutesString = ""
        val daysString = diffDays.toString()
        hoursString = if (diffHours < 10) {
            "0$diffHours"
        } else {
            diffHours.toString()
        }
        minutesString = if (diffMinutes < 10) {
            "0$diffMinutes"
        } else {
            diffMinutes.toString()
        }
        result[0] = daysString
        result[1] = hoursString
        result[2] = minutesString
        return result
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