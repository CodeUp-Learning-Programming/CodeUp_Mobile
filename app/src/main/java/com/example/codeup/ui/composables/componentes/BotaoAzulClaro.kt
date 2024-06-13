package com.example.codeup.ui.composables.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codeup.R

@Composable
fun BotaoAzulClaro(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    altura: Int = 36,
    largura: Int = 250,
    tamanhoFonte: Int = 12
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .size(width = largura.dp, height = altura.dp)
            .shadow(4.dp, RoundedCornerShape(1.dp))
            .background(
                Color(1, 169, 247),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    onClick.invoke()
                }
            )

    ) {
        Text(
            text = text,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center),
            fontFamily = FontFamily(
                Font(R.font.poppins_semi_bold, FontWeight.SemiBold)
            ),
            fontSize = tamanhoFonte.sp
        )
    }
}