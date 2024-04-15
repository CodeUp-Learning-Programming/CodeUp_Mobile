package com.example.codeup.api.controller

import com.example.codeup.data.Materia
import retrofit2.Call
import retrofit2.http.GET

interface MateriaApi {
    @GET("/materias")
    fun listar(): Call<List<Materia>>
}