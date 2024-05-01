package com.example.codeup.data

import java.io.Serializable

data class ItemLoja(
    var id: Int,
    var nomeItem: String = "",
    var fotoItem: String = "",
    var tipoItem: String = "",
    var precoItem: Double = 0.0,
    var descricaoItem: String = "",
    var adquirido:Boolean = false,
): Serializable