package com.example.codeup.api.controller

import com.example.codeup.data.Loja
import com.example.codeup.data.UsuarioAtualizado
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LojaApi {
    @GET("api/loja")
    suspend fun buscarLojaCompleta(): Response<Loja>

    @POST("api/loja/comprar/{idItem}")
    suspend fun comprarItem(@Path("idItem") idItem: Int): Response<UsuarioAtualizado>
}