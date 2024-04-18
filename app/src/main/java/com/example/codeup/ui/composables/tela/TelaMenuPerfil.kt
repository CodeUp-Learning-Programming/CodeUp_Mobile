package com.example.codeup.ui.composables.tela

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.codeup.R
import com.example.codeup.data.Materia
import com.example.codeup.data.Usuario
import com.example.codeup.ui.composables.BotaoAzulClaro
import com.example.codeup.ui.composables.TextoBranco
import com.example.codeup.ui.composables.card.CardPerfil
import com.example.codeup.ui.composables.card.GraficoLinha
import com.example.codeup.ui.composables.card.GraficoTrilhaRecente
import com.example.codeup.ui.composables.menu.MenuPadrao

@Composable
fun TelaMenuPerfil(
    usuario: Usuario,
) {
    MenuPadrao(
        texto = stringResource(R.string.text_perfil),
        imagem = R.drawable.icon_configurar,
        conteudo = ({


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
                            //Exibir pop up
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
                            texto = stringResource(R.string.dashboard),
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
                            fases = listOf()
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
        })

    )

}