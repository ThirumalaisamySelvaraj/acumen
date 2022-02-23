package com.example.acumenapp.network.retrofit.apis

import com.example.acumenapp.models.UserDataItem
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {

    @GET
    suspend fun getUserList(): Response<List<UserDataItem>>
}