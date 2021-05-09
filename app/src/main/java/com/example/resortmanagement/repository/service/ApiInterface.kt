package com.example.resortmanagement.repository.service

import com.example.resortmanagement.model.Rooms
import com.example.resortmanagement.model.User
import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("/user")
    fun getUser(): Observable<User>

    @GET("/rooms")
    fun getAllRoom(): Observable<Rooms>
//    @GET("/api/v2/pokedex/2/")
//    fun getPokemonKanto(): Observable<Kanto>
//
//    @GET("/api/v2/pokemon/{keyword}")
//    fun getPokemon(@Path("keyword") keyword: String): Observable<Pokemon>
}