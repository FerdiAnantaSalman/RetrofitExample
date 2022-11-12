package com.example.exampleretrofit.api

import com.example.exampleretrofit.model.Post
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // Get Data from API Using manipulation url(Path)
    @GET("posts/{Id}")
    suspend fun getPost(@Path("Id") userid: Int) : Response<Post>

    // GET Data from API Using manipulation url(@Query and Query Map)
    @GET("comments")
    suspend fun getComment(
        @Query("postId") postId: Int,
        @QueryMap option: Map<String, String>
    ) :Response<List<Post>>

    // POST Data from API
    @FormUrlEncoded
    @POST("posts")
    suspend fun pushPost(
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ) : Response<Post>


    // Update data from API using Patch
    @FormUrlEncoded
    @PATCH("posts/{id}")
    suspend fun patchPost(
        @Path("id") num: Int,
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body: String
    ) : Response<Post>

    // Update data from API using PUT
    @FormUrlEncoded
    @PUT("posts/{id}")
    suspend fun putPost(
        @Path("id") num: Int,
        @Field("userId") userId: Int?,
        @Field("id") id: Int?,
        @Field("title") title: String?,
        @Field("body") body: String?
    ) : Response<Post>

    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int) : Response<Void>

}