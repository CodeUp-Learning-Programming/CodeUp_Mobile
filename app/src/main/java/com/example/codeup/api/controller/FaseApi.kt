package com.example.codeup.api.controller

import com.example.codeup.data.Fase
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FaseApi {
    @GET("/fases/{idMateria}")
    fun buscarFaseResultPorIdMateriaIdUsuario(@Path("idMateria") idMateria: Int): Call<List<Fase>>
}