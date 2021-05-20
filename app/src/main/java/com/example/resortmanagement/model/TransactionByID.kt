package com.example.resortmanagement.model

data class TransasctionByID(
    val transactionID:String,
    val check_in:String,
    val check_out:String,
    val total_bill:String,
    val roomNum:String,
    val roomPrice:Int,
    val guestID:String,
    val guestName:String,
    val guestTel:String

)
