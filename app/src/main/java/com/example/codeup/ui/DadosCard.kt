package com.example.codeup.ui

data class DadosDoCard(
    val tituloFase:String,
    val desbloqueada: Boolean = false,
    val qtdExerciciosFase: Int = 0,
    val qtdExerciciosFaseConcluidos: Int = 0
)