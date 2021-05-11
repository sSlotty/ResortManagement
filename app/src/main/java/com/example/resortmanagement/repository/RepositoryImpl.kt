package com.example.resortmanagement.repository

import com.example.resortmanagement.model.*
import com.example.resortmanagement.repository.service.ApiInterface
import io.reactivex.rxjava3.core.Observable

class RepositoryImpl(private val dataSource: ApiInterface) : Repository {
    override fun getUser(): Observable<User> {
        return dataSource.getUser()
    }

    override fun getAllRoom(): Observable<Rooms> {
        return dataSource.getAllRoom()
    }

    override fun login(user:HashMap<String, Any>): Observable<LoginRes> {
        return dataSource.login(user)
    }

    override fun getRoomStatus(): Observable<Rooms> {
        return dataSource.getRoomStatus()
    }

}