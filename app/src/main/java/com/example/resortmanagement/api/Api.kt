package com.example.resortmanagement.api

import com.example.resortmanagement.model.Post
import retrofit2.http.GET

interface Api {
    @GET("/user")
    suspend fun getStaff():Post
}