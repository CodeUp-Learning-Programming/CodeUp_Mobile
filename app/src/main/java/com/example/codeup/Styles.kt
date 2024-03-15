package com.example.codeup

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextoBranco(texto: String, tamanhoFonte:Int, pesoFonte: String){
    if(pesoFonte === "Titulo"){
        Text(
            text = texto,
            color = Color.White,
            fontFamily = FontFamily(
                Font(R.font.poppins_bold, FontWeight.Bold)
            ),
            fontSize = tamanhoFonte.sp,
            textAlign = TextAlign.Start
        )
    }else{
        Text(
            text = texto,
            color = Color.White,
            fontFamily = FontFamily(
                Font(R.font.poppins_semi_bold, FontWeight.SemiBold)
            ),
            fontSize = tamanhoFonte.sp,
            textAlign = TextAlign.Start
        )
    }
}

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

@Composable
fun TextFieldBordaGradienteAzul(
    isTextFieldFocused: Boolean,
    texto: String,
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
            .background(Color(1,1,1))
            .padding(8.dp),
    ) {
        TextoBranco(texto = texto, tamanhoFonte = 14, pesoFonte = "normal")
    }
}

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