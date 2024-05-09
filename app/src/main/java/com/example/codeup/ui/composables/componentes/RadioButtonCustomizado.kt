package com.example.codeup.ui.composables.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonCustomizado(
    listaOpcoesPergunta: List<String>
) {
    val (selectedOption, setSelectedOption) = remember { mutableStateOf(listaOpcoesPergunta[0]) } // Inicializa com a primeira opção selecionada
    var ultimoItem = listaOpcoesPergunta.size - 1
    var contador = 0

    Column(
        Modifier
            .fillMaxWidth()
            .background(Color(31, 31, 31), shape = RoundedCornerShape(8.dp)),
    ) {

        listaOpcoesPergunta.forEach { text ->
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { setSelectedOption(text) },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                RadioButton(
                    selected = text == selectedOption,
                    onClick = { setSelectedOption(text) },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color(0, 176, 246),
                        unselectedColor = Color(57, 57, 57),
                        disabledColor = Color.LightGray
                    )
                )
                TextoBranco(
                    texto = text,
                    tamanhoFonte = 12,
                )
            }
            if (ultimoItem != contador) {
                Spacer(
                    Modifier
                        .background(Color(57, 57, 57))
                        .height(1.dp)
                        .fillMaxWidth()
                )
                contador++
            }
        }
    }
}
