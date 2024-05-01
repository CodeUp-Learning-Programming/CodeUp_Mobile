package com.example.codeup.data

import java.io.Serializable

data class UsuarioRanking(
    var id: Int? = null,
    var nome: String = "",
    var fotoPerfil: String = "",
    var pontos: String = "",
    var posicao: Int = 0,
) : Serializable
