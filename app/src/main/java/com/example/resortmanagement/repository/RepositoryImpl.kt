package com.example.resortmanagement.repository

import com.example.resortmanagement.api.RetrofitInstance
import com.example.resortmanagement.model.Post
import com.example.resortmanagement.model.Rooms
import com.example.resortmanagement.model.User
import com.example.resortmanagement.repository.service.ApiInterface
import io.reactivex.rxjava3.core.Observable

class RepositoryImpl(private val dataSource: ApiInterface) : Repository {
    override fun getUser(): Observable<User> {
        return dataSource.getUser()
    }

    override fun getAllRoom(): Observable<Rooms> {
        return dataSource.getAllRoom()
    }

}