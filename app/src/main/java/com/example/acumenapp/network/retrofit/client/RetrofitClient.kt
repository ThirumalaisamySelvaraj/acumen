package com.example.acumenapp.network.retrofit.client

import android.content.Context
import android.os.Build
import com.example.acumenapp.apputil.GlobalConstants
import com.example.acumenapp.network.retrofit.apis.UserApi
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class RetrofitClient {
    /*
    * 1.Create Http Logging Interceptor
    * 2.Create a custom interceptor to apply headers application wide
    * 3.crete ok http client
    * 4.create gson
    * 5.create retrofit builder
    * 6.create retrofit instance
    * 7.create function that returns retrofit
    * 8.Add dispatchers
    * */

    companion object{
        val httpLoggingInterceptor : HttpLoggingInterceptor by lazy {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        val httpLoggingHeaderInterceptor : Interceptor by lazy {
            object :Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    var request = chain.request()
                    request.newBuilder()
                        .addHeader("x-device", Build.DEVICE)
                        .addHeader("x-accept-language",Locale.getDefault().language)
                        .build()
                    val response = chain.proceed(request)
                    return response
                }
            }
        }
        val okHttpClient : OkHttpClient.Builder by lazy {
            OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingHeaderInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(20,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
        }
        val gson by lazy {
            GsonBuilder().create()
        }
        val retrofitBuilder : Retrofit.Builder by lazy {
            Retrofit.Builder()
                .baseUrl(GlobalConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient.build())
        }
        val retrofitInstance : Retrofit by lazy {
            retrofitBuilder.build()
        }

        fun <T> serviceBuilder(serviceType : Class<T>) : T {
            return retrofitInstance.create(serviceType)
        }

        fun getInstance() : UserApi{
            return retrofitInstance.create(UserApi::class.java)
        }
    }
}