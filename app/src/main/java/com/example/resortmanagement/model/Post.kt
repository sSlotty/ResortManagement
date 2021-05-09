package com.example.resortmanagement.model

import com.google.gson.annotations.SerializedName

data class Post(
    val _id : String,
    val name : String,
    val tel: String,
    val salary: Int,
    val job_position : String
)