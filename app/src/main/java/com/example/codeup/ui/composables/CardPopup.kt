package com.example.codeup.ui.composables

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
fun CardPopup(
    bloqueado: Boolean = false,
    totalExerciciosConcluidos: Int = 0,
    totalExercicios: Int = 0,

) {
    // Conteúdo do pop-up
    Surface{
        if (!bloqueado) {
            CardAprenda(
                bloqueado = false,
                totalExerciciosConcluidos = totalExerciciosConcluidos,
                totalExercicios = totalExercicios,
            )
        }
    }
}
