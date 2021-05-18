package com.example.resortmanagement.repository

import com.example.resortmanagement.model.*
import com.example.resortmanagement.repository.service.ApiInterface
import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Observable

class RepositoryImpl(private val dataSource: ApiInterface) : Repository {

    override fun getUser(): Observable<Response> {
        return dataSource.getUser()
    }

    override fun getAllRoom(): Observable<Response> {
        return dataSource.getAllRoom()
    }

    override fun login(user:HashMap<String, Any>): Observable<LoginRes> {
        return dataSource.login(user)
    }

    override fun getRoomStatus(): Observable<Response> {
        return dataSource.getRoomStatus()
    }

    override fun getAllGuest(): Observable<Response> {
        return dataSource.getAllGuests()
    }

    override fun getGuestByid(guest: HashMap<String, Any>): Observable<Response> {
        return dataSource.getGuestByID(guest)
    }

    override fun updateGuest(guest: HashMap<String, Any>): Observable<Response> {
        return dataSource.updateGuest(guest)
    }

    override fun addGuest(guest: HashMap<String, Any>): Observable<Response> {
        return dataSource.addGuest(guest)
    }

    override fun addEmp(emp: HashMap<String, Any>): Observable<Response> {
        return dataSource.adaEmp(emp)
    }

    override fun createRoom(room: HashMap<String, Any>): Observable<Response> {
        return dataSource.createRoom(room)
    }

    override fun getRoomByID(room: HashMap<String, Any>): Observable<Response> {
        return dataSource.getRoomByID(room)
    }

    override fun updateRoom(room: HashMap<String, Any>): Observable<Response> {
        return dataSource.updateRoom(room)
    }

    override fun booking(data: HashMap<String, Any>): Observable<Response> {
        return dataSource.booking(data)
    }

    override fun getBooking(): Observable<Response> {
        return dataSource.getTransaction()
    }

}