package com.example.codeup.ui.composables.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.codeup.R
import com.example.codeup.data.ItemLoja
import com.example.codeup.ui.composables.TextoBranco

@Composable
fun CardItemLoja(
    modifier: Modifier,
    itemLoja: ItemLoja,
    cor: Color,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        Modifier.clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick
        )
    ) {
        Row(
            Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(cor),
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
                            model = itemLoja.fotoItem,
                            contentDescription = itemLoja.descricaoItem,
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
                        texto = itemLoja.nomeItem,
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
                TextoBranco(texto = itemLoja.precoItem.toInt().toString(), tamanhoFonte = 16)

                val moeda: Painter =
                    painterResource(id = R.drawable.icon_moeda)

                Image(
                    painter = moeda,
                    contentDescription = stringResource(R.string.text_moedas), // A descrição é opcional
                )

            }
        }
    }

}
 

