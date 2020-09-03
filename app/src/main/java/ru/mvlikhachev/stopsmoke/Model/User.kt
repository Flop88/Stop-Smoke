package ru.mvlikhachev.stopsmoke.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val uid : Int,

    @ColumnInfo(name = "first_name")
    val firstName : String?,

    @ColumnInfo(name = "first_name")
    val lastName : String?,

    @ColumnInfo(name = "user_name")
    val userName : String ?,

    @ColumnInfo(name = "email")
    val email : String?,

    @ColumnInfo(name = "cigarette_count")
    val cigaretteCount : Double?,

    @ColumnInfo(name = "cigarette_price")
    val cigarettePackPrice : Int?,

    @ColumnInfo(name = "stop_drink_date")
    val stopDay : String?,

    @ColumnInfo(name = "drink_days")
    val resetDay : ArrayList<String>?
)