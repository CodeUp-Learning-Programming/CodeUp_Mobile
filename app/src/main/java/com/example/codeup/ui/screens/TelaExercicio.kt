package com.example.codeup.ui.screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.lazy.LazyColumn
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
import co.yml.charts.common.extensions.isNotNull
import com.example.codeup.R
import com.example.codeup.data.Exercicio
import com.example.codeup.data.Fase
import com.example.codeup.data.Usuario
import com.example.codeup.ui.OpcoesPergunta
import com.example.codeup.ui.composables.card.CardConclusaoExercicio
import com.example.codeup.ui.composables.card.CardReporteEnviado
import com.example.codeup.ui.composables.componentes.RadioButtonCustomizado
import com.example.codeup.ui.composables.componentes.TextFieldBordaGradienteAzul
import com.example.codeup.ui.composables.componentes.TextOpcaoPergunta
import com.example.codeup.ui.composables.componentes.TextoBranco
import com.example.codeup.ui.composables.menu.MenuExercicio
import com.example.codeup.ui.composables.menu.MenuReporte
import com.example.codeup.ui.screens.ui.theme.CodeupTheme
import com.example.codeup.ui.screens.viewmodels.ExercicioViewModel
import com.example.codeup.util.StoreExercicio
import com.example.codeup.util.StoreFase
import com.example.codeup.util.StoreUser
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TelaExercicio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
    var respostaEscolhida by remember { mutableStateOf<OpcoesPergunta?>(null) }
    var faseAtual by remember { mutableStateOf<Fase?>(null) }
    var listaExercicios by remember { mutableStateOf(emptyList<Exercicio>()) }

    val coroutineScope = rememberCoroutineScope()
    val storeUser = StoreUser.getInstance(context)
    val storeExercicio = StoreExercicio.getInstance(context)
    val storeFase = StoreFase.getInstance(context)
    val scrollState = rememberScrollState()

    var reportar by remember { mutableStateOf(false) }
    var exercicioConsole by remember { mutableStateOf(false) }

    var enviarReportePasso1 by remember { mutableStateOf(false) }
    var enviarReporte by remember { mutableStateOf(false) }


    var isTextfieldFocused by remember { mutableStateOf(false) }
    var validarResposta by remember { mutableStateOf(false) }
    var respostaCerta by remember { mutableStateOf(false) }

    val (showPopup, setShowPopup) = remember { mutableStateOf(false) }
    var (showPopupAcerto, setShowPopupAcerto) = remember { mutableStateOf(false) }

    // Observe and collect user data from DataStore
    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            storeUser.getUsuario.collect { retrievedUser ->
                usuario = retrievedUser
            }
        }
        coroutineScope.launch {
            storeExercicio.getExercicio.collect { exercicios ->
                listaExercicios = exercicios
            }
        }
        coroutineScope.launch {
            storeFase.getFaseAtual.collect { fase ->
                faseAtual = fase
            }
        }
    }

//    exercicioConsole = true

