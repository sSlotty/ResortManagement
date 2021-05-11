package com.example.resortmanagement.repository

import com.example.resortmanagement.model.*
import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Observable

interface Repository {
    fun getUser(): Observable<User>
    fun getAllRoom(): Observable<Rooms>
    fun login(user: HashMap<String,Any>):Observable<LoginRes>
    fun getRoomStatus(): Observable<Rooms>
    fun getAllGuest(): Observable<Guest>
}