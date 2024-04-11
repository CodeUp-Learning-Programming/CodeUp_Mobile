package com.example.codeup.ui.composables.tela

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.codeup.R
import com.example.codeup.data.Materia
import com.example.codeup.data.Usuario
import com.example.codeup.ui.DadosDoCard
import com.example.codeup.ui.composables.BotaoAzulClaro
import com.example.codeup.ui.composables.TextoBranco
import com.example.codeup.ui.composables.card.CardPerfil
import com.example.codeup.ui.composables.card.GraficoTrilhaRecente
import com.example.codeup.ui.composables.menu.MenuPadrao

@Composable
fun TelaMenuPerfil(
    user: Usuario,
    listaExercicios: List<DadosDoCard>
) {
    MenuPadrao(
        texto = "Perfil",
        conteudo = ({
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(40, 40, 40))
            )
            //Geral
            Column {
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
                            model = "https://helpia.ai/wp-content/uploads/2023/11/bing-creator.jpeg",
                            contentDescription = "astronauta",
                        )
                    }
                    //
                    TextoBranco(texto = user.nome, tamanhoFonte = 20, pesoFonte = "Titulo")
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
                        "SEQUÊNCIA"
                    )
                    CardPerfil(
                        R.drawable.icon_estrela,
                        R.string.text_icon_fogo_azul,
                        "2",
                        "SEQUÊNCIA"
                    )
                    CardPerfil(
                        R.drawable.icon_moeda,
                        R.string.text_icon_fogo_azul,
                        "2",
                        "SEQUÊNCIA"
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Canvas(
                        modifier = Modifier
                    ) {
                        drawLine(
                            color = Color(24, 24, 24),
                            start = Offset(-20f, 0f),
                            end = Offset(-330f, 0f),
                            strokeWidth = 24f
                        )
                        drawLine(
                            color = Color(24, 24, 24),
                            start = Offset(330f, 0f),
                            end = Offset(640f, 0f),
                            strokeWidth = 24f,
                        )
                    }
                    TextoBranco(texto = stringResource(R.string.dashboard), tamanhoFonte = 16)
                }
                Spacer(modifier = Modifier.height(10.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){

                        GraficoTrilhaRecente(Materia(id = 1, titulo = "Algoritmo", fases = listOf()))



                }
            }


        })
    )
}