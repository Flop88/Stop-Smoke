package ru.mvlikhachev.stopsmoke.Dao

import androidx.room.*
import ru.mvlikhachev.stopsmoke.Model.User

@Dao
interface UserDao {

    // CRUD methods
    @Insert
    fun insertAll(vararg users : User)

    @Insert
    fun createUser(user : User)

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM user WHERE uid LIKE :userId LIMIT 1")
    fun getUserById(userId : Int) : User

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user : User)



}