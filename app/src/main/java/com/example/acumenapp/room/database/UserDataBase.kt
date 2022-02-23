package com.example.acumenapp.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.acumenapp.room.dao.UserDao
import com.example.acumenapp.room.entity.User

@Database(entities = [User::class], version = 1)
abstract class UserDataBase : RoomDatabase(){
    abstract val userDao:UserDao

    companion object{
        @Volatile
        private var INSTANCE:UserDataBase? = null

        fun getInstance(context: Context):UserDataBase{
            synchronized(this){
                var instance : UserDataBase? = INSTANCE
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDataBase::class.java,
                    "User_DataBase"
                ).build()
                return instance
            }
        }

    }

}