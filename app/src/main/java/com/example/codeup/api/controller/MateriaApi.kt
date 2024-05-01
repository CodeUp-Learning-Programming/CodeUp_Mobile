package com.example.codeup.api.controller

import com.example.codeup.data.Materia
import retrofit2.Response
import retrofit2.http.GET

interface MateriaApi {
    @GET("materias")
    suspend fun listar(): Response<List<Materia>>
}