package com.example.codeup.ui.composables.tela

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.codeup.R
import com.example.codeup.data.ItemLoja
import com.example.codeup.data.Loja
import com.example.codeup.data.Usuario
import com.example.codeup.ui.composables.card.CardComprarItem
import com.example.codeup.ui.composables.card.CardItemLoja
import com.example.codeup.ui.composables.componentes.TextoBranco
import com.example.codeup.ui.composables.menu.MenuLoja
import com.example.codeup.ui.screens.viewmodels.LojaViewModel
import com.example.codeup.ui.screens.viewmodels.UsuarioViewModel
import com.example.codeup.util.StoreLoja
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun TelaMenuLoja(
    usuario: Usuario,
) {
    val context = LocalContext.current

    var loja by remember { mutableStateOf<Loja?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val storeLoja = StoreLoja.getInstance(context)

    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            storeLoja.getLoja.collect { lojaAtual ->
                loja = lojaAtual
            }
        }
    }


    var itemFoiAdquirido by remember { mutableStateOf(false) }

    val interactionSource = remember { MutableInteractionSource() }
    val (showPopup, setShowPopup) = remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<ItemLoja?>(null) }

    val itensPorTipo = loja?.itensPorTipo
    itensPorTipo?.let {
        var atualizando by remember { mutableStateOf(false) }

        val pullRefreshState = rememberPullRefreshState(refreshing = atualizando, onRefresh = {

            coroutineScope.launch {
                atualizando = true
                val lojaViewModel = LojaViewModel(usuario.token)
                lojaViewModel.carregarLoja(context)
                delay(Random.nextLong(500, 3000))
                atualizando = false
            }

        })


        val expandedState = remember { mutableStateMapOf<String, Boolean>() }

        MenuLoja(
            texto = stringResource(R.string.text_loja),
            moedas = usuario.moedas,
            conteudo = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .pullRefresh(state = pullRefreshState)
                ) {

                    LazyColumn {
                        itensPorTipo.forEach { (tipo, itens) ->
                            if (!expandedState.containsKey(tipo)) {
                                expandedState[tipo] = true
                            }

                            stickyHeader {
                                TipoItemHeader(tipo, expandedState[tipo] == true) {
                                    expandedState[tipo] = !(expandedState[tipo] ?: true)
                                }
                            }

                            if (expandedState[tipo] == true) {
                                items(itens) { item ->
                                    var equipado by remember { mutableStateOf(false) }

                                    if (item.tipoItem == "Foto de Perfil" && item.fotoItem != usuario.fotoPerfil) {
                                        equipado = false
                                    } else if (item.tipoItem == "Tema") {
                                        equipado = false
                                    } else {
                                        equipado = true
                                    }
                                    CardItemLoja(
                                        modifier = Modifier.fillMaxWidth(),
                                        itemLoja = item,
                                        equipado = equipado,
                                        cor = if (itens.indexOf(item) % 2 == 0) Color(
                                            33,
                                            33,
                                            33
                                        ) else Color(22, 22, 22),
                                        onClick = {
                                            setShowPopup(true)
                                            selectedItem = item
                                        },
                                        onClickEquipar = {
                                            if (!equipado) {
                                                val usuarioViewModel =
                                                    UsuarioViewModel(usuario.token)
                                                usuarioViewModel.equiparItem(
                                                    item.fotoItem,
                                                    item.tipoItem,
                                                    context
                                                )
                                            }
                                        }
                                    )
                                }
                            }

                        }
                    }
                    PullRefreshIndicator(
                        refreshing = atualizando,
                        state = pullRefreshState,
                        Modifier.align(Alignment.TopCenter)
                    )


                    if (showPopup && selectedItem != null) {
                        var equipado by remember { mutableStateOf(false) }

                        if (selectedItem!!.tipoItem == "Foto de Perfil" && selectedItem!!.fotoItem != usuario.fotoPerfil) {
                            equipado = false
                        } else if (selectedItem!!.tipoItem == "Tema") {
                            equipado = false
                        } else {
                            equipado = true
                        }
                        AnimatedVisibility(
                            visible = showPopup,
                            enter = slideInVertically(
                                initialOffsetY = { -it }, // Inicia o pop-up acima do layout
                                animationSpec = tween(
                                    durationMillis = 400,
                                    easing = FastOutSlowInEasing
                                )
                            ),
                            exit = slideOutVertically(
                                targetOffsetY = { -it }, // Move o pop-up para cima ao sair
                                animationSpec = tween(
                                    durationMillis = 400,
                                    easing = FastOutSlowInEasing
                                )
                            ),
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Color(
                                        13,
                                        13,
                                        13,
                                        128
                                    )
                                ) // Fundo escuro para destacar o pop-up
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null,
                                    onClick = { setShowPopup(false) }
                                ),
                            content = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Black.copy(alpha = 0.5f))
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = null,
                                            onClick = { setShowPopup(false) }
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color(13, 13, 13).copy(alpha = 0.2f))
                                            .clickable(
                                                interactionSource = interactionSource,
                                                indication = null,
                                                onClick = {}
                                            ),

                                        contentAlignment = Alignment.Center
                                    ) {
                                        selectedItem?.let {
                                            CardComprarItem(
                                                itemLoja = it,
                                                onClick = {
                                                    setShowPopup(false)
                                                    selectedItem = null
                                                },
                                                onClickComprar = {
                                                    val lojaViewModel = LojaViewModel(usuario.token)
                                                    lojaViewModel.comprarItem(
                                                        selectedItem!!.id,
                                                        context = context
                                                    )
                                                    coroutineScope.launch {
                                                        delay(Random.nextLong(500, 3000))
                                                    }

                                                    //colocar para fechar depois de um tempo
                                                    setShowPopup(false)
                                                    selectedItem = null
                                                    itemFoiAdquirido = false
                                                },
                                                onClickEquipar = {
                                                    if (!equipado) {
                                                        val usuarioViewModel =
                                                            UsuarioViewModel(usuario.token)
                                                        usuarioViewModel.equiparItem(
                                                            selectedItem!!.fotoItem,
                                                            selectedItem!!.tipoItem,
                                                            context
                                                        )
                                                    }
                                                },
                                                equipado = equipado,
                                            )
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
            })
    }
}


@Composable
fun TipoItemHeader(tipo: String, isExpanded: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(Color(13, 13, 13))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextoBranco(texto = tipo, tamanhoFonte = 20)
        Icon(
            imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = if (isExpanded) "Colapsar" else "Expandir",
            tint = Color.White,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}