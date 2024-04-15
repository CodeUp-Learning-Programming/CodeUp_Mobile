package com.example.codeup.api.controller

import com.example.codeup.data.Usuario
import com.example.codeup.data.UsuarioLoginRequest
import com.example.codeup.data.UsuarioRegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface UsuarioApi {

    @GET("api/usuarios/{id}")
    fun buscarPorId(@Path("id") id: Int): Call<Usuario>

    @GET("api/usuarios/atualizar/{id}")
    fun atualizarListaItensPorId(@Path("id") id: Int): Call<Usuario>

    @POST("api/usuarios/cadastrar")
    fun cadastrar(@Body usuarioRegisterRequest: UsuarioRegisterRequest): Call<Usuario>

    @POST("api/usuarios/login")
    fun login(@Body usuarioLoginRequest: UsuarioLoginRequest): Call<Usuario>

    @PATCH("api/usuarios/foto")
    fun atualizarFotoPerfil(@Body novaFoto: String): Call<Void>

    @DELETE("api/usuarios/foto")
    fun removerFotoPerfil(): Call<Void>

    @DELETE("api/usuarios/perfil")
    fun removerPerfil(@Body senha: String): Call<String>
}