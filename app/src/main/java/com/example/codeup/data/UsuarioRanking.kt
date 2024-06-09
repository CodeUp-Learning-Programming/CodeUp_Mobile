package com.example.codeup.data

import java.io.Serializable

data class UsuarioRanking(
    var nome: String = "",
    var xp: String = "",
    var foto: String = "",
    var email: String = "",
    ) : Serializable
