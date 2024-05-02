package com.example.codeup.ui.composables.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.codeup.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldBordaGradienteAzul(
    modifier: Modifier = Modifier,
    isTextFieldFocused: Boolean,
    texto: String,
    label: String,
    enabled: Boolean = true,
    dadoIncorreto: Boolean = false,
    onValueChange: (String) -> Unit,
    onFocusChanged: (FocusState) -> Unit,
    maxLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    val focusManager = LocalFocusManager.current
    var passwordVisible by remember { mutableStateOf(true) } // Password visibility state


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
            Modifier
                .border(1.dp, corDaBorda, RoundedCornerShape(8.dp))
                .height(55.dp)
                .onFocusChanged { onFocusChanged(it) }
                .background(
                    Color(0, 0, 0), shape = RoundedCornerShape(8.dp)
                )),
        value = if (texto.isEmpty() && !isTextFieldFocused) label else texto,
        onValueChange = { onValueChange(it) },
        textStyle = TextStyle(
            color = if (texto.isEmpty()) Color.Gray else Color.White,
            textAlign = TextAlign.Start,

            ),
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


        visualTransformation = if (keyboardType == KeyboardType.Password && passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            when {
                keyboardType == KeyboardType.Password -> {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = if (passwordVisible) "Hide password" else "Show password"
                        )
                    }
                }

                label == stringResource(R.string.text_data_nascimento) -> {
                    Icon(imageVector = Icons.Filled.CalendarMonth, contentDescription = "Calendar")

                }

                label == stringResource(R.string.text_nome_label) -> {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = "Nome")
                }

                label == stringResource(R.string.text_email_label) -> {
                    Icon(imageVector = Icons.Filled.AlternateEmail, contentDescription = "Email")
                }
            }
        },
        enabled = enabled,

        )

}