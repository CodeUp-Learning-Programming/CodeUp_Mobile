package com.example.codeup.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.codeup.R
import com.example.codeup.ui.DadosDoCard
import com.example.codeup.ui.composables.Card
import com.example.codeup.ui.composables.Menu
import com.example.codeup.ui.theme.CodeupTheme

class TelaHome : ComponentActivity() {
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
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                        Home("Android")

                }
            }
        }
    }
}





@Composable
fun Home(name: String, modifier: Modifier = Modifier) {
    Menu(
        "${R.mipmap.fundo}",
        "Algoritimo",
        totalCoracoes = 5,
        totalMoedas = 10,
        totalSequencia = 5,
        conteudo = {
            val listaExercicios = remember {
                mutableListOf(
                    DadosDoCard(
                        bloqueado = false,
                        totalExercicios = 5,
                        totalExerciciosConcluidos = 3
                    ),
                    DadosDoCard(
                        bloqueado = true,
                        totalExercicios = 5,
                        totalExerciciosConcluidos = 3
                    ),
                    DadosDoCard(
                        bloqueado = true,
                        totalExercicios = 10,
                        totalExerciciosConcluidos = 8
                    ),
                    DadosDoCard(
                        bloqueado = true,
                        totalExercicios = 10,
                        totalExerciciosConcluidos = 8
                    ),
                    DadosDoCard(
                        bloqueado = true,
                        totalExercicios = 10,
                        totalExerciciosConcluidos = 8
                    ),
                    DadosDoCard(
                        bloqueado = true,
                        totalExercicios = 10,
                        totalExerciciosConcluidos = 8
                    ),
                    DadosDoCard(
                        bloqueado = true,
                        totalExercicios = 10,
                        totalExerciciosConcluidos = 8
                    ),
                    DadosDoCard(
                        bloqueado = true,
                        totalExercicios = 10,
                        totalExerciciosConcluidos = 8
                    ),
                    DadosDoCard(
                        bloqueado = true,
                        totalExercicios = 10,
                        totalExerciciosConcluidos = 8
                    ),
                    DadosDoCard(
                        bloqueado = true,
                        totalExercicios = 10,
                        totalExerciciosConcluidos = 8
                    ),
                    DadosDoCard(
                        bloqueado = true,
                        totalExercicios = 15,
                        totalExerciciosConcluidos = 12
                    )
                )
            }
            var i = 0;
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                 ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var alinharDireita = true;
                items(listaExercicios) { exercicio ->
                    Row(
                        modifier = Modifier
                            .width(200.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement =  if(alinharDireita) Arrangement.End else Arrangement.Start
                    ){
                        Card(
                            bloqueado = exercicio.bloqueado,
                            totalExercicios = exercicio.totalExercicios,
                            totalExerciciosConcluidos = exercicio.totalExerciciosConcluidos
                        )
                    }

                    Spacer(modifier = Modifier.height(40.dp))
                    alinharDireita = !alinharDireita;
                }
            }
        })


}

