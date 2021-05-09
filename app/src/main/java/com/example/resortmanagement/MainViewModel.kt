package com.example.resortmanagement

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resortmanagement.model.Post
import com.example.resortmanagement.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository):ViewModel() {

    val myReposponse: MutableLiveData<Post> = MutableLiveData()

    fun getStaff(){
        viewModelScope.launch {
            val response:Post = repository.getStaff()
            myReposponse.value = response
        }
    }
}