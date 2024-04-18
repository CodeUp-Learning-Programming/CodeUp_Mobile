package com.example.codeup.data

import java.io.Serializable

data class Loja(
    val tipoDosItens: List<String> = emptyList(),
    val itensLoja: List<ItemLoja> = emptyList()
): Serializable
