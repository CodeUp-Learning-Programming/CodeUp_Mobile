package com.example.codeup.ui.composables.tela

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.codeup.R
import com.example.codeup.data.Usuario
import com.example.codeup.ui.composables.BotaoPretoBordaBranca
import com.example.codeup.ui.composables.TextFieldBordaGradienteAzul
import com.example.codeup.ui.composables.TextoBranco
import com.example.codeup.ui.composables.card.CardExperimentarPro
import com.example.codeup.ui.composables.menu.MenuConfiguracoes
import com.example.codeup.ui.screens.TelaLogin
import com.example.codeup.util.StoreRememberUser
import kotlinx.coroutines.launch

@Composable
fun TelaMenuConfiguracoes(
    usuario: Usuario,
) {
    MenuConfiguracoes(
        conteudo = ({
            val context = LocalContext.current
            val scope = rememberCoroutineScope()
            val dataStoreRememberUser = StoreRememberUser(context)
            val interactionSource = remember { MutableInteractionSource() }
            val (showPopup, setShowPopup) = remember { mutableStateOf(false) }
            Box(modifier = Modifier.fillMaxSize()) {

                //Geral
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    //Perfil
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        //Foto
                        Box(
                            modifier = Modifier
                                .height(150.dp)
                                .width(150.dp)
                                .clip(CircleShape)
                        ) {
                            AsyncImage(
                                model = usuario.fotoPerfil,
                                contentDescription = "foto de perfil",
                            )
                        }
                    }

                    Box(
                        Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
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
                                texto = usuario.nome,
                                label = "",
                                onValueChange = { usuario.nome },
                                onFocusChanged = {
                                    isTextfieldFocused = true
                                },
                            )
                        }
                    }
                    Box(
                        Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                    ) {
                        Column(Modifier.padding(start = 10.dp, end = 10.dp)) {
                            TextoBranco(
                                texto = stringResource(R.string.text_nome),
                                tamanhoFonte = 14,
                            )
                            var isTextfieldFocused by remember { mutableStateOf(false) }

                            TextFieldBordaGradienteAzul(
                                modifier = Modifier.fillMaxWidth(),
                                isTextFieldFocused = true,
                                texto = usuario.email,
                                label = "",
                                onValueChange = { usuario.email },
                                onFocusChanged = {
                                    isTextfieldFocused = true
                                },
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Box(
                        Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                    ) {
                        Column(Modifier.padding(start = 10.dp, end = 10.dp)) {
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                TextoBranco(
                                    texto = stringResource(R.string.text_configuracoes_do_app),
                                    tamanhoFonte = 14,
                                )
                            }
                        }
                    }


                    Box(
                        Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .height(1.dp)
                                    .fillMaxWidth()
                                    .background(Color(33, 33, 33))
                            )
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .background(Color(17, 17, 17))
                                    .padding(all = 15.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    Modifier.width(100.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    val tema: Painter = painterResource(id = R.drawable.icon_tema)
                                    Image(
                                        modifier = Modifier,
                                        painter = tema,
                                        contentDescription = stringResource(R.string.text_icone_tema),
                                    )
                                    TextoBranco(
                                        texto = stringResource(R.string.text_tema),
                                        tamanhoFonte = 14,
                                    )
                                }
                                TextoBranco(
                                    texto = stringResource(R.string.text_padrao),
                                    tamanhoFonte = 14,
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .height(1.dp)
                                    .fillMaxWidth()
                                    .background(Color(33, 33, 33))
                            )
                        }


                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Box(
                        Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                    ) {
                        Column(Modifier.padding(start = 10.dp, end = 10.dp)) {
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                TextoBranco(
                                    texto = stringResource(R.string.text_conta),
                                    tamanhoFonte = 14,
                                )
                            }
                        }
                    }

                    //Conta
                    Box(
                        Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .height(1.dp)
                                    .fillMaxWidth()
                                    .background(Color(33, 33, 33))
                            )
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .background(Color(17, 17, 17))
                                    .padding(all = 15.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    Modifier.width(150.dp).clickable {
                                        setShowPopup(true)
                                       },
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start,

                                ) {

                                    //trocar se o usuário for pro
                                    val estrela: Painter =
                                        painterResource(id = R.drawable.icon_estrela_vazia)
                                    Image(
                                        modifier = Modifier,
                                        painter = estrela,
                                        contentDescription = stringResource(R.string.text_icone_estrela_vazia),
                                    )
                                    Spacer(modifier = Modifier.width(2.dp))

                                    Text(
                                        text = stringResource(R.string.text_seja_pro),
                                        color = Color(255, 171, 11),
                                        fontFamily = FontFamily(
                                            Font(R.font.poppins_bold, FontWeight.Bold)
                                        ),
                                        fontSize = 14.sp,
                                        textAlign = TextAlign.Start
                                    )
                                }
                                TextoBranco(
                                    texto = "",
                                    tamanhoFonte = 14,
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .height(1.dp)
                                    .fillMaxWidth()
                                    .background(Color(33, 33, 33))
                            )
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .background(Color(17, 17, 17))
                                    .padding(all = 15.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    Modifier.width(150.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    val tema: Painter =
                                        painterResource(id = R.drawable.icon_cadeado_pequeno)
                                    Image(
                                        modifier = Modifier,
                                        painter = tema,
                                        contentDescription = stringResource(R.string.text_icone_cadeado),
                                    )
                                    Spacer(modifier = Modifier.width(2.dp))

                                    TextoBranco(
                                        texto = stringResource(R.string.text_alterar_senha),
                                        tamanhoFonte = 14,
                                    )
                                }
                                TextoBranco(
                                    texto = "",
                                    tamanhoFonte = 14,
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .height(1.dp)
                                    .fillMaxWidth()
                                    .background(Color(33, 33, 33))
                            )
                        }


                    }

                    //Notificações
                    Spacer(modifier = Modifier.height(20.dp))

                    Box(
                        Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                    ) {
                        Column(Modifier.padding(start = 10.dp, end = 10.dp)) {
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                TextoBranco(
                                    texto = stringResource(R.string.text_notificacoes),
                                    tamanhoFonte = 14,
                                )
                            }
                        }
                    }

                    Box(
                        Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .height(1.dp)
                                    .fillMaxWidth()
                                    .background(Color(33, 33, 33))
                            )
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .background(Color(17, 17, 17))
                                    .padding(all = 15.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    Modifier.width(150.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    val sino: Painter = painterResource(id = R.drawable.icon_sino)
                                    Image(
                                        modifier = Modifier,
                                        painter = sino,
                                        contentDescription = stringResource(R.string.text_icone_sino),
                                    )

                                    Spacer(modifier = Modifier.width(2.dp))
                                    TextoBranco(
                                        texto = stringResource(R.string.text_lembrete_diario),
                                        tamanhoFonte = 14,
                                    )
                                }
                                var selecionado by remember { mutableStateOf(false) }

                                Switch(checked = selecionado,
                                    onCheckedChange = { selecionado = it })
                            }
                            Box(
                                modifier = Modifier
                                    .height(1.dp)
                                    .fillMaxWidth()
                                    .background(Color(33, 33, 33))
                            )
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .background(Color(17, 17, 17))
                                    .padding(all = 15.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    Modifier.width(150.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    val relogio: Painter =
                                        painterResource(id = R.drawable.icon_relogio)
                                    Image(
                                        modifier = Modifier,
                                        painter = relogio,
                                        contentDescription = stringResource(R.string.text_icone_relogio),
                                    )
                                    Spacer(modifier = Modifier.width(2.dp))
                                    TextoBranco(
                                        texto = stringResource(R.string.text_lembrete_diario),
                                        tamanhoFonte = 14,
                                    )
                                }
                                TextoBranco(
                                    texto = "12:00",
                                    tamanhoFonte = 14,
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .height(1.dp)
                                    .fillMaxWidth()
                                    .background(Color(33, 33, 33))
                            )
                        }


                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center ) {
                        BotaoPretoBordaBranca("LOGOUT", onClick = {
                            scope.launch {
                                dataStoreRememberUser.saveEmail("")
                                dataStoreRememberUser.savePassword("")
                            }

                            val telaLogin = Intent(context, TelaLogin::class.java)
                            context.startActivity(telaLogin)

                        }
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }

                if (showPopup) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f))
                            .clickable(interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = { setShowPopup(false) }),
                        contentAlignment = Alignment.Center
                    ) {
                        // Interceptador de cliques para o pop-up
                        Box(
                            modifier = Modifier.clickable(interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = {}) // Vazio para não fazer nada quando o pop-up é clicado.
                        ) {
                            CardExperimentarPro(onClickFechar = { setShowPopup(false) },
                                onClickComprar = { setShowPopup(false) })
                        }
                    }
                }


            }
        })

    )

}