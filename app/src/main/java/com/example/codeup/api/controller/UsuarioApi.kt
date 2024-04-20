package com.example.codeup.api.controller

import com.example.codeup.data.Usuario
import com.example.codeup.data.UsuarioLoginRequest
import com.example.codeup.data.UsuarioRegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface UsuarioApi {

    @GET("api/usuarios/{id}")
    suspend fun buscarPorId(@Path("id") id: Int): Response<Usuario>

    @GET("api/usuarios/atualizar/{id}")
    suspend fun atualizarListaItensPorId(@Path("id") id: Int): Response<Usuario>

    @POST("api/usuarios/cadastrar")
    suspend fun cadastrar(@Body usuarioRegisterRequest: UsuarioRegisterRequest): Response<Usuario>

    @POST("api/usuarios/login")
    suspend fun login(@Body usuarioLoginRequest: UsuarioLoginRequest): Response<Usuario>

    @PATCH("api/usuarios/foto")
    suspend fun atualizarFotoPerfil(@Body novaFoto: String): Response<Void>

    @DELETE("api/usuarios/foto")
    suspend fun removerFotoPerfil(): Response<Void>

    @DELETE("api/usuarios/perfil")
    suspend fun removerPerfil(@Body senha: String): Response<String>
}