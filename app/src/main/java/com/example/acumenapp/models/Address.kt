package com.example.acumenapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("city")
    @Expose
    val city: String,
    @SerializedName("geo")
    @Expose
    val geo: Geo,
    @SerializedName("street")
    @Expose
    val street: String,
    @SerializedName("suite")
    @Expose
    val suite: String,
    @SerializedName("zipcode")
    @Expose
    val zipcode: String
)