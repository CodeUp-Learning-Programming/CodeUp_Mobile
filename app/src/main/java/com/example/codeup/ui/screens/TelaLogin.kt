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
import androidx.compose.ui.unit.dp
import com.example.codeup.R
import com.example.codeup.api.RetrofitService
import com.example.codeup.data.Usuario
import com.example.codeup.data.UsuarioLoginRequest
import com.example.codeup.ui.composables.BotaoAzul
import com.example.codeup.ui.composables.CheckboxComGradiente
import com.example.codeup.ui.composables.TextFieldBordaGradienteAzul
import com.example.codeup.ui.composables.TextoAzulGradienteSublinhado
import com.example.codeup.ui.composables.TextoBranco
import com.example.codeup.ui.theme.CodeupTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaLogin : ComponentActivity() {
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
                    Login("extras")
                }
            }
        }
    }
}

@Composable
fun Login(name: String, modifier: Modifier = Modifier) {

    var lembrar by remember { mutableStateOf(false) }
    val contexto = LocalContext.current
    val (usuarioLoginRequest, usuarioLoginRequestSetter) = remember {
        mutableStateOf(UsuarioLoginRequest())
    }
    val (usuario, usuarioSetter) = remember {
        mutableStateOf(Usuario())
    }

    val erroApi = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
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
            TextoBranco(
                texto = stringResource(R.string.text_email),
                tamanhoFonte = 14,
                pesoFonte = "normal"
            )
            var isTextfieldFocused by remember { mutableStateOf(false) }

            TextFieldBordaGradienteAzul(
                isTextFieldFocused = isTextfieldFocused,
                texto = usuarioLoginRequest.email,
                label = stringResource(R.string.text_email_label),
                onValueChanged = { usuarioLoginRequestSetter(usuarioLoginRequest.copy(email = it)) },
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
                texto = stringResource(R.string.text_senha),
                tamanhoFonte = 14,
                pesoFonte = "normal"
            )
            var isTextfieldFocused by remember { mutableStateOf(false) }

            TextFieldBordaGradienteAzul(
                isTextFieldFocused = isTextfieldFocused,
                texto = usuarioLoginRequest.senha,
                label = "********",
                onValueChanged = { usuarioLoginRequestSetter(usuarioLoginRequest.copy(senha = it)) },
                onFocusChanged = { isTextfieldFocused = it.isFocused },
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .width(115.dp)
                .height(60.dp)
        ) {
            CheckboxComGradiente(lembrar = lembrar, onCheckedChange = { lembrar = it })

            TextoBranco(
                texto = stringResource(R.string.text_lembrar),
                tamanhoFonte = 12,
                pesoFonte = "normal"
            )
        }


        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )

        BotaoAzul(
            text = stringResource(R.string.text_entrar),
            onClick = {
                val ApiUsuarios = RetrofitService.getApiUsuarioService(null)
                val post = ApiUsuarios.login(usuarioLoginRequest);

                post.enqueue(object : Callback<Usuario> {
                    override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                        if (response.isSuccessful) {
                            val usuarioResponse = response.body()
                            if (usuarioResponse != null) {
                                usuarioSetter(usuario.copy(
                                    id = usuarioResponse.id,
                                    fotoPerfil = usuarioResponse.fotoPerfil,
                                    nome = usuarioResponse.nome,
                                    token = usuarioResponse.token,
                                    email = usuarioResponse.email,
                                    moedas = usuarioResponse.moedas,
                                    nivel = usuarioResponse.nivel,
                                    xp = usuarioResponse.xp,
                                    itensAdquiridos = usuarioResponse.itensAdquiridos
                                ))

                                val telaHome = Intent(contexto, TelaHome::class.java)
                                telaHome.putExtra("usuario", usuario)
                                contexto.startActivity(telaHome)


                            } else {
                                erroApi.value = "Erro: Usuário não encontrado"
                            }
                        } else {
                            erroApi.value = "Erro na resposta: ${response.code()}"
                        }
                    }

                    override fun onFailure(call: Call<Usuario>, t: Throwable) {
                        erroApi.value = t.message.toString()
                    }
                })

            },
            modifier = Modifier.fillMaxWidth()
        )
        if(!erroApi.equals("")){
            TextoBranco(
                texto = erroApi.value,
                tamanhoFonte = 12,
                pesoFonte = "normal")
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(268.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            TextoBranco(
                texto = stringResource(R.string.text_nao_tem_conta),
                tamanhoFonte = 12,
                pesoFonte = "normal"
            )
            TextButton(
                onClick = {
                    val intent = Intent(contexto, TelaCadastro::class.java)
                    contexto.startActivity(intent)
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
