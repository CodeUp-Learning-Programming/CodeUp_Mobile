package com.example.codeup.ui.composables.listas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import com.example.codeup.data.Usuario
import com.example.codeup.ui.composables.TextoBranco

@Composable
fun ListaAmigos(
    listaAmigos: List<Usuario>
) {
    var contador = 0
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(listaAmigos) { amigos ->

            Row(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(
                        if (contador % 2 == 0) Color(22, 22, 22) else Color(
                            0,
                            0,
                            0
                        )
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
                        modifier = Modifier
                            .width(80.dp)
                            .height(100.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp)
                                .clip(CircleShape)
                        ) {
                            AsyncImage(
                                model = "https://helpia.ai/wp-content/uploads/2023/11/bing-creator.jpeg",
                                contentDescription = "astronauta",
                            )
                        }
                    }
                    //Nome
                    Row(
                        modifier = Modifier
                            .height(100.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextoBranco(
                            texto = "${amigos.nome}",
                            tamanhoFonte = 12
                        )

                    }
                }


                //Pontuação
                Row(
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .height(20.dp)
                        .width(20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    val usuario: Painter =
                        painterResource(id = R.drawable.icon_usuario)

                    Image(
                        painter = usuario,
                        contentDescription = null, // A descrição é opcional
                        modifier = Modifier.fillMaxSize()
                    )

                }
            }
            contador++
        }
    }
}