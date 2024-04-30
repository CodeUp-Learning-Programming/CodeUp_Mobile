package com.example.codeup.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.codeup.ui.OpcoesPergunta
import com.example.codeup.ui.composables.TextOpcaoPergunta
import com.example.codeup.ui.composables.TextoBranco
import com.example.codeup.ui.composables.menu.MenuExercicio
import com.example.codeup.ui.screens.ui.theme.CodeupTheme

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
                    Greeting2("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    MenuExercicio(totalCoracoes = 5, conteudo = {
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
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextoBranco(
                        texto = "Os botões são essenciais para a interatividade nas interfaces de usuário, proporcionando uma maneira intuitiva e direta de realizar diversas ações, como enviar formulários, fazer seleções e efetuar compras. Sua presença facilita a execução de tarefas específicas, simplificando a experiência do usuário. Além disso, os botões visualmente comunicam as ações possíveis, auxiliando os usuários na interação com a interface.",
                        tamanhoFonte = 14,
                    )

                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Row {
                TextoBranco(
                    texto = "Qual elemento HTML é utilizado para criar um botão clicável em uma página da web?",
                    tamanhoFonte = 16,
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            var respostaEscolhida by remember { mutableStateOf("") }

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
                                onOptionSelected = { respostaEscolhida = opcao.texto }
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

            }

        }

    })


}

