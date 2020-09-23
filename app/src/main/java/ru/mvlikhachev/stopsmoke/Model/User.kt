package ru.mvlikhachev.stopsmoke.Model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class User(

    var uid: String? = "",
    var displayName: String = "",
    var email: String? = "",
//    var cigaretteCount: String?,
//    var cigarettePackPrice: String?,
    var stopDay: String = "",
//    var gender: String = "",

    ) {

}