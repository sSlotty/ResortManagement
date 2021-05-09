package com.example.resortmanagement.repository

import com.example.resortmanagement.api.RetrofitInstance
import com.example.resortmanagement.model.Post

class Repository {
    suspend fun getStaff():Post{
        return RetrofitInstance.api.getStaff()
    }
}