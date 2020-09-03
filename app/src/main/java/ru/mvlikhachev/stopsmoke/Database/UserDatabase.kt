package ru.mvlikhachev.stopsmoke.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mvlikhachev.stopsmoke.Dao.UserDao
import ru.mvlikhachev.stopsmoke.Model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
}