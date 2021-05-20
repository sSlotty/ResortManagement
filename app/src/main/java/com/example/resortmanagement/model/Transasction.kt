package com.example.resortmanagement.model

data class Transasction(
    val _id:String,
    val guestID:String,
    val roomID:String,
    val check_in:String,
    val status:String,
    val check_out:String,
    val total_bill:String
)
