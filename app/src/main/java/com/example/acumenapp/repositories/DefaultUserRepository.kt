package com.example.acumenapp.repositories

import androidx.lifecycle.LiveData
import com.example.acumenapp.Resource
import com.example.acumenapp.models.UserDataItem
import com.example.acumenapp.network.retrofit.apis.UserApi
import com.example.acumenapp.room.dao.UserDao
import com.example.acumenapp.room.entity.User
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class DefaultUserRepository @Inject constructor( private val userDao: UserDao,private val userApi: UserApi) : UserRepository{
    override suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    override fun observeAllUser(): LiveData<List<User>> {
        return userDao.getAllUser()
    }

    override suspend fun getUserApi(): Resource<List<UserDataItem>> {
        return try {
            val response = userApi.getUserList()
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured",null)
            } else {
             Resource.error("An unknown error occured",null)
            }
        }catch (e:Exception) {
            Resource.error("Couldn't reach the server. Check your Internet connection",null)
        }
    }
}