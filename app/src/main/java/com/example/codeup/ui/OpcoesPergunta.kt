package com.example.codeup.ui

data class OpcoesPergunta(
    val idFase: Int,
    val idExercicio: Int,
    val texto: String,
    val respostaCorreta: Boolean = false,
    val respostaEnviar: String = ""
)
