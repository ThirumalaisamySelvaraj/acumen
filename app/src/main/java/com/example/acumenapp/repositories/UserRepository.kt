package com.example.acumenapp.repositories

import androidx.lifecycle.LiveData
import com.example.acumenapp.Resource
import com.example.acumenapp.models.UserDataItem
import com.example.acumenapp.room.entity.User
import retrofit2.Response

interface UserRepository {

    suspend fun insertUser(user:User)

    suspend fun deleteUser(user: User)

    fun observeAllUser() : LiveData<List<User>>

    suspend fun getUserApi(): Resource<List<UserDataItem>>
}