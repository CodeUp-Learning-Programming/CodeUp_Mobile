package com.example.codeup.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun CheckboxComGradiente(
    lembrar: Boolean,
    onCheckedChange: (Boolean) -> Unit,
){
    val gradient =
        Brush.horizontalGradient(
            colors = listOf(
                Color(0, 0, 0),
                Color(0, 0, 0)
            )
        )
    val borderGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0, 225, 242),
            Color(0, 132, 249)
        )
    )
    val checkedGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0, 225, 242),
            Color(0, 132, 249)
        )
    )
    var cor_fundo = if (lembrar) {
        checkedGradient
    } else {
        gradient
    }
    Box(
        modifier = Modifier
            .size(24.dp)
            .background(cor_fundo)
            .border(1.dp, borderGradient, shape = RectangleShape)
    ) {
        Checkbox(
            checked = lembrar,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Transparent,
                uncheckedColor = Color.Transparent, // Define a cor não selecionada como transparente
                checkmarkColor = Color.Black,
//                        disabledIndeterminateColor = Color.LightGray
            )
        )
    }
}