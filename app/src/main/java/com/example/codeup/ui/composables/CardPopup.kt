package com.example.codeup.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CardPopup(
    bloqueado: Boolean,
    totalExerciciosConcluidos: Int,
    totalExercicios: Int,
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
