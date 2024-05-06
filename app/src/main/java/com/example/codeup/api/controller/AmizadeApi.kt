package com.example.codeup.api.controller

import com.example.codeup.data.Amigo
import com.example.codeup.data.AmizadeResult
import com.example.codeup.data.SolicitarAmizadeRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AmizadeApi {
        @POST("amizades/solicitacoes")
        suspend fun solicitarAmizade(@Body request: SolicitarAmizadeRequest): Response<Boolean>

        @GET("amizades/solicitacoes/recebidas")
        suspend fun solicitacoesRecebidas(@Query("idUsuario") idUsuario: Int): Response<List<AmizadeResult>>

        @GET("amizades/solicitacoes/enviadas")
        suspend fun solicitacoesEnviadas(@Query("idUsuario") idUsuario: Int): Response<List<AmizadeResult>>

        @GET("amizades/amigos")
        suspend fun listarAmigos(@Query("idUsuario") idUsuario: Int): Response<List<Amigo>>
}