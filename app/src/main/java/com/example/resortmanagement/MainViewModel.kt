package com.example.resortmanagement

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resortmanagement.model.*
import com.example.resortmanagement.repository.Repository
import com.example.resortmanagement.repository.RepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository):ViewModel() {

    val user: MutableLiveData<User> = MutableLiveData()
    val allRoom: MutableLiveData<Rooms> = MutableLiveData()
    val login: MutableLiveData<LoginRes> = MutableLiveData()
    val roomStatus : MutableLiveData<Rooms> = MutableLiveData()

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

    fun login(user:HashMap<String,Any>){
        repository.login(user).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                login.value = it
                Log.i("success : ",it.toString())
            }, {
                Log.i("error", it.message.toString())
            })
    }

    fun getRoomStatus(){
        repository.getAllRoom()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                roomStatus.value = it
            },{
                Log.i("error",it.message.toString())
            })
    }
}