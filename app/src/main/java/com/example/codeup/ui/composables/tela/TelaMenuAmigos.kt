package com.example.codeup.ui.composables.tela

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.codeup.R
import com.example.codeup.data.Amigo
import com.example.codeup.data.AmizadeRequestReceived
import com.example.codeup.data.BuscarPorNomeRequest
import com.example.codeup.data.BuscarPorNomeResult
import com.example.codeup.data.Usuario
import com.example.codeup.ui.composables.componentes.BotaoAzulClaro
import com.example.codeup.ui.composables.componentes.TextFieldBordaGradienteAzul
import com.example.codeup.ui.composables.componentes.TextoBranco
import com.example.codeup.ui.composables.listas.ListaAmigos
import com.example.codeup.ui.composables.listas.ListaProcurarAmigos
import com.example.codeup.ui.composables.listas.ListaSolicitacoesAmigos
import com.example.codeup.ui.composables.menu.MenuPadrao
import com.example.codeup.ui.composables.menu.MenuPadraoVoltar
import com.example.codeup.ui.screens.viewmodels.AmizadeViewModel
import com.example.codeup.util.StoreAmizades
import kotlinx.coroutines.launch

@Composable
fun TelaMenuAmigos(
    usuario: Usuario,
) {

    var listaAmigos by remember { mutableStateOf(emptyList<Amigo>()) }
    var listaProcurarAmigos by remember { mutableStateOf(emptyList<BuscarPorNomeResult>()) }
    var listaSolicitacoes by remember { mutableStateOf(emptyList<AmizadeRequestReceived>()) }

    var procurarAmigos by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val storeAmizades = StoreAmizades.getInstance(context)

    val (buscarPorNomeRequest, buscarPorNomeRequestSetter) = remember {
        mutableStateOf(BuscarPorNomeRequest())
    }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            storeAmizades.getAmigos.collect { amigos ->
                listaAmigos = amigos
            }
        }
        coroutineScope.launch {
            storeAmizades.getListaBuscarAmigos.collect { amigos ->
                listaProcurarAmigos = amigos
            }
        }
        coroutineScope.launch {
            storeAmizades.getPedidoAmizade.collect { pedidos ->
                listaSolicitacoes = pedidos
            }
        }
    }

    Log.d("LISTA", "LISTAPEDIDOS $listaProcurarAmigos")

    if (procurarAmigos) {
        MenuPadraoVoltar(
            titulo = stringResource(id = R.string.text_add_amigos),
            onClickVoltar = {
                procurarAmigos = false
            },
            conteudo = ({
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(150.dp), contentAlignment = Alignment.Center
                ) {
                    Column(Modifier.padding(start = 10.dp, end = 10.dp)) {
                        TextoBranco(
                            texto = stringResource(R.string.text_nome),
                            tamanhoFonte = 14,
                        )
                        var isTextfieldFocused by remember { mutableStateOf(false) }
                        Spacer(modifier = Modifier.width(2.dp))

                        TextFieldBordaGradienteAzul(
                            modifier = Modifier.fillMaxWidth(),
                            isTextFieldFocused = true,
                            texto = buscarPorNomeRequest.nome,
                            label = "",
                            onValueChange = {
                                buscarPorNomeRequestSetter(
                                    buscarPorNomeRequest.copy(
                                        idUsuario = usuario.id!!,
                                        nome = it
                                    )
                                )
                            },
                            onFocusChanged = {
                                isTextfieldFocused = true
                            },
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        BotaoAzulClaro(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.text_buscar).uppercase(),
                            onClick = {
                                Log.d(
                                    "AMIZADE",
                                    "${buscarPorNomeRequest.nome} e ${buscarPorNomeRequest.idUsuario}"
                                )

                                val amizadeViewModelViewModel = AmizadeViewModel(usuario.token)
                                amizadeViewModelViewModel.solicitacoesRecebidas(
                                    usuario.id!!,
                                    context
                                )
                                amizadeViewModelViewModel.buscarRelaciomento(
                                    buscarPorNome = buscarPorNomeRequest,
                                    context
                                )
                            })
                    }
                }
                ListaProcurarAmigos(
                    listaProcurarAmigos,
                    usuario = usuario,
                    imagem = R.drawable.icon_adicionar_amigo
                )
            }),
        )
    } else {
        MenuPadrao(
            titulo = stringResource(R.string.text_amigos),
            onClick = {
                procurarAmigos = true
            },
            imagem = R.drawable.icon_adicionar_amigo,
            conteudo = ({
                Column(
                    Modifier
                        .fillMaxWidth()
                        .height(600.dp)
                ) {
                    if (listaSolicitacoes.isNotEmpty()) {
                        ListaSolicitacoesAmigos(
                            listaSolicitacoes, usuario = usuario,
                            imagem = R.drawable.icon_usuario
                        )
                    }
                    Spacer(Modifier.height(10.dp))

                    ListaAmigos(
                        temSolicitacao = listaSolicitacoes.isNotEmpty(),
                        listaAmigos, usuario = usuario,
                        imagem = R.drawable.icon_usuario
                    )
                }
            })
        )
    }
}
