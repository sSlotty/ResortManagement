package com.example.resortmanagement.repository.service

import com.example.resortmanagement.model.*
import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

interface ApiInterface {

    @GET("/user")
    fun getUser(): Observable<User>

    @GET("/guests")
    fun getAllGuests(): Observable<Guest>

    @GET("/rooms")
    fun getAllRoom(): Observable<Rooms>

    @GET("/rooms/status?status=true")
    fun getRoomStatus(): Observable<Rooms>

    @POST("/authentication/token")
    fun login(@Body body: HashMap<String, Any>): Observable<LoginRes>

    @GET("/guests/id")
    fun getGuestByID(@QueryMap params: HashMap<String, Any>): Observable<Guest>

    @PUT("/guests")
    fun updateGuest(@Body body: HashMap<String, Any>):Observable<HashMap<String,Any>>

    @POST("/guests")
    fun addGuest(@Body body: HashMap<String, Any>):Observable<HashMap<String,Any>>

    @POST("authentication/signup")
    fun adaEmp(@Body body: HashMap<String, Any>):Observable<HashMap<String,Any>>
//    @GET("/api/v2/pokedex/2/")
//    fun getPokemonKanto(): Observable<Kanto>
//
//    @GET("/api/v2/pokemon/{keyword}")
//    fun getPokemon(@Path("keyword") keyword: String): Observable<Pokemon>
}