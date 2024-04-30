package com.example.codeup.ui.composables.tela

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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
import com.example.codeup.ui.composables.TextoBranco
import com.example.codeup.ui.composables.card.CardComprarItem
import com.example.codeup.ui.composables.card.CardItemLoja
import com.example.codeup.ui.composables.menu.MenuLoja
import com.example.codeup.ui.screens.viewmodels.LojaViewModel
import com.example.codeup.ui.screens.viewmodels.UsuarioViewModel
import com.example.codeup.util.StoreLoja
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
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


    var atualizar by remember { mutableStateOf(true) }
    var itemFoiAdquirido by remember { mutableStateOf(false) }

    val interactionSource = remember { MutableInteractionSource() }
    val (showPopup, setShowPopup) = remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<ItemLoja?>(null) }


    // Observe and collect user data from DataStore

    if(loja == null && atualizar){
        atualizar = false
        val lojaViewModel = LojaViewModel(usuario.token)
//        lojaViewModel.carregarLoja(context)
    }

    val itensPorTipo = loja?.itensPorTipo
    itensPorTipo?.let {
        MenuLoja(
            texto = stringResource(R.string.text_loja),
            moedas = usuario.moedas
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn {
                    itensPorTipo.forEach { (tipo, itens) ->
                        stickyHeader {
                            Box(
                                Modifier
                                    .height(50.dp)
                                    .fillMaxWidth()
                                    .background(Color(13, 13, 13)),
                            ) {
                                Row(
                                    Modifier
                                        .height(50.dp)
                                        .fillMaxWidth()
                                        .background(Color(13, 13, 13))
                                        .padding(start = 5.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    TextoBranco(texto = tipo, tamanhoFonte = 20)
                                }
                            }
                        }
                        items(itens) { item ->
                            var equipado by remember { mutableStateOf(false) }

                            if(item.tipoItem == "Imagem" && item.fotoItem != usuario.fotoPerfil){
                                equipado = false
                            }else if(item.tipoItem == "Tema"){
                                equipado = false
                            }else{
                                equipado = true
                            }

                            CardItemLoja(
                                modifier = Modifier.fillMaxWidth(),
                                itemLoja = item,
                                cor = if (itens.indexOf(item) % 2 == 0) Color(
                                    33,
                                    33,
                                    33
                                ) else Color(
                                    22,
                                    22,
                                    22
                                ),
                                onClick = {
                                    setShowPopup(true)
                                    selectedItem = item
                                },
                                onClickEquipar = {
                                    if(!equipado){
                                        val usuarioViewModel = UsuarioViewModel(usuario.token)
                                        usuarioViewModel.equiparItem(item.fotoItem, item.tipoItem,context)
                                    }
                                },
                                equipado = equipado
                            )
                            Log.d("LISTALOJA", "clicando $tipo e $item")
                        }
                    }
                }

                if (showPopup && selectedItem != null) {
                    var equipado by remember { mutableStateOf(false) }

                    if(selectedItem!!.tipoItem == "Imagem" && selectedItem!!.fotoItem != usuario.fotoPerfil){
                        equipado = false
                    }else if(selectedItem!!.tipoItem == "Tema"){
                        equipado = false
                    }else{
                        equipado = true
                    }

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
                                        lojaViewModel.comprarItem(selectedItem!!.id, context = context)
                                        lojaViewModel.carregarLoja(context = context)

                                        //colocar para fechar depois de um tempo
                                        setShowPopup(false)
                                        selectedItem = null
                                        itemFoiAdquirido = false
                                    },
                                    onClickEquipar = {
                                        if(!equipado){
                                            val usuarioViewModel = UsuarioViewModel(usuario.token)
                                            usuarioViewModel.equiparItem(selectedItem!!.fotoItem, selectedItem!!.tipoItem,context)
                                        }
                                    },
                                    equipado = equipado,
                                    isVisible = showPopup,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}