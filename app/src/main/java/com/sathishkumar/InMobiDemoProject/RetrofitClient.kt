package com.sathishkumar.InMobiDemoProject

import com.example.networkingassignretrofit.UserAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{

    val userApi = retrofit()
        .create(UserAPI::class.java)

    val postApi = retrofit()
        .create(PostAPI::class.java)

    private fun retrofit() = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}