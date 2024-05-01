package com.example.codeup.ui.screens

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.unit.dp
import com.example.codeup.data.Usuario
import com.example.codeup.ui.OpcoesPergunta
import com.example.codeup.ui.composables.TextOpcaoPergunta
import com.example.codeup.ui.composables.TextoBranco
import com.example.codeup.ui.composables.menu.MenuExercicio
import com.example.codeup.ui.screens.ui.theme.CodeupTheme
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
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
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

    val coroutineScope = rememberCoroutineScope()
    val storeUser = StoreUser.getInstance(context)

    // Observe and collect user data from DataStore
    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            storeUser.getUsuario.collect { retrievedUser ->
                usuario = retrievedUser
            }
        }
    }
    usuario?.let { it ->
        MenuExercicio(totalCoracoes = it.vidas, conteudo = {
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



}

