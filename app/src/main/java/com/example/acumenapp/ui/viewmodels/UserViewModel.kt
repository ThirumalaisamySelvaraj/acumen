package com.example.acumenapp.ui.viewmodels

import android.service.autofill.UserData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.acumenapp.Resource
import com.example.acumenapp.apputil.GlobalConstants
import com.example.acumenapp.models.UserDataItem
import com.example.acumenapp.repositories.UserRepository
import com.example.acumenapp.room.entity.User
import com.example.acumenapp.ui.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel(){

    val userItems = repository.observeAllUser()

    private val _usersDataItme = MutableLiveData<Event<Resource<List<UserDataItem>>>>()
    val usersDataItme:LiveData<Event<Resource<List<UserDataItem>>>> get() = _usersDataItme

    private val _users = MutableLiveData<Event<List<User>>>()
    val users : LiveData<Event<List<User>>> get() = _users

    private val _insertuserstable = MutableLiveData<Event<User>>()
    val insertuserstable : LiveData<Event<User>> get() = _insertuserstable

    private val _insertuserstableStatus = MutableLiveData<Event<Resource<User>>>()
    val insertuserstableStatus : LiveData<Event<Resource<User>>> get() = _insertuserstableStatus

    fun insertUserItemIntoDB(user: User){
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }

    fun deleteUserItem(user: User){
        viewModelScope.launch {
            repository.deleteUser(user)
        }
    }
    fun insertUsersItem(id:Int,name:String,email:String){
        if(name.isEmpty() || email.isEmpty()){
            _insertuserstableStatus.postValue(Event(Resource.error("The Fields must not be null",null)))
            return
        }
        if(name.length>GlobalConstants.MAX_NAME_LENGTH){
            _insertuserstableStatus.postValue(Event(Resource.error(
                "The name of the item must not exceed ${GlobalConstants.MAX_NAME_LENGTH} characters",null)))
            return
        }
        if(email.length>GlobalConstants.MAX_NAME_LENGTH){
            _insertuserstableStatus.postValue(Event(Resource.error(
                "The email of the item must not exceed ${GlobalConstants.MAX_EMAIL_LENGTH} characters",null)))
            return
        }
        val item = User(id,name,email)
        insertUserItemIntoDB(item)
        _insertuserstableStatus.postValue(Event(Resource.success(item)))
    }

}