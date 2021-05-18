package com.example.resortmanagement.repository.service

import com.example.resortmanagement.model.*
import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

interface ApiInterface {

    @GET("/user")
    fun getUser(): Observable<Response>

    @GET("/guests")
    fun getAllGuests(): Observable<Response>

    @POST("/rooms")
    fun createRoom(@Body body: HashMap<String, Any>): Observable<Response>

    @GET("/rooms")
    fun getAllRoom(): Observable<Response>

    @GET("rooms/status?status=True")
    fun getRoomStatus(): Observable<Response>

    @GET("rooms/id")
    fun getRoomByID(@QueryMap params: HashMap<String, Any>):Observable<Response>

    @PUT("/rooms/id")
    fun updateRoom(@Body body: HashMap<String, Any>):Observable<Response>

    @POST("/authentication/token")
    fun login(@Body body: HashMap<String, Any>): Observable<LoginRes>

    @GET("/guests/id")
    fun getGuestByID(@QueryMap params: HashMap<String, Any>): Observable<Response>

    @PUT("/guests")
    fun updateGuest(@Body body: HashMap<String, Any>):Observable<Response>

    @POST("/guests")
    fun addGuest(@Body body: HashMap<String, Any>):Observable<Response>

    @POST("authentication/signup")
    fun adaEmp(@Body body: HashMap<String, Any>):Observable<Response>


    @POST("transactions")
    fun booking(@Body body: HashMap<String, Any>):Observable<Response>

    @GET("transactions")
    fun getTransaction():Observable<Response>
//    @GET("/api/v2/pokedex/2/")
//    fun getPokemonKanto(): Observable<Kanto>
//
//    @GET("/api/v2/pokemon/{keyword}")
//    fun getPokemon(@Path("keyword") keyword: String): Observable<Pokemon>
}