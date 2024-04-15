package com.example.codeup.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDataBordaGradienteAzul(
    modifier: Modifier = Modifier,
    isTextFieldFocused: Boolean,
    texto: LocalDate,
    enabled: Boolean = true,
    dadoIncorreto: Boolean = false,
    onValueChange: (LocalDate) -> Unit,
    onFocusChanged: (FocusState) -> Unit,

    maxLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    val focusManager = LocalFocusManager.current

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

    OutlinedTextField(
        modifier = modifier.then(
            Modifier.border(1.dp, corDaBorda, RoundedCornerShape(8.dp))
                .height(55.dp)
                .onFocusChanged { onFocusChanged(it) }
                .background(
                    Color(0, 0, 0), shape = RoundedCornerShape(8.dp)
                )
        ),
        value = texto.format(DateTimeFormatter.ISO_LOCAL_DATE),
        onValueChange = { newValue ->
            try {
                val parsedDate = LocalDate.parse(newValue, DateTimeFormatter.ISO_LOCAL_DATE)
                onValueChange(parsedDate)
            } catch (e: Exception) {
                // Handle parsing error, if needed
            }
        },
        textStyle = TextStyle(
            color = Color.Gray,
            textAlign = TextAlign.Start
        ),
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            disabledTextColor = Color.Transparent, // Cor do texto quando desativado
            cursorColor = Color.White, // Cor do cursor
            errorCursorColor = Color.Red, // Cor do cursor quando houver erro
            focusedBorderColor = Color.Transparent, // Cor da borda quando focado
            unfocusedBorderColor = Color.Transparent, // Cor da borda quando não focado
            errorBorderColor = Color.Red, // Cor da borda quando houver erro
            disabledBorderColor = Color.Transparent // Cor da borda quando desativado
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        singleLine = maxLines == 1,
        maxLines = maxLines,
    )
}