Log.d("EXERCICIO", usuario.toString())
Log.d("EXERCICIO", listaExercicios.toString())
Log.d("EXERCICIO", faseAtual.toString())
    usuario?.let { it ->
        if (listaExercicios.isNotNull() && faseAtual.isNotNull()) {
//           if (exercicioConsole) {
            //Exercicio de console
//               MenuExercicio(totalCoracoes = it.vidas,
//                   onClickReportar = {
//                       reportar = true
//                   },
//                   onClickValidarResposta = {
//                       if(respostaEscolhida != null){
//                           val exercicioViewModel = ExercicioViewModel(usuario!!.token)
//                           exercicioViewModel.testJavaScriptCode(respostaEscolhida!!.respostaEnviar, idExercicio = listaExercicios[0].id, faseAtual!!.faseId, context)
//
//                           validarResposta = true
//                       }
//                   },
//                   conteudo = {
////                    val listaPalavras = remember {
////                        mutableListOf(
////                            OpcoesPergunta(texto = "<button/>"),
////                            OpcoesPergunta(texto = "<div/>"),
////                            OpcoesPergunta(texto = "<p/>"),
////                            OpcoesPergunta(texto = "<span/>"),
////                        )
////                    }
//                       var listaPalavrasConsole = remember {
//                           mutableListOf(
//                               PalavraConsole(1, "console.log(1"),
//                               PalavraConsole(2, "<" ,true),
//                               PalavraConsole(1, "90)")
//                           )
//                       }
//                       Column(
//                           modifier = Modifier
//                               .fillMaxSize(),
//                           verticalArrangement = Arrangement.Top,
//                           horizontalAlignment = Alignment.CenterHorizontally,
//                       ) {
//                           Column {
//
//                               Row(
//                                   modifier = Modifier
//                                       .fillMaxWidth()
//                                       .verticalScroll(scrollState),
//                                   verticalAlignment = Alignment.CenterVertically,
//                                   horizontalArrangement = Arrangement.Center
//                               ) {
//                                   TextoBranco(
////                                    texto = listaExercicios[0].conteudoTeorico.replace("^", "\n"),
//                                       texto = "Qual sinal mostra que o 1 é menor que 90?",
//                                       tamanhoFonte = 14,
//                                       alinhamentoTexto = TextAlign.Justify,
//                                   )
//
//                               }
//                           }
//
//                           Spacer(modifier = Modifier.height(10.dp))
//
//                           //Console
//                           Column(Modifier
//                               .fillMaxWidth()) {
//                               Row(
//                                   Modifier
//                                       .fillMaxWidth()
//                                       .background(Color(21, 21, 21))
//                                       .padding(all = 10.dp),
//                                   horizontalArrangement = Arrangement.Start,
//                                   verticalAlignment = Alignment.CenterVertically
//                               ) {
//                                   TextoBranco(texto = stringResource(R.string.text_titulo_console), tamanhoFonte = 12)
//                               }
//                               Row(
//                                   Modifier
//                                       .fillMaxWidth()
//                                       .background(Color(5, 5, 5))
//                                       .padding(all = 10.dp)
//                                       .height(300.dp),
//                                   horizontalArrangement = Arrangement.Start,
//                               ){
//                                   LazyRow {
//                                       items(listaPalavrasConsole) { item ->
//                                           Row(
//                                               modifier = Modifier
//                                                   .fillMaxWidth(),
//                                               verticalAlignment = Alignment.CenterVertically,
//                                               horizontalArrangement = Arrangement.Start
//                                           ) {
//                                               if(item.espaco){
//                                                   Box(Modifier.height(10.dp).width(10.dp).background(
//                                                       Color(35,35,35)),
//                                                       contentAlignment = Alignment.Center){
//
//                                                   }
//                                               }else{
//                                                   TextoBranco(texto = item.palavra, tamanhoFonte = 12)
//                                               }
//                                           }
//                                           Spacer(modifier = Modifier.height(10.dp))
//
//                                       }
//
//                                   }
//
//                               }
//                           }
//                           //Fim do Console
//
//                           Spacer(modifier = Modifier.height(10.dp))
//
//                       }
//
//                   })
//           }else{
            //Exercicio de perguntas
            MenuExercicio(
                loading = validarResposta,
                totalCoracoes = it.vidas,
                onClickReportar = {
                    reportar = true
                },
                onClickValidarResposta = {
                    if (respostaEscolhida != null) {
                        validarResposta = true
                        val exercicioViewModel = ExercicioViewModel(usuario!!.token)
                        exercicioViewModel.testJavaScriptCode(
                            respostaEscolhida!!.respostaEnviar,
                            idExercicio = listaExercicios[faseAtual!!.qtdExerciciosFaseConcluidos].id,
                            faseAtual!!.faseId,
                            context
                        )
                        Log.d("POPUP", respostaEscolhida.toString())
                        coroutineScope.launch {
                            delay(2500)
                            validarResposta = false
                        }
                        if(respostaEscolhida!!.respostaCorreta){
                            setShowPopupAcerto(true)

                        }

                    }
                },
                conteudo = {
                    val listaOpcoesPergunta = remember {
                        mutableListOf(
                            OpcoesPergunta(
                                idFase = 1,
                                idExercicio = 1,
                                texto = "var texto = 'Olá mundo!';",
                                respostaCorreta = true,
                                respostaEnviar = "function primeiraVariavel() {var texto = \"aaa\"; return verificarTexto(texto);}function verificarTexto(texto) {if (texto.length > 0) {return texto;} else {return false;}} primeiraVariavel()"
                            ),
                            OpcoesPergunta(
                                idFase = 1,
                                idExercicio = 1,
                                texto = "var text = 'Olá mundo!';"
                            ),
                            OpcoesPergunta(
                                idFase = 1,
                                idExercicio = 1,
                                texto = "var texto : 'Olá mundo!';"
                            ),
                            OpcoesPergunta(
                                idFase = 1,
                                idExercicio = 1,
                                texto = "var texto = 'Olá mundo!';P"
                            ),
                            //soma
                            OpcoesPergunta(
                                idFase = 2,
                                idExercicio = 1,
                                texto = "var soma = n1 + n2;",
                                respostaCorreta = true,
                                respostaEnviar = "function operadoresNumericos() {var n1 = 2;\nvar n2 = 2;\nvar soma = n1 + n2; if(soma > 1){return soma}else{return false}}; operadoresNumericos()"
                            ),
                            OpcoesPergunta(
                                idFase = 2,
                                idExercicio = 1,
                                texto = "var soma = n1 / n2;"
                            ),
                            OpcoesPergunta(
                                idFase = 2,
                                idExercicio = 1,
                                texto = "var soma = n1 - n2;"
                            ),
                            OpcoesPergunta(
                                idFase = 2,
                                idExercicio = 1,
                                texto = "var soma = n1 * n2;"
                            ),
                            //subtração
                            OpcoesPergunta(
                                idFase = 2,
                                idExercicio = 2,
                                texto = "var subtracao = n1 + n2;"
                            ),
                            OpcoesPergunta(
                                idFase = 2,
                                idExercicio = 2,
                                texto = "var subtracao = n1 / n2;"
                            ),
                            OpcoesPergunta(
                                idFase = 2,
                                idExercicio = 2,
                                texto = "var subtracao = n1 - n2;",
                                respostaCorreta = true,
                                respostaEnviar = "function operadoresNumericos() {var n1 = 2;\nvar n2 = 2;\nvar subtracao = n1 - n2; if( subtracao < 1){return subtracao}else{return false}}; operadoresNumericos()"
                            ),
                            OpcoesPergunta(
                                idFase = 2,
                                idExercicio = 2,
                                texto = "var subtracao = n1 * n2;"
                            ),
                            //multiplicacao
                            OpcoesPergunta(
                                idFase = 2,
                                idExercicio = 3,
                                texto = "var multiplicacao = n1 + n2;"
                            ),
                            OpcoesPergunta(
                                idFase = 2,
                                idExercicio = 3,
                                texto = "var multiplicacao = n1 / n2;"
                            ),
                            OpcoesPergunta(
                                idFase = 2,
                                idExercicio = 3,
                                texto = "var multiplicacao = n1 - n2;",
                            ),
                            OpcoesPergunta(
                                idFase = 2,
                                idExercicio = 3,
                                texto = "var multiplicacao = n1 * n2;",
                                respostaCorreta = true,
                                respostaEnviar = "function operadoresNumericos() {var n1 = 2;\nvar n2 = 2;\nvar multiplicacao = n1 * n2; if( multiplicacao > 1){return multiplicacao}else{return multiplicacao}}; operadoresNumericos()\n"
                            ),
                            //divisao
                            OpcoesPergunta(
                                idFase = 2,
                                idExercicio = 4,
                                texto = "var divisao = n1 + n2;"
                            ),
                            OpcoesPergunta(
                                idFase = 2,
                                idExercicio = 4,
                                texto = "var divisao = n1 / n2;",
                                respostaCorreta = true,
                                respostaEnviar = "function operadoresNumericos() {var n1 = 2;\n" +
                                        "var n2 = 2;\n" +
                                        "var divisao = n1 / n2; if(divisao == 0){return divisao}else{return divisao}}; operadoresNumericos()"
                            ),
                            OpcoesPergunta(
                                idFase = 2,
                                idExercicio = 4,
                                texto = "var divisao = n1 - n2;"
                            ),
                            OpcoesPergunta(
                                idFase = 2,
                                idExercicio = 4,
                                texto = "var divisao = n1 * n2;"
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
                                    texto = listaExercicios[if (faseAtual!!.qtdExerciciosFaseConcluidos < faseAtual!!.qtdExerciciosFase) faseAtual!!.qtdExerciciosFaseConcluidos else 0].conteudoTeorico.replace(
                                        "^",
                                        "\n"
                                    ),
                                    tamanhoFonte = 14,
                                    alinhamentoTexto = TextAlign.Justify,
                                )

                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Row {
                            TextoBranco(
                                texto = listaExercicios[if (faseAtual!!.qtdExerciciosFaseConcluidos < faseAtual!!.qtdExerciciosFase) faseAtual!!.qtdExerciciosFaseConcluidos else 0].desafio,
                                tamanhoFonte = 16,
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        var selecionado by remember { mutableStateOf(false) }


                        // Opções de escolha
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            reverseLayout = false
                        ) {
                            items(listaOpcoesPergunta) { opcao ->


                                if (opcao.idExercicio == faseAtual!!.qtdExerciciosFaseConcluidos + 1 && opcao.idFase == faseAtual!!.faseId) {
                                    Column {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            TextOpcaoPergunta(
                                                texto = opcao.texto,
                                                isSelected = respostaEscolhida == opcao,
                                                onOptionSelected = { respostaEscolhida = opcao },
                                                respostaCerta = opcao.respostaCorreta,
                                                validarResposta = validarResposta
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(10.dp))
                                    }
                                }
                            }

                        }

                    }

                })
//           }


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


            if (showPopupAcerto) {
                val interactionSource = remember { MutableInteractionSource() }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = 0.5f))
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = { setShowPopupAcerto(false) }
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color(13, 13, 13).copy(alpha = 0.2f))
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = null,
                                        onClick = {}
                                    ),

                                contentAlignment = Alignment.Center
                            ) {
                                CardConclusaoExercicio(listaExercicios[faseAtual!!.qtdExerciciosFaseConcluidos].moeda,listaExercicios[faseAtual!!.qtdExerciciosFaseConcluidos].xp, onClickVoltar = {
                                    val telaHome = Intent(context, TelaHome::class.java)
                                    context.startActivity(telaHome)
                                })
                            }
                        }





            }
        }

    }


}

