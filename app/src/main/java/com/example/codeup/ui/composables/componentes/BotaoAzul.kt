package com.example.codeup.ui.composables.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codeup.R

@Composable
fun BotaoAzul(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    altura: Int = 56,
    largura: Int = 120,
    tamanhoFonte: Int = 16,
    loading: Boolean = false
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .size(width = largura.dp, height = altura.dp)
            .shadow(4.dp, RoundedCornerShape(1.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0, 225, 242),
                        Color(0, 132, 249)
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    onClick.invoke()
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (!loading) {
            Text(
                text = text,
                color = Color.White,
                fontFamily = FontFamily(
                    Font(R.font.poppins_semi_bold, FontWeight.SemiBold)
                ),
                fontSize = tamanhoFonte.sp
            )
        } else {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                color = Color.White,
                strokeWidth = 3.dp
            )
        }
    }
}
