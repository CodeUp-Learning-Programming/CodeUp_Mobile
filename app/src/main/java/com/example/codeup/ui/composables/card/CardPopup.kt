package com.example.codeup.ui.composables.card

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.example.codeup.ui.composables.card.CardAprenda

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
