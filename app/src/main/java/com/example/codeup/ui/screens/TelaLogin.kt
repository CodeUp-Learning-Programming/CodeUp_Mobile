package com.example.codeup.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.codeup.R
import com.example.codeup.data.UsuarioLoginRequest
import com.example.codeup.ui.composables.BotaoAzul
import com.example.codeup.ui.composables.CheckboxComGradiente
import com.example.codeup.ui.composables.TextFieldBordaGradienteAzul
import com.example.codeup.ui.composables.TextoAzulGradienteSublinhado
import com.example.codeup.ui.composables.TextoBranco
import com.example.codeup.ui.screens.viewmodels.UsuarioViewModel
import com.example.codeup.ui.theme.CodeupTheme
import com.example.codeup.util.StoreRememberUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TelaLogin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                0, 0
            ), navigationBarStyle = SystemBarStyle.light(
                0, 0
            )
        )

        setContent {
            CodeupTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = Color(13, 13, 13)
                ) {
                    Login()
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Login(usuarioViewModel: UsuarioViewModel = UsuarioViewModel(null)) {

    val (usuarioLoginRequest, usuarioLoginRequestSetter) = remember {
        mutableStateOf(UsuarioLoginRequest())
    }
    var lembrar by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = StoreRememberUser(context)

    val emailSalvo = dataStore.getEmail.collectAsState(initial = "")
    val senhaSalva = dataStore.getPassword.collectAsState(initial = "")

    if (emailSalvo.value!! != "" && senhaSalva.value!! != "") {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                usuarioViewModel.login(
                    UsuarioLoginRequest(emailSalvo.value!!, senhaSalva.value!!),
                    context,
                    scope,
                    dataStore,
                    true
                )
            } catch (e: Exception) {
                Log.e("LOGIN", "Erro! ${e.message}")
            }
        }
    }


    var emailInputValido by remember { mutableStateOf(false) }
    var senhaInputValido by remember { mutableStateOf(false) }


    val erroApi = remember { mutableStateOf("") }

    Column(
        Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            Modifier.padding(all = 20.dp), verticalArrangement = Arrangement.Top
        ) {

            Spacer(
                Modifier
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
                Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            )
            Column(Modifier.fillMaxWidth()) {
                TextoBranco(
                    texto = stringResource(R.string.text_email),
                    tamanhoFonte = 14,
                )
                var isTextfieldFocused by remember { mutableStateOf(false) }

                TextFieldBordaGradienteAzul(
                    modifier = Modifier.fillMaxWidth(),
                    isTextFieldFocused = isTextfieldFocused,
                    texto = usuarioLoginRequest.email,
                    label = stringResource(R.string.text_email_label),
                    onValueChange = { usuarioLoginRequestSetter(usuarioLoginRequest.copy(email = it)) },
                    onFocusChanged = {
                        isTextfieldFocused = it.isFocused
                        emailInputValido = false
                    },
                    dadoIncorreto = emailInputValido,
                    keyboardType = KeyboardType.Email
                )
            }

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

            Column(Modifier.fillMaxWidth()) {
                TextoBranco(
                    texto = stringResource(R.string.text_senha),
                    tamanhoFonte = 14,
                )
                var isTextfieldFocused by remember { mutableStateOf(false) }

                TextFieldBordaGradienteAzul(
                    modifier = Modifier.fillMaxWidth(),
                    isTextFieldFocused = isTextfieldFocused,
                    texto = usuarioLoginRequest.senha,
                    label = "Sua senha",
                    onValueChange = { usuarioLoginRequestSetter(usuarioLoginRequest.copy(senha = it)) },
                    onFocusChanged = {
                        isTextfieldFocused = it.isFocused
                        senhaInputValido = false
                    },
                    dadoIncorreto = senhaInputValido,
                    keyboardType = KeyboardType.Password
                )
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,

                ) {
                CheckboxComGradiente(lembrar = lembrar, onCheckedChange = { lembrar = it })

                Spacer(Modifier.width(10.dp))
                TextoBranco(
                    texto = stringResource(R.string.text_lembrar),
                    tamanhoFonte = 12,
                )
            }


            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

            BotaoAzul(
                text = stringResource(R.string.text_entrar), onClick = {
                    if (usuarioLoginRequest.email.isEmpty() || usuarioLoginRequest.senha.isEmpty()) {
                        emailInputValido = true
                        senhaInputValido = true

                    } else {
                        CoroutineScope(Dispatchers.IO).launch {
                            try {
                                usuarioViewModel.login(
                                    usuarioLoginRequest, context, scope, dataStore, lembrar
                                )
                            } catch (e: Exception) {
                                Log.e("LOGIN", "Erro! ${e.message}")
                            }
                        }
                    }
                }, modifier = Modifier.fillMaxWidth()
            )

            if (!erroApi.equals("")) {
                TextoBranco(
                    texto = erroApi.value,
                    tamanhoFonte = 12,
                )
            }

        }
        Column(
            Modifier.padding(bottom = 30.dp)
        ) {

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                TextoBranco(
                    texto = stringResource(R.string.text_nao_tem_conta),
                    tamanhoFonte = 12,
                )
                TextButton(
                    onClick = {
                        val intent = Intent(context, TelaCadastro::class.java)
                        context.startActivity(intent)
                    },
                ) {
                    TextoAzulGradienteSublinhado(
                        texto = stringResource(R.string.text_cadastrar_se),
                        tamanhoFonte = 12,
                        pesoFonte = "normal"
                    )
                }
            }

        }
    }

}
