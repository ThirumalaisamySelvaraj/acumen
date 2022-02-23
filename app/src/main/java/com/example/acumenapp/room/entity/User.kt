package com.example.acumenapp.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User_Table")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "userId")
    val userId : Int,
    @ColumnInfo(name = "userName")
    val userName : String,
    @ColumnInfo(name = "userEmail")
    val userEmail : String
)