package com.example.codeup.ui.composables.card

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.codeup.ui.composables.TextoBranco
import com.example.codeup.ui.screens.TelaHome

@Composable
fun CardSair(
    onCancel: () -> Unit,
    onConfirm: () -> Unit
) {
    val contexto = LocalContext.current
    var isPopupVisible by remember { mutableStateOf(false) }

    var gradiente = Brush.horizontalGradient(
        colors = listOf(
            Color(56, 56, 56),
            Color(37, 37, 37)
        )
    )
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(110.dp)
            .fillMaxSize()
            .background(
                Color.Black.copy(alpha = 0.5f),
                shape = RoundedCornerShape(4.dp)
            ), // Cor de fundo do cartão
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .padding(all = 60.dp)
                .background(Color.Black, shape = RoundedCornerShape(8.dp))
                .border(1.dp, gradiente, shape = RoundedCornerShape(4.dp)),
        ) {
            Column(
                modifier = Modifier
                    .padding(all = 10.dp)
            ) {


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    TextoBranco(
                        texto = "Tem certeza que você quer sair?",
                        tamanhoFonte = 14,
                        pesoFonte = "normal"
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    TextoBranco(
                        texto = "Se você sair, irá perder todo o progresso.",
                        tamanhoFonte = 10,
                        pesoFonte = "normal"
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    //voltar para a tela home
                    TextButton(onClick = onCancel) {
                        TextoBranco(texto = "CANCELAR", tamanhoFonte = 10, pesoFonte = "normal")
                    }
                    //fechar o pop up
                    TextButton(onClick = {
                        isPopupVisible = false
                        val telaHome = Intent(contexto, TelaHome::class.java)
                        contexto.startActivity(telaHome)
                    }
                    ) {
                        TextoBranco(texto = "SAIR", tamanhoFonte = 10, pesoFonte = "normal")
                    }
                }
            }
        }
    }
}

