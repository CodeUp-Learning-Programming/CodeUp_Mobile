package com.example.codeup.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .size(width = 120.dp, height = 56.dp)
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
            .clickable { onClick.invoke() }
            .padding(16.dp)
    ){
        Text(
            text = text,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center),
            fontFamily = FontFamily(
                Font(R.font.poppins_semi_bold, FontWeight.SemiBold)
            ),
            fontSize = 16.sp
        )
    }
}