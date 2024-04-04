package com.example.codeup.api

import java.io.Serializable

data class Usuario(
    var id:Int? = null,
    var fotoPerfil:String = "",
    var nome:String = "",
    var token: String = "",
    var email:String = "",
    var senha:String = "",
    var moedas: Int = 0,
    var nivel: Int = 0,
    var xp: Int = 0,
    var dataNascimento:String = "",
): Serializable
