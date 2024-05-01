package com.example.codeup.data

import com.google.gson.annotations.SerializedName

data class UsuarioAtualizado(
    var moedas: Int = 0,
    var nivel: Int = 0,
    var xp: Int = 0,
    @SerializedName("itensAdquiridos") var itensAdquiridos:List<ItemAdquirido>? = emptyList()
)
