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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.codeup.R
import com.example.codeup.ui.composables.ImagemFundo
import com.example.codeup.ui.composables.TextoBranco

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuPadrao(
    texto: String = "",
    imagem: Int = R.drawable.icon_adicionar_amigo,
    conteudo: @Composable () -> Unit
) {
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0, 0, 0))
                    .height(80.dp)
                    .padding(top = 20.dp)
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
                        val fotoEngrenagem: Painter = painterResource(id = imagem)
                        TextoBranco(texto = texto, tamanhoFonte = 24, pesoFonte = "normal")
                        Image(
                            modifier = Modifier,
                            painter = fotoEngrenagem,
                            contentDescription = stringResource(R.string.text_descricao_materia),
                        )

                    }

                }

            }

        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            ImagemFundo(backgroundImageResId = R.drawable.tema_padrao) {
                //Colocar conteudo aqui dinamicamente
                conteudo()
            }
        }
    }
}

