package ru.mvlikhachev.stopsmoke.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id : Int,

    @ColumnInfo(name = "uid")
    var uid : String,

    @ColumnInfo(name = "firstname")
    var firstName : String?,

    @ColumnInfo(name = "lastname")
    var lastName : String?,

    @ColumnInfo(name = "username")
    var userName : String ?,

    @ColumnInfo(name = "email")
    var email : String?,

    @ColumnInfo(name = "cigarette_count")
    var cigaretteCount : Double?,

    @ColumnInfo(name = "cigarette_price")
    var cigarettePackPrice : Int?,

    @ColumnInfo(name = "stop_drink_date")
    var stopDay : String?,

)