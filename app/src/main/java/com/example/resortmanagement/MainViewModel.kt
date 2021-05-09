package com.example.resortmanagement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resortmanagement.model.Post
import com.example.resortmanagement.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository):ViewModel() {

    val myReposponse: MutableLiveData<Response<Post>> = MutableLiveData()

    fun getStaff(){
        viewModelScope.launch {
            val response:Response<Post> = repository.getStaff()
            myReposponse.value = response
        }
    }
}