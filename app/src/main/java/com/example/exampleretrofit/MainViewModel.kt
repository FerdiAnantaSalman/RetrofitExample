package com.example.exampleretrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exampleretrofit.model.Post
import com.example.exampleretrofit.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse : MutableLiveData<Response<Post>> = MutableLiveData()
    val myResponse2 : MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val myResponse3 : MutableLiveData<Response<Void>> = MutableLiveData()


    fun getPost(userId: Int) {
        viewModelScope.launch {
            val response = repository.getPost(userId)
            myResponse.value = response
        }
    }

    fun getComment(postId: Int, option: Map<String, String>) {
        viewModelScope.launch {
            val response = repository.getComment(postId, option)
            myResponse2.value = response
        }
    }

    fun pushPost(userId: Int, id: Int, title: String, body: String) {
        viewModelScope.launch {
            val response = repository.pushPost(userId, id, title, body)
            myResponse.value = response
        }
    }

    fun patchPost(num: Int, userId: Int, id: Int, title: String, body: String) {
        viewModelScope.launch {
            val response = repository.patchPost(num, userId, id, title, body)
            myResponse.value = response
        }
    }

    fun putPost(num: Int, userId: Int?, id: Int?, title: String?, body: String?) {
        viewModelScope.launch {
            val response = repository.putPost(num, userId, id, title, body)
            myResponse.value = response
        }
    }

    fun deletePost(id: Int) {
        viewModelScope.launch {
            val response = repository.deletePost(id)
            myResponse3.value = response
        }
    }
}