package com.example.codeup.ui.composables.menu

import androidx.compose.foundation.Image
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.codeup.R
import com.example.codeup.ui.composables.componentes.ImagemFundo
import com.example.codeup.ui.composables.componentes.TextoBranco

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuHome(
    tema: Int,
    nomeMateria: String,
    totalCoracoes: Int,
    totalSequencia: Int,
    totalMoedas: Int,
    conteudo: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(top = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .width(150.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //Linha de cima
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //materia
                        Spacer(Modifier.width(10.dp))
                        Row(
                            Modifier.width(100.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            val painter: Painter =
                                painterResource(id = R.drawable.icon_javascript_logo)

                            Image(
                                painter = painter,
                                contentDescription = stringResource(R.string.text_descricao_materia),
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val painter1: Painter = painterResource(id = R.drawable.icon_coracao_cheio)
                            val painter2: Painter = painterResource(id = R.drawable.icon_fogo_azul)
                            val painter3: Painter = painterResource(id = R.drawable.icon_moeda)

                            Row(
                                modifier = Modifier.width(100.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    modifier = Modifier.width(100.dp),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    TextoBranco(
                                        texto = "${totalMoedas}",
                                        tamanhoFonte = 24,
                                    )
                                    Spacer(Modifier.width(5.dp))
                                    Image(
                                        painter = painter3,
                                        contentDescription = stringResource(R.string.text_descricao_materia),
                                    )
                                }
                            }
                            Spacer(Modifier.width(10.dp))

                            Row(
                                modifier = Modifier.width(50.dp),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextoBranco(
                                    texto = "${totalCoracoes}",
                                    tamanhoFonte = 24,
                                )
                                Image(
                                    painter = painter1,
                                    contentDescription = stringResource(R.string.text_descricao_materia),
                                )
                            }
                            Spacer(Modifier.width(10.dp))
                            Row(
                                modifier = Modifier.width(50.dp),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextoBranco(
                                    texto = "${totalSequencia}",
                                    tamanhoFonte = 24,
                                )
                                Image(
                                    painter = painter2,
                                    contentDescription = stringResource(R.string.text_descricao_materia),
                                )
                            }

                            Spacer(Modifier.width(10.dp))
                        }
                    }
                    //Linha de baixo
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //menu hamburg
//                        Row(
//                            horizontalArrangement = Arrangement.Start,
//                            verticalAlignment = Alignment.CenterVertically
//                        ){
//                            Icon(
//                                imageVector = Icons.Rounded.Menu,
//                                contentDescription = "Menu"  // Descrição para acessibilidade
//                            )
//                        }

                        //materia
                        TextoBranco(
                            texto = nomeMateria,
                            tamanhoFonte = 20,
                        )
                    }
                }
            }


        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            ImagemFundo(backgroundImageResId = tema) {
                //Colocar conteudo aqui dinamicamente
                conteudo()
            }
        }
    }
}


