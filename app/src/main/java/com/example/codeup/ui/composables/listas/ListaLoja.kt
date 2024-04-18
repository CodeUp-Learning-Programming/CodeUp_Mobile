package com.example.codeup.ui.composables.listas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.codeup.R
import com.example.codeup.data.ItemLoja
import com.example.codeup.ui.composables.TextoBranco

@Composable
fun ListaLoja(
    categoria: String = "",
    listaItemLoja: List<ItemLoja>
) {

    var contador = 0
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Row(
            Modifier
                .height(50.dp)
                .fillMaxWidth()
                .padding(start = 10.dp)
                .background(Color(13, 13, 13)),
        ) {
            TextoBranco(texto = categoria, tamanhoFonte = 24)
        }

        Column {
            listaItemLoja.forEach { item ->

//                items(listaItemLoja) { item ->

                    Row(
                        Modifier
                            .height(100.dp)
                            .fillMaxWidth()
                            .background(
                                if (contador % 2 == 0) Color(22, 22, 22) else Color(21, 21, 21)
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            //Foto
                            Row(
                                Modifier
                                    .width(80.dp)
                                    .height(100.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    Modifier
                                        .height(60.dp)
                                        .width(60.dp)
                                        .clip(CircleShape)
                                ) {
                                    AsyncImage(
                                        model = item.fotoItem,
                                        contentDescription = item.descricaoItem,
                                    )
                                }
                            }
                            //Nome
                            Row(
                                Modifier.height(100.dp),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextoBranco(
                                    texto = item.nomeItem,
                                    tamanhoFonte = 12
                                )

                            }
                        }


                        //Preço
                        Row(
                            modifier = Modifier
                                .padding(end = 20.dp)
                                .height(30.dp)
                                .width(40.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextoBranco(texto = item.precoItem.toInt().toString(), tamanhoFonte = 16)

                            val moeda: Painter =
                                painterResource(id = R.drawable.icon_moeda)

                            Image(
                                painter = moeda,
                                contentDescription = null, // A descrição é opcional
                            )

                        }
                    }
                    contador++
                }
            }
            }
    }
