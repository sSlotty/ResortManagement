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
    fun getGuestByid(guest:HashMap<String,Any>): Observable<Guest>
    fun updateGuest(guest: HashMap<String, Any>):Observable<HashMap<String,Any>>
    fun addGuest(guest: HashMap<String, Any>):Observable<HashMap<String,Any>>
    fun addEmp(emp:HashMap<String,Any>):Observable<HashMap<String,Any>>
}