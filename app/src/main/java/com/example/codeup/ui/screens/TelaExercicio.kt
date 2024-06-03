package com.example.codeup.ui.screens

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.codeup.R
import com.example.codeup.data.Exercicio
import com.example.codeup.data.PalavraConsole
import com.example.codeup.data.Usuario
import com.example.codeup.ui.OpcoesPergunta
import com.example.codeup.ui.composables.card.CardReporteEnviado
import com.example.codeup.ui.composables.componentes.RadioButtonCustomizado
import com.example.codeup.ui.composables.componentes.TextFieldBordaGradienteAzul
import com.example.codeup.ui.composables.componentes.TextOpcaoPergunta
import com.example.codeup.ui.composables.componentes.TextoBranco
import com.example.codeup.ui.composables.menu.MenuExercicio
import com.example.codeup.ui.composables.menu.MenuReporte
import com.example.codeup.ui.screens.ui.theme.CodeupTheme
import com.example.codeup.util.StoreExercicio
import com.example.codeup.util.StoreUser
import kotlinx.coroutines.launch

class TelaExercicio : ComponentActivity() {
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
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        setContent {
            CodeupTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ExercicioAtual()
                }
            }
        }
    }
}

@Composable
fun ExercicioAtual() {

    val context = LocalContext.current
    var usuario by remember { mutableStateOf<Usuario?>(null) }
    var listaExercicios by remember { mutableStateOf(emptyList<Exercicio>()) }

    val coroutineScope = rememberCoroutineScope()
    val storeUser = StoreUser.getInstance(context)
    val storeExercicio = StoreExercicio.getInstance(context)
    val scrollState = rememberScrollState()

    var reportar by remember { mutableStateOf(false) }
    var exercicioConsole by remember { mutableStateOf(false) }

    var enviarReportePasso1 by remember { mutableStateOf(false) }
    var enviarReporte by remember { mutableStateOf(false) }

    var respostaEscolhida by remember { mutableStateOf("") }

    var isTextfieldFocused by remember { mutableStateOf(false) }
    var validarResposta by remember { mutableStateOf(false) }
    var respostaCerta by remember { mutableStateOf(false) }

    val (showPopup, setShowPopup) = remember { mutableStateOf(false) }

    // Observe and collect user data from DataStore
    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            storeUser.getUsuario.collect { retrievedUser ->
                usuario = retrievedUser
            }
        }
    }
