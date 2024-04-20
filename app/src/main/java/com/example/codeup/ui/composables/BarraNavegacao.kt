package com.example.codeup.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.codeup.R
import com.example.codeup.data.Fase
import com.example.codeup.data.Materia
import com.example.codeup.data.Usuario
import com.example.codeup.ui.TelasFragmentos
import com.example.codeup.ui.composables.tela.TelaMenuAmigos
import com.example.codeup.ui.composables.tela.TelaMenuAprenda
import com.example.codeup.ui.composables.tela.TelaMenuLoja
import com.example.codeup.ui.composables.tela.TelaMenuPerfil
import com.example.codeup.ui.composables.tela.TelaMenuRanking

@Composable
fun BarraNavegacao(
    navController: NavHostController,
    usuario: Usuario,
    listaExercicio: List<Fase>,
    materia: Materia
) {
    var estadoAprendaAtivo by remember { mutableStateOf(false) }
    var estadoRankingAtivo by remember { mutableStateOf(false) }
    var estadoAmigosAtivo by remember { mutableStateOf(false) }
    var estadoLojaAtivo by remember { mutableStateOf(false) }
    var estadoPerfilAtivo by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            Modifier.padding(bottom = 60.dp)
        ) {

            NavHost(
                modifier = Modifier,
                navController = navController,
                startDestination = TelasFragmentos.TELA_MENU_APRENDA.name
            ) {
                composable(TelasFragmentos.TELA_MENU_APRENDA.name) {
                    estadoAprendaAtivo = true
                    estadoRankingAtivo = false
                    estadoAmigosAtivo = false
                    estadoLojaAtivo = false
                    estadoPerfilAtivo = false

                    TelaMenuAprenda(usuario = usuario, listaExercicios = listaExercicio, materia)
                }
                composable(TelasFragmentos.TELA_MENU_RANKING.name) {
                    estadoAprendaAtivo = false
                    estadoRankingAtivo = true
                    estadoAmigosAtivo = false
                    estadoLojaAtivo = false
                    estadoPerfilAtivo = false

                    TelaMenuRanking(usuario = usuario)
                }
                composable(TelasFragmentos.TELA_MENU_AMIGOS.name) {
                    estadoAprendaAtivo = false
                    estadoRankingAtivo = false
                    estadoAmigosAtivo = true
                    estadoLojaAtivo = false
                    estadoPerfilAtivo = false

                    TelaMenuAmigos(usuario = usuario)
                }
                composable(TelasFragmentos.TELA_MENU_LOJA.name) {
                    estadoAprendaAtivo = false
                    estadoRankingAtivo = false
                    estadoAmigosAtivo = false
                    estadoLojaAtivo = true
                    estadoPerfilAtivo = false

                    TelaMenuLoja(usuario = usuario)
                }
                composable(TelasFragmentos.TELA_MENU_PERFIL.name) {
                    estadoAprendaAtivo = false
                    estadoRankingAtivo = false
                    estadoAmigosAtivo = false
                    estadoLojaAtivo = false
                    estadoPerfilAtivo = true

                    TelaMenuPerfil(usuario = usuario)
                }
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
                val aprendaSelecionado: Painter =
                    painterResource(id = R.drawable.icon_aprenda_selecionado)
                val aprendaNaoSelecionado: Painter = painterResource(id = R.drawable.icon_aprenda)

                val amigosSelecionado: Painter =
                    painterResource(id = R.drawable.icon_amigos_selecionado)
                val amigosNaoSelecionado: Painter = painterResource(id = R.drawable.icon_amigos)

                val usuarioSelecionado: Painter =
                    painterResource(id = R.drawable.icon_usuario_selecionado)
                val usuarioNaoSelecionado: Painter = painterResource(id = R.drawable.icon_usuario)

                val medalhaSelecionado: Painter =
                    painterResource(id = R.drawable.icon_medalha_selecionado)
                val medalhaNaoSelecionado: Painter = painterResource(id = R.drawable.icon_medalha)

                val lojaSelecionado: Painter =
                    painterResource(id = R.drawable.icon_loja_selecionado)
                val lojaNaoSelecionado: Painter = painterResource(id = R.drawable.icon_loja)
                val interactionSource = remember { MutableInteractionSource() }

                Column(
                    modifier = Modifier
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = { navController.navigate(TelasFragmentos.TELA_MENU_APRENDA.name) },
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Image(
                        painter = if (estadoAprendaAtivo) aprendaSelecionado else aprendaNaoSelecionado,
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
                    modifier = Modifier
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = { navController.navigate(TelasFragmentos.TELA_MENU_RANKING.name) },
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Image(
                        painter = if (estadoRankingAtivo) medalhaSelecionado else medalhaNaoSelecionado,
                        contentDescription = stringResource(R.string.text_ranking),

                        )
                    TextoBranco(
                        texto = stringResource(R.string.text_ranking),
                        tamanhoFonte = 12,
                        pesoFonte = "normal"
                    )
                }
                Column(
                    modifier = Modifier
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = { navController.navigate(TelasFragmentos.TELA_MENU_AMIGOS.name) },
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = if (estadoAmigosAtivo) amigosSelecionado else amigosNaoSelecionado,
                        contentDescription = stringResource(R.string.text_amigos),

                        )
                    TextoBranco(
                        texto = stringResource(R.string.text_amigos),
                        tamanhoFonte = 12,
                        pesoFonte = "normal"
                    )
                }
                Column(
                    modifier = Modifier
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = { navController.navigate(TelasFragmentos.TELA_MENU_LOJA.name) },
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = if (estadoLojaAtivo) lojaSelecionado else lojaNaoSelecionado,
                        contentDescription = stringResource(R.string.text_loja),

                        )
                    TextoBranco(
                        texto = stringResource(R.string.text_loja),
                        tamanhoFonte = 12,
                        pesoFonte = "normal"
                    )
                }
                Column(
                    modifier = Modifier
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null,
                            onClick = { navController.navigate(TelasFragmentos.TELA_MENU_PERFIL.name) },
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = if (estadoPerfilAtivo) usuarioSelecionado else usuarioNaoSelecionado,
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
}