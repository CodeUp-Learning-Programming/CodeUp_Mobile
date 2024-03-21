package com.example.codeup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextoBranco(texto: String, tamanhoFonte:Int, pesoFonte: String){
    if(pesoFonte === "Titulo"){
        Text(
            text = texto,
            color = Color.White,
            fontFamily = FontFamily(
                Font(R.font.poppins_bold, FontWeight.Bold)
            ),
            fontSize = tamanhoFonte.sp,
            textAlign = TextAlign.Start
        )
    }else{
        Text(
            text = texto,
            color = Color.White,
            fontFamily = FontFamily(
                Font(R.font.poppins_semi_bold, FontWeight.SemiBold)
            ),
            fontSize = tamanhoFonte.sp,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun TextoAzulGradienteSublinhado(texto: String, tamanhoFonte: Int, pesoFonte: String) {

    val gradientColors = listOf(
        Color(0, 225, 242),
        Color(0, 132, 249)
    )

    if (pesoFonte == "Titulo") {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        brush = Brush.linearGradient(
                            colors = gradientColors,
                        )
                    )
                ) {
                    append(texto)
                }
            },
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
                fontFamily = FontFamily(Font(R.font.poppins_bold, FontWeight.Bold)),
                fontSize = tamanhoFonte.sp,
                textAlign = TextAlign.Start,
                color = Color.Black,
            ),
        )
    } else {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        brush = Brush.linearGradient(
                            colors = gradientColors,
                        )
                    )
                ) {
                    append(texto)
                }
            },
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
                fontFamily = FontFamily(Font(R.font.poppins_semi_bold, FontWeight.SemiBold)),
                fontSize = tamanhoFonte.sp,
                textAlign = TextAlign.Start,
                color = Color.Black,
            ),
        )
    }
}

@Composable
fun BotaoAzul(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .size(width = 120.dp, height = 56.dp)
            .shadow(4.dp, RoundedCornerShape(1.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0, 225, 242),
                        Color(0, 132, 249)
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick.invoke() }
            .padding(16.dp)
    ){
        Text(
            text = text,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center),
            fontFamily = FontFamily(
                Font(R.font.poppins_semi_bold, FontWeight.SemiBold)
            ),
            fontSize = 16.sp
        )
    }
}

@Composable
fun TextFieldBordaGradienteAzul(
    isTextFieldFocused: Boolean,
    texto: String,
    exemplo: String,
    onValueChanged: (String) -> Unit,
    onFocusChanged: (FocusState) -> Unit,

    ) {
    val corDaBorda = if (isTextFieldFocused) {
        Brush.horizontalGradient(
            colors = listOf(
                Color(0, 225, 242),
                Color(0, 132, 249)
            )
        )
    } else {
        Brush.horizontalGradient(
            colors = listOf(
                Color(56, 56, 56),
                Color(37, 37, 37)
            )
        )
    }

    BasicTextField(
        value = texto,
        onValueChange = { onValueChanged(it) },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { onFocusChanged(it) }
            .border(1.dp, corDaBorda, shape = RoundedCornerShape(8.dp))
            .background(Color(1, 1, 1))
            .padding(8.dp),
    ) {
        TextoBranco(texto = texto, tamanhoFonte = 14, pesoFonte = "normal")
    }
}

