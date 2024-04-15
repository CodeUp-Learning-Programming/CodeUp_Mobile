package com.example.codeup.ui.composables.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codeup.R
import com.example.codeup.ui.composables.TextoBranco

@Composable
fun CardPerfil(
    imagem: Int,
    altImagem: Int,
    texto: String = "",
    titulo: String = ""
) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .background(Color.Black, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier
                    .height(33.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val painter: Painter = painterResource(id = imagem)
                Image(
                    painter = painter,
                    contentDescription = stringResource(altImagem),
                )
            }
            Row(
                modifier = Modifier
                    .height(33.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextoBranco(texto = texto, tamanhoFonte = 20, pesoFonte = "normal")

            }
            Row(
                modifier = Modifier
                    .height(33.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = titulo.uppercase(),
                    color = Color(194, 194, 194),
                    fontFamily = FontFamily(
                        Font(R.font.poppins_bold, FontWeight.Bold)
                    ),
                    fontSize = 13.sp,
                    textAlign = TextAlign.Start
                )
            }


        }

    }
}


