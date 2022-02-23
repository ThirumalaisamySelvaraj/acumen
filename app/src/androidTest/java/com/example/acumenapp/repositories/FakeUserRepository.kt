package com.example.acumenapp.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.acumenapp.Resource
import com.example.acumenapp.models.UserDataItem
import com.example.acumenapp.room.entity.User


class FakeUserRepository : UserRepository{

    private val usersListItems = mutableListOf<User>()

    private val observableUserItems = MutableLiveData<List<User>>(usersListItems)

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value :Boolean){
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData(){
        observableUserItems.postValue(usersListItems)
    }

    override suspend fun insertUser(user: User) {
        usersListItems.add(user)
        refreshLiveData()
    }

    override suspend fun deleteUser(user: User) {
        usersListItems.remove(user)
        refreshLiveData()
    }

    override fun observeAllUser(): LiveData<List<User>> {
        return observableUserItems
    }

    override suspend fun getUserApi(): Resource<List<UserDataItem>> {
        return if(shouldReturnNetworkError){
            Resource.error("Error",null)
        }else{
            Resource.success(listOf())
        }
    }

}