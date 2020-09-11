package com.sathishkumar.InMobiDemoProject

import retrofit2.Response
import retrofit2.http.GET

interface PostAPI {

    @GET("posts")
    suspend fun getPosts(): Response<List<Posts>>

}