package com.example.codeup.ui.composables.componentes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.codeup.R

@Composable
fun TextoBranco(
    texto: String,
    tamanhoFonte: Int,
    pesoFonte: String = "",
    alinhamentoTexto: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier
) {
    if (pesoFonte == "Titulo") {
        Text(
            text = texto,
            color = Color.White,
            fontFamily = FontFamily(
                Font(R.font.poppins_bold, FontWeight.Bold)
            ),
            fontSize = tamanhoFonte.sp,
            textAlign = alinhamentoTexto
        )
    } else {
        Text(
            text = texto,
            color = Color.White,
            fontFamily = FontFamily(
                Font(R.font.poppins_semi_bold, FontWeight.SemiBold)
            ),
            fontSize = tamanhoFonte.sp,
            textAlign = alinhamentoTexto
        )
    }
}