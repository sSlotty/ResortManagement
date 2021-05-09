package com.example.resortmanagement.repository

import com.example.resortmanagement.api.RetrofitInstance
import com.example.resortmanagement.model.Post
import retrofit2.Response

class Repository {
    suspend fun getStaff():Response<Post>{
        return RetrofitInstance.api.getStaff()
    }
}