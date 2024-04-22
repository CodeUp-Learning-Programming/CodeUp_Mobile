package com.example.codeup.ui.composables.tela

import android.content.Intent
import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.codeup.R
import com.example.codeup.data.Materia
import com.example.codeup.data.Usuario
import com.example.codeup.ui.composables.BotaoAzulClaro
import com.example.codeup.ui.composables.TextoBranco
import com.example.codeup.ui.composables.card.CardExperimentarPro
import com.example.codeup.ui.composables.card.CardPerfil
import com.example.codeup.ui.composables.card.GraficoLinha
import com.example.codeup.ui.composables.card.GraficoTrilhaRecente
import com.example.codeup.ui.composables.menu.MenuPadrao
import com.example.codeup.ui.screens.TelaConfiguracoes
import com.example.codeup.util.StoreRememberUser

@Composable
fun TelaMenuPerfil(
    usuario: Usuario,
) {
    val context = LocalContext.current

    MenuPadrao(
        texto = stringResource(R.string.text_perfil),
        imagem = R.drawable.icon_configurar,
        onClick = {
            val telaConfiguracoes = Intent(context, TelaConfiguracoes::class.java)
            telaConfiguracoes.putExtra("usuario", usuario)
            context.startActivity(telaConfiguracoes)
        },
        conteudo = ({
            val scope = rememberCoroutineScope()
            val dataStoreRememberUser = StoreRememberUser(context)

            val interactionSource = remember { MutableInteractionSource() }
            val (showPopup, setShowPopup) = remember { mutableStateOf(false) }
            Box(modifier = Modifier.fillMaxSize()) {

                //Geral
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    //Perfil
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        //Foto
                        Box(
                            modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                                .clip(CircleShape)
                        ) {
                            AsyncImage(
                                model = usuario.fotoPerfil,
                                contentDescription = "astronauta",
                            )
                        }
                        //
                        TextoBranco(texto = usuario.nome, tamanhoFonte = 16, pesoFonte = "Titulo")
                        BotaoAzulClaro(
                            modifier = Modifier,
                            text = "EXPERIMENTE O PRO DE GRAÇA",
                            onClick = {
                                setShowPopup(true)
                                Log.d("PERFIL", "Exibindo pop up")
                            })
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        CardPerfil(
                            R.drawable.icon_fogo_azul,
                            R.string.text_icon_fogo_azul,
                            "2",
                            stringResource(R.string.text_sequencia)
                        )
                        CardPerfil(
                            R.drawable.icon_estrela,
                            R.string.text_icon_fogo_azul,
                            "2",
                            stringResource(R.string.text_ranking)
                        )
                        CardPerfil(
                            R.drawable.icon_moeda,
                            R.string.text_icon_fogo_azul,
                            usuario.moedas.toString(),
                            stringResource(R.string.text_moedas)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                                .width(100.dp)
                                .background(Color(24, 24, 24))
                        )
                        Row(
                            modifier = Modifier
                                .width(100.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            TextoBranco(
                                texto = stringResource(R.string.text_dashboard),
                                tamanhoFonte = 12
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .height(8.dp)
                                .width(100.dp)
                                .background(Color(24, 24, 24))
                        )

                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        GraficoTrilhaRecente(
                            Materia(
                                id = 1,
                                titulo = "Algoritmo",
                                url = ""
                            )
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .height(10.dp)
                            .background(Color(24, 24, 24))
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {

                        GraficoLinha(titulo = stringResource(R.string.text_exercicios_feitos))

                        Spacer(
                            modifier = Modifier
                                .height(10.dp)
                                .background(Color(24, 24, 24))
                        )
                        GraficoLinha(titulo = stringResource(R.string.text_maior_ranking))

                    }
                    Spacer(
                        modifier = Modifier
                            .height(50.dp)
                            .background(Color(24, 24, 24))
                    )

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
                        // Interceptador de cliques para o pop-up
                        Box(
                            modifier = Modifier
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null,
                                    onClick = {}) // Vazio para não fazer nada quando o pop-up é clicado.
                        ) {
                            CardExperimentarPro(
                                onClickFechar = { setShowPopup(false) },
                                onClickComprar = { setShowPopup(false) }
                            )
                        }
                    }
                }


            }
        })

    )

}