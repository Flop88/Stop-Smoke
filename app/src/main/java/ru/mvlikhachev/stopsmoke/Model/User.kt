package ru.mvlikhachev.stopsmoke.Model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(

    var uid : String? = "",

    var userName : String = "",

    var email : String? = "",

//    var cigaretteCount : Double?,
//
//    var cigarettePackPrice : Int?,
//
    var stopDay : String= "",

    var gender: String = "",

)