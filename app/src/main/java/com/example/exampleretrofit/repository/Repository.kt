package com.example.exampleretrofit.repository

import com.example.exampleretrofit.api.ApiConfig
import com.example.exampleretrofit.api.ApiService
import com.example.exampleretrofit.model.Post
import retrofit2.Response

class Repository() {

    suspend fun getPost(userId: Int) : Response<Post> {
        return ApiConfig.instance.getPost(userId)
    }

    suspend fun getComment(postId: Int, option: Map<String, String>) : Response<List<Post>> {
        return ApiConfig.instance.getComment(postId, option)
    }

    suspend fun pushPost(userId: Int, id: Int, title: String, body: String) : Response<Post> {
        return ApiConfig.instance.pushPost(userId, id, title, body)
    }

    suspend fun patchPost(num: Int, userId: Int, id: Int, title: String, body: String) : Response<Post> {
        return ApiConfig.instance.patchPost(num, userId, id, title, body)
    }

    suspend fun putPost(num: Int, userId: Int?, id: Int?, title: String?, body: String?) : Response<Post> {
        return ApiConfig.instance.putPost(num, userId, id, title, body)
    }

    suspend fun deletePost(id: Int) : Response<Void> {
        return ApiConfig.instance.deletePost(id)
    }
}