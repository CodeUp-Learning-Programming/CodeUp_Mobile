package com.example.codeup.ui.composables.tela

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
import com.example.codeup.data.AmizadeSearchRequest
import com.example.codeup.data.Usuario
import com.example.codeup.ui.composables.componentes.BotaoAzulClaro
import com.example.codeup.ui.composables.componentes.TextFieldBordaGradienteAzul
import com.example.codeup.ui.composables.componentes.TextoBranco
import com.example.codeup.ui.composables.listas.ListaAmigos
import com.example.codeup.ui.composables.menu.MenuPadrao
import com.example.codeup.ui.composables.menu.MenuPadraoVoltar
import com.example.codeup.util.StoreAmizades
import kotlinx.coroutines.launch

@Composable
fun TelaMenuAmigos(
    usuario: Usuario,
) {
    var listaAmigos by remember { mutableStateOf(emptyList<Amigo>()) }
    val listaProcurarAmigos by remember { mutableStateOf(emptyList<Amigo>()) }
    var procurarAmigos by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val storeAmizades = StoreAmizades.getInstance(context)

    val (amizadeSearchRequest, amizadeSearchRequestSetter) = remember {
        mutableStateOf(AmizadeSearchRequest())
    }

    LaunchedEffect(key1 = true) {
        coroutineScope.launch {
            storeAmizades.getAmigos.collect { amigo ->
                listaAmigos = amigo
            }
        }
    }



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
                            texto = amizadeSearchRequest.nome,
                            label = "",
                            onValueChange = { amizadeSearchRequest.nome },
                            onFocusChanged = {
                                isTextfieldFocused = true
                            },
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        BotaoAzulClaro(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(id = R.string.text_buscar).uppercase(),
                            onClick = { })
                    }
                }
                ListaAmigos(
                    listaAmigos,
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


                ListaAmigos(
                    listaAmigos, usuario = usuario,
                    imagem = R.drawable.icon_usuario
                )
            })
        )
    }

}