// Coleta de dados deve ser controlada por condições específicas
    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            storeExercicio.getExercicio.collect { exercicios ->
                listaExercicios = exercicios
            }
        }
    }



    exercicioConsole = true


    usuario?.let { it ->
        if (exercicioConsole) {
            //Exercicio de console
            MenuExercicio(totalCoracoes = it.vidas,
                onClickReportar = {
                    reportar = true
                },
                onClickValidarResposta = {
                    validarResposta = true
                },
                conteudo = {
                    val listaPalavras = remember {
                        mutableListOf(
                            OpcoesPergunta(texto = "<button/>"),
                            OpcoesPergunta(texto = "<div/>"),
                            OpcoesPergunta(texto = "<p/>"),
                            OpcoesPergunta(texto = "<span/>"),
                        )
                    }
                    var listaPalavrasConsole = remember {
                        mutableListOf(
                            PalavraConsole(1, "console.log(1"),
                            PalavraConsole(2, "<" ,true),
                            PalavraConsole(1, "90)")
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Column {
                            
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .verticalScroll(scrollState),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                TextoBranco(
//                                    texto = listaExercicios[0].conteudoTeorico.replace("^", "\n"),
                                    texto = "Qual sinal mostra que o 1 é menor que 90?",
                                    tamanhoFonte = 14,
                                    alinhamentoTexto = TextAlign.Justify,
                                )

                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        //Console
                        Column(Modifier
                            .fillMaxWidth()) {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .background(Color(21, 21, 21))
                                    .padding(all = 10.dp),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextoBranco(texto = stringResource(R.string.text_titulo_console), tamanhoFonte = 12)
                            }
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .background(Color(5, 5, 5))
                                    .padding(all = 10.dp)
                                    .height(300.dp),
                                horizontalArrangement = Arrangement.Start,
                            ){
                                LazyRow {
                                    items(listaPalavrasConsole) { item ->
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Start
                                            ) {
                                               if(item.espaco){
                                                   Box(Modifier.height(10.dp).width(10.dp).background(
                                                       Color(35,35,35)),
                                                       contentAlignment = Alignment.Center){

                                                   }
                                               }else{
                                                   TextoBranco(texto = item.palavra, tamanhoFonte = 12)
                                               }
                                            }
                                            Spacer(modifier = Modifier.height(10.dp))

                                    }

                                }
                                
                            }
                        }
                        //Fim do Console

                        Spacer(modifier = Modifier.height(10.dp))

                    }

                })
        }else{
            //Exercicio de perguntas
            MenuExercicio(totalCoracoes = it.vidas,
                onClickReportar = {
                    reportar = true
                },
                onClickValidarResposta = {
                    validarResposta = true
                },
                conteudo = {
                    val listaOpcoesPergunta = remember {
                        mutableListOf(
                            OpcoesPergunta(
                                texto = "<button/>"
                            ),
                            OpcoesPergunta(
                                texto = "<div/>"
                            ),
                            OpcoesPergunta(
                                texto = "<p/>"
                            ),
                            OpcoesPergunta(
                                texto = "<span/>"
                            ),
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color(40, 40, 40))
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(all = 10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Column(Modifier.height(250.dp)) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .verticalScroll(scrollState),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                TextoBranco(
                                    texto = listaExercicios[0].conteudoTeorico.replace("^", "\n"),
                                    tamanhoFonte = 14,
                                    alinhamentoTexto = TextAlign.Justify,
                                )

                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row {
                            TextoBranco(
                                texto = listaExercicios[0].desafio,
                                tamanhoFonte = 16,
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))


                        // Opções de escolha
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            reverseLayout = false
                        ) {
                            items(listaOpcoesPergunta) { opcao ->
                                Column {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        TextOpcaoPergunta(
                                            texto = opcao.texto,
                                            isSelected = respostaEscolhida == opcao.texto,
                                            onOptionSelected = { respostaEscolhida = opcao.texto },
                                            respostaCerta = if(opcao.texto == "<button/>") true else false,
                                            validarResposta = validarResposta
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                }
                            }

                        }

                    }

                })
        }


        if (reportar) {
            MenuReporte(
                onClickReporte = {
                    if (!enviarReportePasso1) {
                        enviarReportePasso1 = true
                    } else {
                        setShowPopup(true)
                    }
                },
                conteudo = {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(top = 50.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        TextoBranco(
                            texto = stringResource(R.string.text_reporte_exercicio),
                            tamanhoFonte = 24
                        )

                        Spacer(Modifier.height(30.dp))
                        if (!enviarReportePasso1) {
                            Column {
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(start = 15.dp, end = 15.dp)
                                ) {
                                    RadioButtonCustomizado(
                                        listOf(
                                            stringResource(id = R.string.text_resposta_errada),
                                            stringResource(id = R.string.text_erro_digitacao),
                                            stringResource(id = R.string.text_questao_confusa),
                                            stringResource(id = R.string.text_algo_errado),
                                        )
                                    )
                                }
                            }
                        } else {
                            var text by remember { mutableStateOf("") }
                            Column {
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(start = 15.dp, end = 15.dp)
                                ) {
                                    TextFieldBordaGradienteAzul(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 200.dp),
                                        isTextFieldFocused = isTextfieldFocused,
                                        texto = text,
                                        label = stringResource(R.string.text_explique_problema),
                                        onValueChange = { text = it },
                                        onFocusChanged = {
                                            isTextfieldFocused = it.isFocused
                                        }
                                    )
                                }
                            }
                        }
                    }

                }, onClickSair = {
                    reportar = false
                })
        }


        if (showPopup) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { setShowPopup(false) }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {})
                ) {
                    CardReporteEnviado(
                        onClickFechar = {
                            setShowPopup(false)
                            reportar = false
                        },
                    )
                }
            }
        }
    }


}

