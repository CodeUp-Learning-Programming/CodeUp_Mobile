package com.example.codeup.ui.screens

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.codeup.R
import com.example.codeup.api.RetrofitService
import com.example.codeup.data.Fase
import com.example.codeup.data.Usuario
import com.example.codeup.ui.DadosDoCard
import com.example.codeup.ui.composables.CardExercicio
import com.example.codeup.ui.composables.CardPopup
import com.example.codeup.ui.composables.Menu
import com.example.codeup.ui.theme.CodeupTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaHome : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val extras = intent.extras
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
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Home(extras = extras)

                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun Home(
    name: String = "a",
    fundo: String = "tema_padrao",
    extras: Bundle?,
    modifier: Modifier = Modifier
) {

    val user = extras?.getSerializable("usuario") as? Usuario
    val erroApi = remember { mutableStateOf("") }
    val ApiUsuarios = RetrofitService.getApiFaseService(user?.token)
    val get = ApiUsuarios.buscarFasePelaMateria(1);
    var listaExercicios by remember { mutableStateOf<List<DadosDoCard>>(emptyList()) }

    get.enqueue(object : Callback<List<Fase>> {
        override fun onResponse(call: Call<List<Fase>>, response: Response<List<Fase>>) {
            if (response.isSuccessful) {
                val faseResponse = response.body()
                if (faseResponse != null) {
                    listaExercicios = faseResponse.map { fase ->
                        DadosDoCard(
                            desbloqueada = fase.desbloqueada,
                            qtdExerciciosFase = fase.qtdExerciciosFase,
                            qtdExerciciosFaseConcluidos = fase.qtdExerciciosFaseConcluidos
                        )
                    }
                } else {
                    erroApi.value = "Erro: Usuário não encontrado"
                }
            } else {
                erroApi.value = "Erro na resposta: ${response.code()}"
            }
        }

        override fun onFailure(call: Call<List<Fase>>, t: Throwable) {
            erroApi.value = t.message.toString()
            var listaExerciciosMock = listOf(
                DadosDoCard(
                    desbloqueada = false,
                    qtdExerciciosFase = 5,
                    qtdExerciciosFaseConcluidos = 5
                ),
                DadosDoCard(
                    desbloqueada = false,
                    qtdExerciciosFase = 5,
                    qtdExerciciosFaseConcluidos = 3
                ),
            )
        }
    })

    if (user != null) {
        Menu(
            "${R.drawable.tema_pontos}",
            user.nome,
            totalCoracoes = 5,
            totalMoedas = user.moedas,
            totalSequencia = 5,
            conteudo = {

                // Variável de estado para controlar a visibilidade do pop-up
                val (showPopup, setShowPopup) = remember { mutableStateOf(false) }
                // Índice do card selecionado
                var selectedCardIndex by remember { mutableStateOf(-1) }

                var i = 0;
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    reverseLayout = false
                ) {
                    var alinharDireita = true;
                    items(listaExercicios) { exercicio ->
                        Column {
                            //Linha reta
                            Canvas(
                                modifier = Modifier
                                    .fillMaxHeight()
                            ) {
                                drawLine(
                                    color = Color.Gray,
                                    start = Offset(300f, 0f),
                                    end = Offset(300f, 400f),
                                    strokeWidth = 50f
                                )
                                drawLine(
                                    color = Color.Black,
                                    start = Offset(300f, 0f),
                                    end = Offset(300f, 400f),
                                    strokeWidth = 40f
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .width(200.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = if (alinharDireita) Arrangement.End else Arrangement.Start
                            ) {
                                CardExercicio(
                                    desbloqueada = exercicio.desbloqueada,
                                    qtdExerciciosFase = exercicio.qtdExerciciosFase,
                                    qtdExerciciosFaseConcluidos = exercicio.qtdExerciciosFaseConcluidos,
                                    onClick = {
                                        //exibir card
                                        // Mostra o pop-up ao clicar no card
                                        setShowPopup(true)
                                        // Salva o índice do card selecionado
                                        selectedCardIndex = listaExercicios.indexOf(exercicio)
                                    },
                                )

                            }

                            Spacer(modifier = Modifier.height(40.dp))
                            alinharDireita = !alinharDireita;


                        }
                    }

                }
                // Se o pop-up estiver visível, mostra o pop-up correspondente ao card selecionado
                if (showPopup && selectedCardIndex != -1) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(13, 13, 13).copy(alpha = 0.2f))
                            .clickable {
                                // Fecha o pop-up ao clicar no botão de fechar
                                setShowPopup(false)
                                // Reseta o índice do card selecionado
                                selectedCardIndex = -1
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        CardPopup(
                            desbloqueada = listaExercicios[selectedCardIndex].desbloqueada,
                            qtdExerciciosFase = listaExercicios[selectedCardIndex].qtdExerciciosFase,
                            qtdExerciciosFaseConcluidos = listaExercicios[selectedCardIndex].qtdExerciciosFaseConcluidos,
                        )
                    }

                }
            }
        )
    }
}

