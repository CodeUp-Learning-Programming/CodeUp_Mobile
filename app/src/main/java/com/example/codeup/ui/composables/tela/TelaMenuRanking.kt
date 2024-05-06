package com.example.codeup.ui.composables.tela

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.codeup.R
import com.example.codeup.data.Usuario
import com.example.codeup.data.UsuarioRanking
import com.example.codeup.ui.composables.listas.ListaRanking
import com.example.codeup.ui.composables.menu.MenuPadrao

@Composable
fun TelaMenuRanking(
    usuario: Usuario,
) {

    val listaUsuariosRanking = remember {
        listOf(
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
            UsuarioRanking(
                nome = "Matheus",
                fotoPerfil = "https://th.bing.com/th/id/OIG2.q1BNwR8EEW2JLFBdihTi?pid=ImgGn",
                pontos = "1000 Xp",
            ),
        )
    }

    MenuPadrao(
        titulo = stringResource(R.string.text_ranking),
        onClick = {
            Log.d("Tela Ranking", "Menu")
        },
        conteudo = ({
                ListaRanking(listaUsuariosRanking = listaUsuariosRanking)
        })
    )

}