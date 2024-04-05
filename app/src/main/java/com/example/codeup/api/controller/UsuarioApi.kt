package com.example.codeup.api.controller

import com.example.codeup.data.Usuario
import com.example.codeup.data.UsuarioLoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface UsuarioApi {
    @GET("/usuarios/{id}")
    fun buscarPorId(@Path("id") id: Int): Call<Usuario>

    @GET("/usuarios/atualizar/{id}")
    fun atualizarListaItensPorId(@Path("id") id: Int): Call<Usuario>

    @POST("/usuarios/cadastrar")
    fun cadastrar(@Body usuario: Usuario): Call<Usuario>

    @POST("/usuarios/login")
    fun login(@Body usuarioLoginRequest: UsuarioLoginRequest): Call<Usuario>

    @PATCH("/usuarios/foto")
    fun atualizarFotoPerfil(@Body novaFoto: String): Call<Void>

    @DELETE("/usuarios/foto")
    fun removerFotoPerfil(): Call<Void>

    @DELETE("/usuarios/perfil")
    fun removerPerfil(@Body senha: String): Call<String>
}