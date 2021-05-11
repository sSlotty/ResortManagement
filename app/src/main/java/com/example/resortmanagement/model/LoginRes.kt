package com.example.resortmanagement.model

data class LoginRes(
    val access_token: String,
    val expires_in: Int,
    val refresh_token: String,
    val token_type: String
)