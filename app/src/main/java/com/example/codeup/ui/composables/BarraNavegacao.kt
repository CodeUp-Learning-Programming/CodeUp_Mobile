package com.example.codeup.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import com.example.codeup.R
import com.example.codeup.data.Usuario
import com.example.codeup.ui.DadosDoCard
import com.example.codeup.ui.TelasFragmentos
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.codeup.ui.screens.TelaLogin

@Composable
fun BarraNavegacao(
    modifier: Modifier,
    navController: NavHostController,
    user: Usuario,
    listaExercicio: List<DadosDoCard>
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        NavHost(
            navController = navController,
            startDestination = TelasFragmentos.TELA_MENU_APRENDA.name
        ) {
            composable(TelasFragmentos.TELA_MENU_APRENDA.name) {
                TelaMenuAprenda(user = user, listaExercicios = listaExercicio)
            }
            composable(TelasFragmentos.TELA_MENU_AMIGOS.name) {
                TelaLogin()
            }
//            composable(TelasFragmentos.TELA_MENU_LOJA.name) {
//            }
//            composable(TelasFragmentos.TELA_MENU_RANKING.name) {
//            }
//            composable(TelasFragmentos.TELA_MENU_PERFIL.name) {
        }
    }
    Box(
        modifier = Modifier
            .background(Color.Black)
            .height(110.dp)
            .padding(top = 10.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painter1: Painter = painterResource(id = R.drawable.icon_aprenda_selecionado)
            val painter2: Painter = painterResource(id = R.drawable.icon_medalha)
            val painter3: Painter = painterResource(id = R.drawable.icon_amigos)
            val painter4: Painter = painterResource(id = R.drawable.icon_usuario)

            Column(
                modifier = Modifier
                    .clickable { navController.navigate(TelasFragmentos.TELA_MENU_APRENDA.name) },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painter1,
                    contentDescription = stringResource(R.string.text_aprenda),
                    modifier = Modifier
                )
                TextoBranco(
                    texto = stringResource(R.string.text_aprenda),
                    tamanhoFonte = 12,
                    pesoFonte = "normal"
                )
            }
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painter2,
                    contentDescription = stringResource(R.string.text_ranking),

                    )
                TextoBranco(
                    texto = stringResource(R.string.text_ranking),
                    tamanhoFonte = 12,
                    pesoFonte = "normal"
                )
            }
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painter3,
                    contentDescription = stringResource(R.string.text_amigos),

                    )
                TextoBranco(
                    texto = stringResource(R.string.text_amigos),
                    tamanhoFonte = 12,
                    pesoFonte = "normal"
                )
            }
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painter4,
                    contentDescription = stringResource(R.string.text_perfil),

                    )
                TextoBranco(
                    texto = stringResource(R.string.text_perfil),
                    tamanhoFonte = 12,
                    pesoFonte = "normal"
                )
            }
        }
    }
}
