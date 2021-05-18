package com.example.resortmanagement.model

data class Booking(
    val userID: String,
    val guestID: String,
    val roomID: String,
    val check_in: String,
    val transaction_date: String,
)
