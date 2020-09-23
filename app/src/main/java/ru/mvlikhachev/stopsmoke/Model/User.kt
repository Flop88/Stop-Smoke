package ru.mvlikhachev.stopsmoke.Model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class User(

    var uid: String? = "",
    var displayName: String = "",
    var email: String? = "",
    var stopDay: String = "",
    var gender: String = "",
    //    var cigaretteCount: String?,

    ) {

}