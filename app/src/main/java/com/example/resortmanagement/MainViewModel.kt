package com.example.resortmanagement

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resortmanagement.model.Post
import com.example.resortmanagement.model.Rooms
import com.example.resortmanagement.model.User
import com.example.resortmanagement.repository.Repository
import com.example.resortmanagement.repository.RepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository):ViewModel() {

    val user: MutableLiveData<User> = MutableLiveData()
    val allRoom: MutableLiveData<Rooms> = MutableLiveData()
    fun getUser(){
        repository.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                user.value = it
            }, {
                Log.i("error", it.message.toString())
            })
    }
    fun getAllRoom(){
        repository.getAllRoom()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                allRoom.value = it
            },{
                Log.i("error",it.message.toString())
            })
    }
}