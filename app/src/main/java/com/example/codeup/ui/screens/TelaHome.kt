package com.example.codeup.ui.screens

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.codeup.api.RetrofitService
import com.example.codeup.data.Fase
import com.example.codeup.data.Usuario
import com.example.codeup.ui.DadosDoCard
import com.example.codeup.ui.composables.BarraNavegacao
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
                    desbloqueada = true,
                    qtdExerciciosFase = 5,
                    qtdExerciciosFaseConcluidos = 5
                ),
                DadosDoCard(
                    desbloqueada = false,
                    qtdExerciciosFase = 5,
                    qtdExerciciosFaseConcluidos = 3
                ),
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


            listaExercicios = listaExerciciosMock.map { fase ->
                DadosDoCard(
                    desbloqueada = fase.desbloqueada,
                    qtdExerciciosFase = fase.qtdExerciciosFase,
                    qtdExerciciosFaseConcluidos = fase.qtdExerciciosFaseConcluidos
                )
            }
        }
    })

    if (user != null) {
       BarraNavegacao(rememberNavController(), user, listaExercicios)
    }
}