@Composable
fun CheckboxComGradiente(
    lembrar: Boolean,
    onCheckedChange: (Boolean) -> Unit,
){
    val gradient =
        Brush.horizontalGradient(
            colors = listOf(
                Color(0, 0, 0),
                Color(0, 0, 0)
            )
        )
    val borderGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0, 225, 242),
            Color(0, 132, 249)
        )
    )
    val checkedGradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0, 225, 242),
            Color(0, 132, 249)
        )
    )
    var cor_fundo = if (lembrar) {
        checkedGradient
    } else {
        gradient
    }
    Box(
        modifier = Modifier
            .size(24.dp)
            .background(cor_fundo)
            .border(1.dp, borderGradient, shape = RectangleShape)
    ) {
        Checkbox(
            checked = lembrar,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Transparent,
                uncheckedColor = Color.Transparent, // Define a cor não selecionada como transparente
                checkmarkColor = Color.Black,
//                        disabledIndeterminateColor = Color.LightGray
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample(imagem:String, nomeMateria:String, totalCoracoes:Int, totalSequencia:Int, totalMoedas:Int) {
    var presses by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color(R.color.black),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Box{
                        Column {
                            //Linha de cima
                            Row{

                                //materia
                                Row{
                                    val painter: Painter = painterResource(id = R.mipmap.javascritp_logo)
                                    val contentScale = ContentScale.FillBounds

                                    Image(
                                        painter = painter,
                                        contentDescription = stringResource(R.string.text_descricao_materia),
                                    )
                                }
                                Row(modifier = Modifier
                                    .fillMaxWidth(),

                                    ){
                                    //coracao
                                    //fogo
                                    //moedas
                                    val painter1: Painter = painterResource(id = R.mipmap.coracao_cheio)
                                    val painter2: Painter = painterResource(id = R.mipmap.fogo_azul)
                                    val painter3: Painter = painterResource(id = R.mipmap.moeda)
                                    val contentScale = ContentScale.FillBounds
                                    Row(modifier = Modifier){
                                        TextoBranco(texto = "${totalCoracoes}", tamanhoFonte = 20, pesoFonte = "normal")
                                        Image(
                                            painter = painter1,
                                            contentDescription = stringResource(R.string.text_descricao_materia),
                                        )
                                    }
                                    Row(){
                                        TextoBranco(texto = "${totalSequencia}", tamanhoFonte = 20, pesoFonte = "normal")
                                        Image(
                                            painter = painter2,
                                            contentDescription = stringResource(R.string.text_descricao_materia),
                                        )
                                    }
                                    Row(){
                                        TextoBranco(texto = "${totalMoedas}", tamanhoFonte = 20, pesoFonte = "normal")
                                        Image(
                                            painter = painter3,
                                            contentDescription = stringResource(R.string.text_descricao_materia),
                                        )
                                    }

                                }
                            }
                            //Linha de baixo
                            Row(){
                                //menu hamburg
                                //materia
                                TextoBranco(texto = nomeMateria, tamanhoFonte = 20, pesoFonte = "normal")
                            }
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(R.color.black),
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
               Box(modifier = Modifier
                       .fillMaxWidth()
                       .fillMaxHeight()){
                   Row(modifier = Modifier
                       .fillMaxWidth()
                       .fillMaxHeight(),
                       horizontalArrangement = Arrangement.SpaceAround,
                       verticalAlignment = Alignment.CenterVertically
                   ){
                       val painter1: Painter = painterResource(id = R.mipmap.chapeu_estudante)
                       val painter2: Painter = painterResource(id = R.mipmap.medalha)
                       val painter3: Painter = painterResource(id = R.mipmap.usuario)
                       val painter4: Painter = painterResource(id = R.mipmap.usuario)
                       val contentScale = ContentScale.FillBounds

                       Column(
                           modifier = Modifier,
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center
                       ) {
                           Image(
                               painter = painter1,
                               contentDescription = stringResource(R.string.text_aprenda),
                               modifier = Modifier

                           )
                           TextoBranco(texto = stringResource(R.string.text_aprenda), tamanhoFonte = 12, pesoFonte = "normal")
                       }
                       Column (
                           modifier = Modifier,
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center
                       ){
                           Image(
                               painter = painter2,
                               contentDescription = stringResource(R.string.text_ranking),

                           )
                           TextoBranco(texto = stringResource(R.string.text_ranking), tamanhoFonte = 12, pesoFonte = "normal")
                       }
                       Column (
                           modifier = Modifier,
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center
                       ){
                           Image(
                               painter = painter3,
                               contentDescription = stringResource(R.string.text_perfil),

                           )
                           TextoBranco(texto = stringResource(R.string.text_perfil), tamanhoFonte = 12, pesoFonte = "normal")
                       }
                       Column (
                           modifier = Modifier,
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center
                       ){
                           Image(
                               painter = painter4,
                               contentDescription = stringResource(R.string.text_perfil),

                           )
                           TextoBranco(texto = stringResource(R.string.text_perfil), tamanhoFonte = 12, pesoFonte = "normal")
                       }
                   }
               }
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
         ImageBackgroundExample(backgroundImageResId = R.mipmap.fundo) {
             
         }
        }
    }
}


@Composable
fun ImageBackgroundExample(
    backgroundImageResId: Int,
    content: @Composable () -> Unit
) {
    val painter: Painter = painterResource(id = backgroundImageResId)
    val contentScale = ContentScale.FillBounds

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painter,
            contentDescription = null, // A descrição é opcional
            contentScale = contentScale,
            modifier = Modifier.fillMaxSize()
        )
        content()
    }
}