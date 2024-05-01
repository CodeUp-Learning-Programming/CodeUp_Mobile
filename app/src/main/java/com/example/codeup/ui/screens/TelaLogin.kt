package com.example.codeup.ui.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

class TelaLogin : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(0, 0),
            navigationBarStyle = SystemBarStyle.light(0, 0)
        )
        window.decorView.apply {
            // Hide both the navigation bar and the status bar.
            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
            // a general rule, you should design your app to hide the status bar whenever you
            // hide the navigation bar.
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
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

    //ViewModel
    val carregando by usuarioViewModel.carregando.observeAsState(false)
    val loginStatus by usuarioViewModel.loginStatus.observeAsState()

    //DataStore
    val context = LocalContext.current
    val dataStore = StoreRememberUser(context)

    val emailSalvo = dataStore.getEmail.collectAsState(initial = "")
    val senhaSalva = dataStore.getPassword.collectAsState(initial = "")


    //Objeto Usuario
    val (usuarioLoginRequest, usuarioLoginRequestSetter) = remember {
        mutableStateOf(UsuarioLoginRequest())
    }


    var lembrar by remember { mutableStateOf(false) }
    var entrando by remember { mutableStateOf(false) }
    //Logando automaticamente caso o usuário tenha selecionado lembrar anteriormente
    if (emailSalvo.value!! != "" && senhaSalva.value!! != "" && !entrando) {
        entrando = true
        usuarioViewModel.login(
            UsuarioLoginRequest(emailSalvo.value!!, senhaSalva.value!!),
            context,
            dataStore,
            true
        )
    }

    var emailInputValido by remember { mutableStateOf(false) }
    var senhaInputValido by remember { mutableStateOf(false) }



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
                    label = stringResource(R.string.text_sua_senha),
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
                loading = carregando,
                text = stringResource(R.string.text_entrar), onClick = {
                    if (usuarioLoginRequest.email.isEmpty() || usuarioLoginRequest.senha.isEmpty()) {
                        emailInputValido = true
                        senhaInputValido = true

                    } else {
                        usuarioViewModel.login(usuarioLoginRequest, context, dataStore, lembrar)

                    }
                }, modifier = Modifier.fillMaxWidth()

            )

            loginStatus?.let {
                TextoBranco(it, 20)  // Mostrar o status do login
                Log.d("aaaaaa", loginStatus!!)
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
