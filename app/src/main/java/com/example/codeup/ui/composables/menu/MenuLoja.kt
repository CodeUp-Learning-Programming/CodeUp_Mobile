package com.example.codeup.ui.composables.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.codeup.ui.composables.TextoBranco

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuLoja(
    texto: String = "",
    moedas: Int,
    conteudo: @Composable () -> Unit
) {

    Scaffold(
        topBar = {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0, 0, 0))
                    .height(80.dp)
                    .padding(top = 40.dp, start = 10.dp, end = 10.dp)
            ) {

                Column {
                    //Linha de cima
                    Row(Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val moeda: Painter = painterResource(id = R.drawable.icon_moeda)

                        TextoBranco(texto = texto, tamanhoFonte = 24)

                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically){
                            TextoBranco(texto = moedas.toString(), tamanhoFonte = 24)

                            Image(
                                modifier = Modifier,
                                painter = moeda,
                                contentDescription = stringResource(R.string.text_descricao_materia),
                            )
                        }


                    }
                }

            }


        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight()
                .background(Color(13, 13, 13)),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            //Colocar conteudo aqui dinamicamente
//            Box(Modifier.fillMaxWidth().height(1.dp).background(Color(40,40,40)))
            conteudo()

        }
    }
}

