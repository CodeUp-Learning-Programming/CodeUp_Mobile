package com.example.codeup.ui.composables.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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

@Composable
fun MenuPadrao(
    texto: String = "",
    imagem: Int = 0,
    onClick: () -> Unit,
    conteudo: @Composable () -> Unit
) {

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0, 0, 0))
                    .height(90.dp)
                    .padding(top = 30.dp)
            ) {

                Column {
                    //Linha de cima
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        TextoBranco(texto = texto, tamanhoFonte = 24)
                        if(imagem != 0){
                            val fotoEngrenagem: Painter = painterResource(imagem)

                            Image(
                                modifier = Modifier.clickable (onClick = onClick),
                                painter = fotoEngrenagem,
                                contentDescription = stringResource(R.string.text_descricao_materia),
                            )
                        }else{

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
            conteudo()
        }
    }
}

