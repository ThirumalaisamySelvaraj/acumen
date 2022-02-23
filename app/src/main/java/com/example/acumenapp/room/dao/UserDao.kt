package com.example.acumenapp.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.acumenapp.room.entity.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Update(onConflict = OnConflictStrategy.ABORT)
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("Delete from User_Table")
    suspend fun deleteAllUser()

    @Query("Select * from User_Table")
    fun getAllUser():LiveData<List<User>>

}