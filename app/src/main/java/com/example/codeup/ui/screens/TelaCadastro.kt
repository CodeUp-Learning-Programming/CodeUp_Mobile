package com.example.codeup.ui.screens

import android.content.Intent
import android.os.Bundle
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.codeup.R
import com.example.codeup.api.RetrofitService
import com.example.codeup.data.Usuario
import com.example.codeup.data.UsuarioRegisterRequest
import com.example.codeup.ui.composables.BotaoAzul
import com.example.codeup.ui.composables.TextFieldBordaGradienteAzul
import com.example.codeup.ui.composables.TextoAzulGradienteSublinhado
import com.example.codeup.ui.composables.TextoBranco
import com.example.codeup.ui.theme.CodeupTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaCadastro : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                0, 0
            ),
            navigationBarStyle = SystemBarStyle.light(
                0, 0
            )
        )
        setContent {
            CodeupTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = Color(13, 13, 13)
                ) {
                    Cadastro("Android")
                }
            }
        }
    }
}

@Composable
fun Cadastro(name: String, modifier: Modifier = Modifier) {
    val contexto = LocalContext.current
    val (usuario, usuarioSetter) = remember {
        mutableStateOf(UsuarioRegisterRequest())
    }

    var emailInputValido by remember { mutableStateOf(false) }
    var senhaInputValido by remember { mutableStateOf(false) }
    var dtNascimentoInputValido by remember { mutableStateOf(false) }
    var nomeInputValido by remember { mutableStateOf(false) }


    val erroApi = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .padding(all = 20.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            )
            Row {
                TextoBranco(
                    texto = stringResource(R.string.text_cadastrar_se),
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
                TextoBranco(
                    texto = stringResource(R.string.text_nome),
                    tamanhoFonte = 14,
                )
                var isTextfieldFocused by remember { mutableStateOf(false) }

                TextFieldBordaGradienteAzul(
                    isTextFieldFocused = isTextfieldFocused,
                    texto = usuario.nome.toString(),
                    label = stringResource(R.string.text_nome_label),
                    onValueChange = { usuarioSetter(usuario.copy(nome = it.toString())) },
                    onFocusChanged = { isTextfieldFocused = it.isFocused },
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                TextoBranco(
                    texto = stringResource(R.string.text_data_nascimento),
                    tamanhoFonte = 14,
                )
                var isTextfieldFocused by remember { mutableStateOf(false) }

                TextFieldBordaGradienteAzul(
                    isTextFieldFocused = isTextfieldFocused,
                    texto = usuario.dtNascimento.toString(),
                    label = stringResource(R.string.text_data_label),
                    onValueChange = { usuarioSetter(usuario.copy(dtNascimento = it.toString())) },
                    onFocusChanged = { isTextfieldFocused = it.isFocused },
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                TextoBranco(
                    texto = stringResource(R.string.text_email),
                    tamanhoFonte = 14,
                )
                var isTextfieldFocused by remember { mutableStateOf(false) }

                TextFieldBordaGradienteAzul(
                    isTextFieldFocused = isTextfieldFocused,
                    texto = usuario.email.toString(),
                    label = stringResource(R.string.text_email_label),
                    onValueChange = { usuarioSetter(usuario.copy(email = it.toString())) },
                    onFocusChanged = { isTextfieldFocused = it.isFocused },
                    keyboardType = KeyboardType.Email
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                TextoBranco(
                    texto = stringResource(R.string.text_senha),
                    tamanhoFonte = 14,
                    pesoFonte = "normal"
                )
                var isTextfieldFocused by remember { mutableStateOf(false) }

                TextFieldBordaGradienteAzul(
                    isTextFieldFocused = isTextfieldFocused,
                    texto = usuario.senha.toString(),
                    label = "********",
                    onValueChange = { usuarioSetter(usuario.copy(senha = it.toString())) },
                    onFocusChanged = { isTextfieldFocused = it.isFocused },
                    keyboardType = KeyboardType.Password
                )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

            BotaoAzul(
                text = stringResource(R.string.text_cadastrar),
                onClick = {

                    if (usuario.email.isEmpty() || usuario.senha.isEmpty()) {
                        emailInputValido = true
                        senhaInputValido = true
                        nomeInputValido = true
                        dtNascimentoInputValido = true
                    } else {
                        val ApiUsuarios = RetrofitService.getApiUsuarioService(null)
                        val post = ApiUsuarios.cadastrar(usuario);

                        post.enqueue(object : Callback<Usuario> {
                            override fun onResponse(
                                call: Call<Usuario>,
                                response: Response<Usuario>
                            ) {
                                if (response.isSuccessful) {
                                    val usuarioResponse = response.body()
                                    if (usuarioResponse != null) {
                                        val telaLogin = Intent(contexto, TelaLogin::class.java)
                                        contexto.startActivity(telaLogin)
                                    }
                                }
                            }

                            override fun onFailure(call: Call<Usuario>, t: Throwable) {

                            }
                        })

                    }

                },
                modifier = Modifier.fillMaxWidth()
            )
        }
        Column(
            modifier = Modifier
                .padding(bottom = 30.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {


                TextoBranco(
                    texto = stringResource(R.string.text_ja_tem_conta),
                    tamanhoFonte = 12,
                    pesoFonte = "normal"
                )
                TextButton(
                    onClick = {
                        val intent = Intent(contexto, TelaLogin::class.java)
                        contexto.startActivity(intent)
                    },
                ) {
                    TextoAzulGradienteSublinhado(
                        texto = stringResource(R.string.text_entrar),
                        tamanhoFonte = 12,
                        pesoFonte = "normal"
                    )
                }

            }

        }
    }
}

