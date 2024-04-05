package com.example.codeup.api.controller

import com.example.codeup.data.Exercicio
import com.example.codeup.data.JsResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ExercicioApi {

    @GET("/exercicios/{idFase}")
    fun buscarExerciciosPorIdFase(@Path("idFase") idFase: Int): Call<List<Exercicio>>

    @GET("/exercicios/js")
    fun testJavaScriptCode(
        @Query("funcao") funcao: String,
        @Query("idExercicio") idExercicio: Int,
        @Query("idFase") idFase: Int
    ): Call<JsResult>
}