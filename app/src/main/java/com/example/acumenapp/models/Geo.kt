package com.example.acumenapp.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Geo(
    @SerializedName("lat")
    @Expose
    val lat: String,
    @SerializedName("lng")
    @Expose
    val lng: String
)