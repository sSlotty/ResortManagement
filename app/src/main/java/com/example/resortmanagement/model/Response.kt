package com.example.resortmanagement.model

import com.google.gson.JsonArray
import com.google.gson.JsonObject

data class Response(
    val `data`: JsonArray, //JsonArray
    val message: String,
    val status: Int
)
