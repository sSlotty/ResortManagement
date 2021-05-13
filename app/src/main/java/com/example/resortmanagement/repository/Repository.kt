package com.example.resortmanagement.repository

import com.example.resortmanagement.model.*
import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Observable

interface Repository {
    fun getUser(): Observable<Response>
    fun getAllRoom(): Observable<Response>
    fun login(user: HashMap<String,Any>):Observable<LoginRes>
    fun getRoomStatus(): Observable<Response>
    fun getAllGuest(): Observable<Response>
    fun getGuestByid(guest:HashMap<String,Any>): Observable<Response>
    fun updateGuest(guest: HashMap<String, Any>):Observable<Response>
    fun addGuest(guest: HashMap<String, Any>):Observable<Response>
    fun addEmp(emp:HashMap<String,Any>):Observable<Response>
}