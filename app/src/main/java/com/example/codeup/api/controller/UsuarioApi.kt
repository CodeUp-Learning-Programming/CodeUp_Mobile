package com.example.codeup.api.controller

import com.example.codeup.data.FotoPerfilRequest
import com.example.codeup.data.Usuario
import com.example.codeup.data.UsuarioLoginRequest
import com.example.codeup.data.UsuarioRanking
import com.example.codeup.data.UsuarioRegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface UsuarioApi {

    @GET("usuarios/{id}")
    suspend fun buscarPorId(@Path("id") id: Int): Response<Usuario>

    @GET("usuarios/exercicios/mes/{id}")
    suspend fun buscarExerciciosPorMes(@Path("id") id: Int): Response<Map<String, String>>

    @GET("usuarios/atualizar/{id}")
    suspend fun atualizarListaItensPorId(@Path("id") id: Int): Response<Usuario>

    @GET("usuarios/ranking")
    suspend fun ranking(): Response<List<UsuarioRanking>>

    @POST("usuarios/cadastrar")
    suspend fun cadastrar(@Body usuarioRegisterRequest: UsuarioRegisterRequest): Response<Usuario>

    @POST("usuarios/login")
    suspend fun login(@Body usuarioLoginRequest: UsuarioLoginRequest): Response<Usuario>

    @PATCH("usuarios/foto")
    suspend fun atualizarFotoPerfil(@Body fotoPerfilRequest: FotoPerfilRequest): Response<Void>

    @DELETE("usuarios/foto")
    suspend fun removerFotoPerfil(): Response<Void>

    @DELETE("usuarios/perfil")
    suspend fun removerPerfil(@Body senha: String): Response<String>
}