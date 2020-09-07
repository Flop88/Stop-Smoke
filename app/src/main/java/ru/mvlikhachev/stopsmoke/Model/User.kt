package ru.mvlikhachev.stopsmoke.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class User(

    var uid : String?,

    var userName : String ?,

    var email : String?,

//    var cigaretteCount : Double?,
//
//    var cigarettePackPrice : Int?,
//
    var stopDay : String?,

) {

}