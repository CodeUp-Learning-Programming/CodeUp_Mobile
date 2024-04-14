package com.example.codeup.ui.composables.card

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
fun CardPopup(
    tituloFase: String,
    desbloqueada: Boolean = false,
    qtdExerciciosFase: Int = 0,
    qtdExerciciosFaseConcluidos: Int = 0,

) {
    // Conteúdo do pop-up
    Surface{
        if (desbloqueada) {
            CardAprenda(
                tituloFase = tituloFase,
                desbloqueada = desbloqueada,
                qtdExerciciosFaseConcluidos = qtdExerciciosFaseConcluidos,
                qtdExerciciosFase = qtdExerciciosFase,
            )
        }
    }
}
