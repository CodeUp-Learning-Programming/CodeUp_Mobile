package com.example.codeup.api

import retrofit2.Call
import retrofit2.http.POST

interface ApiCodeUp {

    @POST("/login")
    fun get(): Call<List<Usuario>>

}