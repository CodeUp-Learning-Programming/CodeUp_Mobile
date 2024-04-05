package com.example.codeup.api.controller

import com.example.codeup.data.Loja
import com.example.codeup.data.Usuario
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LojaApi {
    @GET("/loja")
    fun buscarLojaCompleta(): Call<Loja>

    @POST("/loja/comprar/{idItem}")
    fun comprarItem(@Path("idItem") idItem: Int): Call<Usuario>
}