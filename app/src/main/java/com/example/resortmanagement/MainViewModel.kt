package com.example.resortmanagement

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resortmanagement.model.*
import com.example.resortmanagement.repository.Repository
import com.example.resortmanagement.repository.RepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.nio.channels.NonReadableChannelException
import kotlin.math.log

class MainViewModel(private val repository: Repository):ViewModel() {

    val user: MutableLiveData<List<Users>> = MutableLiveData()
    val allRoom: MutableLiveData<List<Rooms>> = MutableLiveData()
    val login: MutableLiveData<LoginRes> = MutableLiveData()
    val roomStatus : MutableLiveData<List<Rooms>> = MutableLiveData()
    val allGuest : MutableLiveData<List<Guest>> = MutableLiveData()
    val booking:MutableLiveData<List<Booking>> =  MutableLiveData()
    val transaction :MutableLiveData<List<Transasction>> = MutableLiveData()

    fun getUser(listener: (status: Boolean) -> Unit){
        repository.getUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val data = Gson().fromJson(response.data, Array<Users>::class.java).toList()
                user.value = data
                listener.invoke(true)
                Log.i("success", data.toString())
            }, {
                Log.i("error", it.message.toString())
                listener.invoke(false)
            })
    }

    fun addEmp(emp: HashMap<String, Any>, listener: (status: Boolean) -> Unit){
        repository.addEmp(emp).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val data = Gson().fromJson(response.data, Array<Users>::class.java).toList()
                user.value = data
                if(response.status == 201){
                    listener.invoke(true)
                }else{
                    listener.invoke(false)
                }

            },{
                Log.i("error", it.message.toString())
                listener.invoke(false)
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

    fun getAllRoom(listener: (status: Boolean) -> Unit){
        repository.getAllRoom()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response->
                val data = Gson().fromJson(response.data, Array<Rooms>::class.java).toList()
                allRoom.value = data
                listener.invoke(true)
            },{
                Log.i("Res", it.toString())
                listener.invoke(false)
            })
    }

    fun getRoomStatus(listener: (status: Boolean) -> Unit){
        repository.getRoomStatus()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response->
                val data = Gson().fromJson(response.data, Array<Rooms>::class.java).toList()
                roomStatus.value = data
                listener.invoke(true)
            },{
                Log.i("Res", it.toString())
                listener.invoke(false)
            })
    }

    fun addRoom(room: HashMap<String, Any>, listener: (status: Boolean) -> Unit){
        repository.createRoom(room).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val data = Gson().fromJson(response.data, Array<Rooms>::class.java).toList()
                allRoom.value = data
                if(response.status == 201){
                    listener.invoke(true)
                }else{
                    listener.invoke(false)
                }

            },{
                Log.i("error", it.message.toString())
                listener.invoke(false)
            })
    }

    fun getRoomByID(room:HashMap<String,Any>, listener: (status: Boolean) -> Unit){
        repository.getRoomByID(room).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val data = Gson().fromJson(response.data, Array<Rooms>::class.java).toList()
                allRoom.value = data
                listener(true)
            },{
                Log.i("Res", it.toString())
                listener.invoke(false)
            })
    }

    fun updateRoom(room: HashMap<String, Any>, listener: (status: Boolean) -> Unit){
        repository.updateRoom(room).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val data = Gson().fromJson(response.data, Array<Rooms>::class.java).toList()
                allRoom.value = data
                if(response.status == 200){
                    listener.invoke(true)
                }else{
                    listener.invoke(false)
                }

            },{
                Log.i("error", it.message.toString())
                listener.invoke(false)
            })
    }

    fun getAllGuests(listener: (status: Boolean) -> Unit){
        repository.getAllGuest().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response->
                val data = Gson().fromJson(response.data, Array<Guest>::class.java).toList()
                allGuest.value = data
                listener.invoke(true)
            },{
                Log.i("Res", it.toString())
                listener.invoke(false)
            })
    }

    fun getGuestByID(guest:HashMap<String,Any>, listener: (status: Boolean) -> Unit){
        repository.getGuestByid(guest).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val data = Gson().fromJson(response.data, Array<Guest>::class.java).toList()
                allGuest.value = data
                listener(true)
            },{
                Log.i("Res", it.toString())
                listener.invoke(false)
            })
    }
    fun updateGuest(guest: HashMap<String, Any>, listener: (status: Boolean) -> Unit){
        repository.updateGuest(guest).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response->
                val data = Gson().fromJson(response.data, Array<Guest>::class.java).toList()
                allGuest.value = data
                listener.invoke(true)
            },{
                Log.i("Res", it.toString())
                listener.invoke(false)
            })
    }

    fun addGuest(guest: HashMap<String, Any>, listener: (status: Boolean) -> Unit){
        repository.addGuest(guest).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response->
                val data = Gson().fromJson(response.data, Array<Guest>::class.java).toList()
                allGuest.value = data
                if(response.status == 201){
                    listener.invoke(true)
                }else{
                    listener.invoke(false)
                }

            },{
                Log.i("Res", it.toString())
                listener.invoke(false)
            })
    }

    fun booking(data: HashMap<String, Any>, listener: (status: Boolean) -> Unit){
        repository.booking(data).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response->
//                val res = Gson().fromJson(response.data, Array<Booking>::class.java).toList()
//                booking.value = res
                if(response.status == 201){
                    listener.invoke(true)
                }else{
                    listener.invoke(false)
                }

            },{
                Log.i("Res", it.toString())
                listener.invoke(false)
            })
    }

    fun getBooking(listener: (status: Boolean) -> Unit){
        repository.getBooking().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                val res = Gson().fromJson(response.data, Array<Transasction>::class.java).toList()
                transaction.value = res

                if (response.status == 200){
                    listener.invoke(true)
                }else{
                    listener.invoke(false)
                }

            },{
                listener.invoke(false)
            })
    }




}