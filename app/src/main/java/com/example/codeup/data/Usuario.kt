package com.example.codeup.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Usuario(
    var id:Int,
    @SerializedName("fotoPerfil") var fotoPerfil:String = "",
    var nome:String = "",
    var email:String = "",
    var token: String = "",
    var moedas: Int = 0,
    var nivel: Int = 0,
    var xp: Int = 0,
    var vidas: Int = 0,
    var sequencia: Int = 0,
    @SerializedName("itensAdquiridos") var itensAdquiridos:List<ItemAdquirido> = emptyList()
): Serializable
