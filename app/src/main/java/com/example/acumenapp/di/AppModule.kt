package com.example.acumenapp.di

import android.content.Context
import com.example.acumenapp.network.retrofit.apis.UserApi
import com.example.acumenapp.network.retrofit.client.RetrofitClient
import com.example.acumenapp.repositories.DefaultUserRepository
import com.example.acumenapp.repositories.UserRepository
import com.example.acumenapp.room.dao.UserDao
import com.example.acumenapp.room.database.UserDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideUserDataBase(@ApplicationContext context : Context) : UserDataBase{
        return UserDataBase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideUserDao(userDataBase: UserDataBase):UserDao {
        return userDataBase.userDao
    }

    @Singleton
    @Provides
    fun provideUserApi():UserApi{
        return RetrofitClient.serviceBuilder(UserApi::class.java)
    }

 /*   @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao,userApi: UserApi) : UserRepository {
        return DefaultUserRepository(userDao,userApi) as UserRepository
    }*/

    @Singleton
    @Provides
    fun provideUserRepository(userDao: UserDao,userApi: UserApi) = DefaultUserRepository(userDao,userApi) as UserRepository


}