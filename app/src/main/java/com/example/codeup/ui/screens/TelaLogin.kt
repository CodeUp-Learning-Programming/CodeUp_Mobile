package com.example.codeup.ui.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codeup.R
import com.example.codeup.Usuario
import com.example.codeup.ui.composables.BotaoAzul
import com.example.codeup.ui.composables.CheckboxComGradiente
import com.example.codeup.ui.composables.TextFieldBordaGradienteAzul
import com.example.codeup.ui.composables.TextoAzulGradienteSublinhado
import com.example.codeup.ui.composables.TextoBranco
import com.example.codeup.ui.theme.CodeupTheme

class TelaLogin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CodeupTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(1, 1, 1)),
                ) {
                    Login("extras")
                }
            }
        }
    }
}

@Composable
fun Login(name: String, modifier: Modifier = Modifier) {

    var lembrar by remember { mutableStateOf(false)}
    val contexto = LocalContext.current
    val (usuario, usuarioSetter) = remember{
        mutableStateOf(Usuario())
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 20.dp),
        verticalArrangement = Arrangement.Top) {

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )
        Row {
            TextoBranco(
                texto = stringResource(R.string.text_login),
                tamanhoFonte = 36,
                pesoFonte = "Titulo"
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            TextoBranco(texto = stringResource(R.string.text_email), tamanhoFonte = 14, pesoFonte = "normal")
            var isTextfieldFocused by remember { mutableStateOf(false) }

            TextFieldBordaGradienteAzul(
                isTextFieldFocused = isTextfieldFocused,
                texto = usuario.email.toString(),
                label = stringResource(R.string.text_email_label),
                onValueChanged = {usuarioSetter(usuario.copy(email = it.toString()))},
                onFocusChanged = {isTextfieldFocused = it.isFocused},
            )
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            TextoBranco(texto = stringResource(R.string.text_senha), tamanhoFonte = 14, pesoFonte = "normal")
            var isTextfieldFocused by remember { mutableStateOf(false) }

            TextFieldBordaGradienteAzul(
                isTextFieldFocused = isTextfieldFocused,
                texto = usuario.senha.toString(),
                label = "********",
                onValueChanged = {usuarioSetter(usuario.copy(senha = it.toString()))},
                onFocusChanged = {isTextfieldFocused = it.isFocused},
                )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .width(115.dp)
                .height(60.dp)
        ){
            CheckboxComGradiente(lembrar = lembrar, onCheckedChange = {lembrar = it})

            TextoBranco(texto = stringResource(R.string.text_lembrar), tamanhoFonte = 12, pesoFonte = "normal")
        }


        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )

        BotaoAzul(
            text = stringResource(R.string.text_entrar),
            onClick = {
                val telaHome = Intent(contexto, TelaHome::class.java)
                telaHome.putExtra("usuario", usuario)
                contexto.startActivity(telaHome)
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()

        ){
            TextoBranco(texto = stringResource(R.string.text_nao_tem_conta), tamanhoFonte = 12, pesoFonte = "normal")
            TextButton(
                onClick = {
                    val intent = Intent(contexto, TelaCadastro::class.java)
                    contexto.startActivity(intent)
                },) {
                TextoAzulGradienteSublinhado(texto = stringResource(R.string.text_cadastrar_se), tamanhoFonte = 12, pesoFonte = "normal")
            }
        }
    }



}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    CodeupTheme {
        Login("sada")
    }
}