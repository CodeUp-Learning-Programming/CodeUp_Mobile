package com.example.codeup.ui.composables.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextOpcaoPergunta(
    texto: String,
    isSelected: Boolean,
    onOptionSelected: () -> Unit,
    respostaCerta: Boolean = false,
    validarResposta : Boolean = false,

    ) {

    val interactionSource = remember { MutableInteractionSource() }

    //selecionado
    val corDaBorda = if (isSelected && !validarResposta) {
        Brush.horizontalGradient(
            colors = listOf(
                Color(0, 225, 242),
                Color(0, 132, 249)
            )
        )
    } else if(!respostaCerta && validarResposta){
        //padrão
        Brush.horizontalGradient(
            colors = listOf(
                Color(242, 0, 0),
                Color(249, 45, 0)
            )
        )
    } else if(respostaCerta && validarResposta){
        //padrão
        Brush.horizontalGradient(
            colors = listOf(
                Color(48, 242, 0),
                Color(4, 186, 1)
            )
        )
    }else {
        //padrão
        Brush.horizontalGradient(
            colors = listOf(
                Color(0, 225, 242).copy(alpha = 0.2f),
                Color(0, 132, 249).copy(alpha = 0.2f)
            )
        )
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    onOptionSelected()
                }
            )
            .height(35.dp)
            .background(
                Color(0, 166, 247).copy(0.1f),
                shape = RoundedCornerShape(4.dp)
            )
            .border(1.dp, corDaBorda, shape = RoundedCornerShape(4.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(start = 10.dp),
            text = texto,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}