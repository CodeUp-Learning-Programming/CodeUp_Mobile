package com.example.codeup.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun ImagemFundo(
    backgroundImageResId: Int,
    content: @Composable () -> Unit
) {
    val painter: Painter = painterResource(id = backgroundImageResId)
    val contentScale = ContentScale.FillBounds

    Box(
        modifier = Modifier.fillMaxSize(),
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