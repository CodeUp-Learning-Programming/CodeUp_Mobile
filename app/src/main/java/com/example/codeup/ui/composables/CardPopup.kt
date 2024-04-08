package com.example.codeup.ui.composables

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
fun CardPopup(
    desbloqueada: Boolean = false,
    qtdExerciciosFase: Int = 0,
    qtdExerciciosFaseConcluidos: Int = 0,

) {
    // Conteúdo do pop-up
    Surface{
        if (!desbloqueada) {
            CardAprenda(
                desbloqueada = desbloqueada,
                qtdExerciciosFaseConcluidos = qtdExerciciosFaseConcluidos,
                qtdExerciciosFase = qtdExerciciosFase,
            )
        }
    }
}
