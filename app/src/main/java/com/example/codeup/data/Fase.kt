package com.example.codeup.data

data class Fase (
    val faseId:Int,
    val numFase:Int,
    val tituloFase:String,
    val qtdExerciciosFase: Int,
    val qtdExerciciosFaseConcluidos: Int,
    val desbloqueada:Boolean = false
)