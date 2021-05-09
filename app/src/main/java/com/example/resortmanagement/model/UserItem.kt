package com.example.resortmanagement.model


import com.google.gson.annotations.SerializedName

data class UserItem(
    @SerializedName("_id")
    var id: String?,
    @SerializedName("job_position")
    var jobPosition: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("salary")
    var salary: Int?,
    @SerializedName("tel")
    var tel: String?
)