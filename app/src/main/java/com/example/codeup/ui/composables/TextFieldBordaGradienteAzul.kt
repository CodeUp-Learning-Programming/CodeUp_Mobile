package com.example.codeup.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextFieldBordaGradienteAzul(
    isTextFieldFocused: Boolean,
    texto: String,
    label: String,
    dadoIncorreto: Boolean = false,
    onValueChanged: (String) -> Unit,
    onFocusChanged: (FocusState) -> Unit,
) {
    val corDaBorda = if (isTextFieldFocused) {
        Brush.horizontalGradient(
            colors = listOf(
                Color(0, 225, 242),
                Color(0, 132, 249)
            )
        )
    } else if (dadoIncorreto) {
        Brush.horizontalGradient(
            colors = listOf(
                Color(242, 0, 0),
                Color(242, 0, 0)
            )
        )
    } else {
        Brush.horizontalGradient(
            colors = listOf(
                Color(56, 56, 56),
                Color(37, 37, 37)
            )
        )
    }

    BasicTextField(
        value = texto,
        onValueChange = { onValueChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { onFocusChanged(it) }
            .border(1.dp, corDaBorda, shape = RoundedCornerShape(8.dp))
            .background(
                Color(0, 0, 0), shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
    ) {
        val textoExibido = if (texto.isEmpty()) label else texto
        val corDoTexto =
            if (texto.isEmpty()) Color.Gray else Color.White // Ajustando a cor do texto
        Text(
            text = textoExibido,
            color = corDoTexto,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}