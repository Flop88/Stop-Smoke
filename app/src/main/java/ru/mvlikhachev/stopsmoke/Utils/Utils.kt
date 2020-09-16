package ru.mvlikhachev.stopsmoke.Utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

// Константа файла сохранения настроек
const val APP_PREFERENCES = "datasetting"
const val APP_PREFERENCES_KEY_USERID = "userIdFromDb"


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

// Проверка подключения к интернету
fun hasConnection(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    var wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
    if (wifiInfo != null && wifiInfo.isConnected) {
        return true
    }
    wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
    if (wifiInfo != null && wifiInfo.isConnected) {
        return true
    }
    wifiInfo = cm.activeNetworkInfo
    return wifiInfo != null && wifiInfo.isConnected
}

// Получить текущую дату
fun getCurrentDate(): String? {
    val dateFormat: DateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
    val date = Date()
    return dateFormat.format(date)
}

// Получаем user id из Firebase и присваиваем его в userId и помещаем в APP_PREFERENCES_KEY_USERID
fun getUserId(context: Context): String? {
    val result = arrayOf<String?>("")
    val sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val auth = FirebaseAuth.getInstance()
    val database = FirebaseDatabase.getInstance()
    val userDatabaseReference = database.reference.child("users")
    return if (hasConnection(context)) {
        userDatabaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (childSnapshot in dataSnapshot.children) {
                        val key = childSnapshot.key
                        val email = childSnapshot.child("email").getValue(
                            String::class.java
                        )
                        Log.d("firebaseid", "First IF: " +  key.toString())
                        if (email == auth.currentUser!!.email) {
                            result[0] = key
                            Log.d("firebaseid", "Second IF: " +  result[0])
                            // Save "userId" on local storage
                            if (result[0]!!.length == 20) {
                                Log.d("firebaseid", "FINISH IF: " +  result[0])
                                editor.putString(APP_PREFERENCES_KEY_USERID, result[0])
                                editor.apply()
                            }
                        }
                    }
                }
            }



            override fun onCancelled(databaseError: DatabaseError) {}
        })
        result[0] = sharedPreferences.getString(APP_PREFERENCES_KEY_USERID, "qwerty")
        result[0]
    } else {
        sharedPreferences.getString(APP_PREFERENCES_KEY_USERID, "qwerty")
    }
}
