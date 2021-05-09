package com.example.resortmanagement.repository

import com.example.resortmanagement.model.Rooms
import com.example.resortmanagement.model.User
import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Observable

interface Repository {
    fun getUser(): Observable<User>
    fun getAllRoom(): Observable<Rooms>
}