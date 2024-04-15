package com.example.codeup.ui.composables.tela

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.codeup.data.Usuario
import com.example.codeup.data.UsuarioRanking
import com.example.codeup.ui.DadosDoCard
import com.example.codeup.ui.composables.listas.ListaRanking
import com.example.codeup.ui.composables.menu.MenuPadrao

@Composable
fun TelaMenuRanking(
    user: Usuario,
    listaExercicios: List<DadosDoCard>
) {

    val listaUsuariosRanking = remember {
        listOf(
            UsuarioRanking(
                posicao = 1,
                pontos = 1000,
            ),
            UsuarioRanking(
                posicao = 2,
                pontos = 1000,
            ),
            UsuarioRanking(
                posicao = 3,
                pontos = 1000,
            ),
            UsuarioRanking(
                posicao = 4,
                pontos = 1000,
            ),
            UsuarioRanking(
                posicao = 5,
                pontos = 1000,
            ),
            UsuarioRanking(
                posicao = 6,
                pontos = 1000,
            ),
            UsuarioRanking(
                posicao = 7,
                pontos = 1000,
            ),
            UsuarioRanking(
                posicao = 8,
                pontos = 1000,
            ),
            UsuarioRanking(
                posicao = 9,
                pontos = 1000,
            ),
            UsuarioRanking(
                posicao = 10,
                pontos = 1000,
            ),
            UsuarioRanking(
                posicao = 11,
                pontos = 1000,
            ),
            UsuarioRanking(
                posicao = 12,
                pontos = 1000,
            ),
            UsuarioRanking(
                posicao = 13,
                pontos = 1000,
            ),
            UsuarioRanking(
                posicao = 14,
                pontos = 1000,
            ),
            UsuarioRanking(
                posicao = 15,
                pontos = 1000,
            ),
            UsuarioRanking(
                posicao = 16,
                pontos = 1000,
            ),
            UsuarioRanking(
                posicao = 17,
                pontos = 1000,
            ),
            UsuarioRanking(
                posicao = 18,
                pontos = 1000,
            ),
            UsuarioRanking(
                posicao = 19,
                pontos = 1000,
            ),
            UsuarioRanking(
                posicao = 20,
                pontos = 1000,
            ),

        )
    }

    MenuPadrao(
        texto = "Ranking",
        conteudo = ({
           ListaRanking(listaUsuariosRanking = listaUsuariosRanking)
        })
    )
}