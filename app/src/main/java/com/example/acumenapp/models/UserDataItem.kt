package com.example.acumenapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserDataItem(
    @SerializedName("address")
    @Expose
    val address: Address,
    @SerializedName("company")
    @Expose
    val company: Company,
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("phone")
    @Expose
    val phone: String,
    @SerializedName("username")
    @Expose
    val username: String,
    @SerializedName("website")
    @Expose
    val website: String
)