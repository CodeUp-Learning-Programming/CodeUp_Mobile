package com.example.codeup.ui.composables.componentes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.codeup.R


@Composable
fun TextoAzulGradienteSublinhado(texto: String, tamanhoFonte: Int, pesoFonte: String) {

    val gradientColors = listOf(
        Color(0, 225, 242),
        Color(0, 132, 249)
    )

    if (pesoFonte == "Titulo") {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        brush = Brush.linearGradient(
                            colors = gradientColors,
                        )
                    )
                ) {
                    append(texto)
                }
            },
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
                fontFamily = FontFamily(Font(R.font.poppins_bold, FontWeight.Bold)),
                fontSize = tamanhoFonte.sp,
                textAlign = TextAlign.Start,
                color = Color.Black,
            ),
        )
    } else {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        brush = Brush.linearGradient(
                            colors = gradientColors,
                        )
                    )
                ) {
                    append(texto)
                }
            },
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
                fontFamily = FontFamily(Font(R.font.poppins_semi_bold, FontWeight.SemiBold)),
                fontSize = tamanhoFonte.sp,
                textAlign = TextAlign.Start,
                color = Color.Black,
            ),
        )
    }
}