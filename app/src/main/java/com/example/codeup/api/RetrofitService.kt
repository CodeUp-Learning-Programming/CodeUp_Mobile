package com.example.codeup.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    const val BASE_URL = "https://localhost:8080/"

    fun getApiFilmesService(): ApiCodeUp {
        val cliente =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiCodeUp::class.java)

        return cliente
    }

}