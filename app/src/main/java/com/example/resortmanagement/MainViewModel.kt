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
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.nio.channels.NonReadableChannelException
import kotlin.math.log

class MainViewModel(private val repository: Repository):ViewModel() {

    val user: MutableLiveData<User> = MutableLiveData()
    val allRoom: MutableLiveData<Rooms> = MutableLiveData()
    val login: MutableLiveData<LoginRes> = MutableLiveData()
    val roomStatus : MutableLiveData<Rooms> = MutableLiveData()
    val allGuest : MutableLiveData<Guest> = MutableLiveData()

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

    fun login(user:HashMap<String,Any>,listener:((status: Boolean,text: String) -> Unit)) {
        repository.login(user).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                login.value = it
                Log.i("success : ",it.toString())

                listener.invoke(true,it.access_token)
            }, {
                Log.i("error", it.message.toString())
                listener.invoke(false,"Invalid username")
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

    fun getAllGuests(){
        repository.getAllGuest().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                allGuest.value = it
                Log.i("Values ", it.toString())
            },{Log.i("error" , it.message.toString())})
    }

    fun getGuestByID(guest:HashMap<String,Any>, listener: (status: Boolean, data:Guest) -> Unit){
        repository.getGuestByid(guest).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                allGuest.value = it
                listener(true, it)
            }
    }
    fun updateGuest(guest: HashMap<String, Any>, listener: (status: Boolean, text:String) -> Unit){
        repository.updateGuest(guest).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("Res", it.toString())
                listener.invoke(true, it.toString())
            },{
                Log.i("Res", it.toString())
                listener.invoke(true,it.toString())
            })
    }

    fun addGuest(guest: HashMap<String, Any>, listener: (status: Boolean, text:String) -> Unit){
        repository.addGuest(guest).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("Res", it.toString())
                listener.invoke(true, "Success to add Guest")
            },{
                Log.i("Res", it.toString())
                listener.invoke(false,"Fail to add Guest")
            })
    }

    fun addEmp(emp: HashMap<String, Any>, listener: (status: Boolean, text:String) -> Unit){
        repository.addEmp(emp).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("Res", it.toString())
                listener.invoke(true, "Success to add Employee")
            },{
                Log.i("Res", it.toString())
                listener.invoke(false,"Fail to add Employee")
            })
    }


}