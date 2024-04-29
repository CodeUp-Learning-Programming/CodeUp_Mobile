package com.example.codeup.ui.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.codeup.data.UsuarioRegisterRequest
import com.example.codeup.ui.composables.BotaoAzul
import com.example.codeup.ui.composables.TextFieldBordaGradienteAzul
import com.example.codeup.ui.composables.TextoAzulGradienteSublinhado
import com.example.codeup.ui.composables.TextoBranco
import com.example.codeup.ui.screens.viewmodels.UsuarioViewModel
import com.example.codeup.ui.theme.CodeupTheme
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class TelaCadastro : ComponentActivity() {
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
                    Cadastro()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro(usuarioViewModel: UsuarioViewModel = UsuarioViewModel(null)) {
    val contexto = LocalContext.current
    val (usuario, usuarioSetter) = remember {
        mutableStateOf(UsuarioRegisterRequest())
    }

    var emailInputValido by remember { mutableStateOf(false) }
    var senhaInputValido by remember { mutableStateOf(false) }
    var dtNascimentoInputValido by remember { mutableStateOf(false) }
    var nomeInputValido by remember { mutableStateOf(false) }



    val erroApi = remember { mutableStateOf("") }

    val calendarState = rememberSheetState()
    val (dtNascimento, setDtNascimento) = remember { mutableStateOf("") }


    CalendarDialog(state = calendarState, config = CalendarConfig(
        monthSelection = true, yearSelection = true
    ), selection = CalendarSelection.Date { date ->
        usuarioSetter(usuario.copy(dtNascimento = date.toString()))
        setDtNascimento(date.toString().toBrazilianDateFormat())
    })

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            Modifier.padding(all = 20.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp))
            Row {
                TextoBranco(
                    texto = stringResource(R.string.text_cadastrar_se),
                    tamanhoFonte = 36,
                    pesoFonte = "Titulo"
                )
            }
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(40.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                TextoBranco(
                    texto = stringResource(R.string.text_nome),
                    tamanhoFonte = 14,
                )
                var isTextfieldFocused by remember { mutableStateOf(false) }

                TextFieldBordaGradienteAzul(
                    modifier = Modifier.fillMaxWidth(),
                    isTextFieldFocused = isTextfieldFocused,
                    texto = usuario.nome,
                    label = stringResource(R.string.text_nome_label),
                    onValueChange = { usuarioSetter(usuario.copy(nome = it)) },
                    onFocusChanged = { isTextfieldFocused = it.isFocused },
                )
            }

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(20.dp))

            Column(Modifier.fillMaxWidth()) {
                TextoBranco(
                    texto = stringResource(R.string.text_data_nascimento),
                    tamanhoFonte = 14,
                )
                var isTextfieldFocused by remember { mutableStateOf(false) }


                TextFieldBordaGradienteAzul(
                    modifier = Modifier
                        .clickable { calendarState.show() }
                        .fillMaxWidth(),
                    isTextFieldFocused = isTextfieldFocused,
                    texto = dtNascimento,
                    label = stringResource(R.string.text_data_nascimento),
                    onValueChange = { },
                    onFocusChanged = { isTextfieldFocused = it.isFocused },
                    keyboardType = KeyboardType.Text,
                    enabled = false,
                )
            }

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(20.dp))

            Column(Modifier.fillMaxWidth()) {
                TextoBranco(
                    texto = stringResource(R.string.text_email),
                    tamanhoFonte = 14,
                )
                var isTextfieldFocused by remember { mutableStateOf(false) }

                TextFieldBordaGradienteAzul(
                    modifier = Modifier.fillMaxWidth(),
                    isTextFieldFocused = isTextfieldFocused,
                    texto = usuario.email,
                    label = stringResource(R.string.text_email_label),
                    onValueChange = { usuarioSetter(usuario.copy(email = it)) },
                    onFocusChanged = { isTextfieldFocused = it.isFocused },
                    keyboardType = KeyboardType.Email
                )
            }

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(20.dp))

            Column(Modifier.fillMaxWidth()) {
                TextoBranco(
                    texto = stringResource(R.string.text_senha),
                    tamanhoFonte = 14
                )
                var isTextfieldFocused by remember { mutableStateOf(false) }

                TextFieldBordaGradienteAzul(
                    modifier = Modifier.fillMaxWidth(),
                    isTextFieldFocused = isTextfieldFocused,
                    texto = usuario.senha,
                    label = stringResource(R.string.text_sua_senha),
                    onValueChange = { usuarioSetter(usuario.copy(senha = it)) },
                    onFocusChanged = { isTextfieldFocused = it.isFocused },
                    keyboardType = KeyboardType.Password
                )
            }

            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(20.dp))

            BotaoAzul(
                text = stringResource(R.string.text_cadastrar), onClick = {
                    if (usuario.email.isEmpty() || usuario.senha.isEmpty() || usuario.nome.isEmpty() || usuario.dtNascimento.isEmpty()) {
                        emailInputValido = true
                        senhaInputValido = true
                        nomeInputValido = true
                        dtNascimentoInputValido = true
                    } else {
                            CoroutineScope(Dispatchers.IO).launch {
                                try {
                                    usuarioViewModel.cadastrar(usuario, contexto)
                                } catch (e: Exception) {
                                    Log.e("Cadastro", "Erro ao cadastrar usuário! ${e.message}")
                                }
                            }
                    }

                }, modifier = Modifier.fillMaxWidth()
            )
        }

        Column(Modifier.padding(bottom = 30.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {


                TextoBranco(
                    texto = stringResource(R.string.text_ja_tem_conta),
                    tamanhoFonte = 12,
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

fun String.toBrazilianDateFormat(inputPattern: String = "yyyy-MM-dd", outputPattern: String = "dd/MM/yyyy"): String {
    return try {
        val inputFormat = SimpleDateFormat(inputPattern, Locale.getDefault())
        val date = inputFormat.parse(this)
        val outputFormat = SimpleDateFormat(outputPattern, Locale.getDefault())
        outputFormat.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
        "Formato de data inválidoLLL"
    }
}
