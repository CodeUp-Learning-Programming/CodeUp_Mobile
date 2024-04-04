package com.example.codeup.api

import java.io.Serializable

data class ItensAdquiridos(
    var nomeItem: String = "",
    var fotoItem: String = "",
    var tipoItem: String = "",
    var precoItem: Double = 0.0,
): Serializable

//{
//    "id": 0,
//    "fotoPerfil": "string",
//    "nome": "string",
//    "email": "string",
//    "token": "string",
//    "moedas": 0,
//    "nivel": 0,
//    "xp": 0,
//    "itensAdquiridos": [
//    {
//        "nomeItem": "string",
//        "fotoItem": "string",
//        "tipoItem": "string",
//        "precoItem": 0,
//        "descricaoItem": "string",
//        "equipado": true
//    }
//    ]
//}