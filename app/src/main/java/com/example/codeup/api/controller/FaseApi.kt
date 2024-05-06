package com.example.codeup.api.controller

import com.example.codeup.data.Fase
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FaseApi {
    @GET("fases/{idMateria}")
    suspend fun buscarFasePelaMateria(@Path("idMateria") idMateria: Int): Response<List<Fase>>
}