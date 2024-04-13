package com.example.codeup.ui.composables.tela

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.codeup.data.ItemAdquirido
import com.example.codeup.data.Usuario
import com.example.codeup.ui.composables.listas.ListaAmigos
import com.example.codeup.ui.composables.menu.MenuPadrao

@Composable
fun TelaMenuAmigos(
    user: Usuario,
) {
    val listaAmigos = remember {
        listOf(
            Usuario(
                id = 1,
                fotoPerfil = "", nome = "Administrador",
                email = "admin@sptech.school",
                token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZXZAc3B0ZWNoLnNjaG9vbCIsImlhdCI6MTY5ODA4NDY1NCwiZXhwIjoxNzAxNjg0NjU0fQ._ByXuksiF9C2K2Xu5OrAhquC2SHNfiAO7uut0pGEXN8JKzY8bzGksmQJQ6ICZIJ3uhladvK7NoDJyeS7iMrA0A",
                moedas = 500,
                nivel = 950,
                xp = 250,
                itensAdquiridos = listOf(
                    ItemAdquirido(
                        id = 1,
                        nomeItem = "Item 1",
                        fotoItem = "!",
                        tipoItem = "Imagem",
                        precoItem = 20.0,
                        descricaoItem = "Item 1",
                        equipado = false
                    ),
                    ItemAdquirido(
                        id = 2,
                        nomeItem = "Item 2",
                        fotoItem = "?",
                        tipoItem = "Imagem2",
                        precoItem = 20.0,
                        descricaoItem = "Item 2",
                        equipado = false
                    )
                )
            ),
            Usuario(
                id = 1,
                fotoPerfil = "", nome = "Administrador",
                email = "admin@sptech.school",
                token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZXZAc3B0ZWNoLnNjaG9vbCIsImlhdCI6MTY5ODA4NDY1NCwiZXhwIjoxNzAxNjg0NjU0fQ._ByXuksiF9C2K2Xu5OrAhquC2SHNfiAO7uut0pGEXN8JKzY8bzGksmQJQ6ICZIJ3uhladvK7NoDJyeS7iMrA0A",
                moedas = 500,
                nivel = 950,
                xp = 250,
                itensAdquiridos = listOf(
                    ItemAdquirido(
                        id = 1,
                        nomeItem = "Item 1",
                        fotoItem = "!",
                        tipoItem = "Imagem",
                        precoItem = 20.0,
                        descricaoItem = "Item 1",
                        equipado = false
                    ),
                    ItemAdquirido(
                        id = 2,
                        nomeItem = "Item 2",
                        fotoItem = "?",
                        tipoItem = "Imagem2",
                        precoItem = 20.0,
                        descricaoItem = "Item 2",
                        equipado = false
                    )
                )
            ),
            Usuario(
                id = 1,
                fotoPerfil = "", nome = "Administrador",
                email = "admin@sptech.school",
                token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZXZAc3B0ZWNoLnNjaG9vbCIsImlhdCI6MTY5ODA4NDY1NCwiZXhwIjoxNzAxNjg0NjU0fQ._ByXuksiF9C2K2Xu5OrAhquC2SHNfiAO7uut0pGEXN8JKzY8bzGksmQJQ6ICZIJ3uhladvK7NoDJyeS7iMrA0A",
                moedas = 500,
                nivel = 950,
                xp = 250,
                itensAdquiridos = listOf(
                    ItemAdquirido(
                        id = 1,
                        nomeItem = "Item 1",
                        fotoItem = "!",
                        tipoItem = "Imagem",
                        precoItem = 20.0,
                        descricaoItem = "Item 1",
                        equipado = false
                    ),
                    ItemAdquirido(
                        id = 2,
                        nomeItem = "Item 2",
                        fotoItem = "?",
                        tipoItem = "Imagem2",
                        precoItem = 20.0,
                        descricaoItem = "Item 2",
                        equipado = false
                    )
                )
            ),
            Usuario(
                id = 1,
                fotoPerfil = "", nome = "Administrador",
                email = "admin@sptech.school",
                token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZXZAc3B0ZWNoLnNjaG9vbCIsImlhdCI6MTY5ODA4NDY1NCwiZXhwIjoxNzAxNjg0NjU0fQ._ByXuksiF9C2K2Xu5OrAhquC2SHNfiAO7uut0pGEXN8JKzY8bzGksmQJQ6ICZIJ3uhladvK7NoDJyeS7iMrA0A",
                moedas = 500,
                nivel = 950,
                xp = 250,
                itensAdquiridos = listOf(
                    ItemAdquirido(
                        id = 1,
                        nomeItem = "Item 1",
                        fotoItem = "!",
                        tipoItem = "Imagem",
                        precoItem = 20.0,
                        descricaoItem = "Item 1",
                        equipado = false
                    ),
                    ItemAdquirido(
                        id = 2,
                        nomeItem = "Item 2",
                        fotoItem = "?",
                        tipoItem = "Imagem2",
                        precoItem = 20.0,
                        descricaoItem = "Item 2",
                        equipado = false
                    )
                )
            ),
            Usuario(
                id = 1,
                fotoPerfil = "", nome = "Administrador",
                email = "admin@sptech.school",
                token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZXZAc3B0ZWNoLnNjaG9vbCIsImlhdCI6MTY5ODA4NDY1NCwiZXhwIjoxNzAxNjg0NjU0fQ._ByXuksiF9C2K2Xu5OrAhquC2SHNfiAO7uut0pGEXN8JKzY8bzGksmQJQ6ICZIJ3uhladvK7NoDJyeS7iMrA0A",
                moedas = 500,
                nivel = 950,
                xp = 250,
                itensAdquiridos = listOf(
                    ItemAdquirido(
                        id = 1,
                        nomeItem = "Item 1",
                        fotoItem = "!",
                        tipoItem = "Imagem",
                        precoItem = 20.0,
                        descricaoItem = "Item 1",
                        equipado = false
                    ),
                    ItemAdquirido(
                        id = 2,
                        nomeItem = "Item 2",
                        fotoItem = "?",
                        tipoItem = "Imagem2",
                        precoItem = 20.0,
                        descricaoItem = "Item 2",
                        equipado = false
                    )
                )
            ),

            Usuario(
                id = 1,
                fotoPerfil = "", nome = "Administrador",
                email = "admin@sptech.school",
                token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZXZAc3B0ZWNoLnNjaG9vbCIsImlhdCI6MTY5ODA4NDY1NCwiZXhwIjoxNzAxNjg0NjU0fQ._ByXuksiF9C2K2Xu5OrAhquC2SHNfiAO7uut0pGEXN8JKzY8bzGksmQJQ6ICZIJ3uhladvK7NoDJyeS7iMrA0A",
                moedas = 500,
                nivel = 950,
                xp = 250,
                itensAdquiridos = listOf(
                    ItemAdquirido(
                        id = 1,
                        nomeItem = "Item 1",
                        fotoItem = "!",
                        tipoItem = "Imagem",
                        precoItem = 20.0,
                        descricaoItem = "Item 1",
                        equipado = false
                    ),
                    ItemAdquirido(
                        id = 2,
                        nomeItem = "Item 2",
                        fotoItem = "?",
                        tipoItem = "Imagem2",
                        precoItem = 20.0,
                        descricaoItem = "Item 2",
                        equipado = false
                    )
                )
            ),
            Usuario(
                id = 1,
                fotoPerfil = "", nome = "Administrador",
                email = "admin@sptech.school",
                token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZXZAc3B0ZWNoLnNjaG9vbCIsImlhdCI6MTY5ODA4NDY1NCwiZXhwIjoxNzAxNjg0NjU0fQ._ByXuksiF9C2K2Xu5OrAhquC2SHNfiAO7uut0pGEXN8JKzY8bzGksmQJQ6ICZIJ3uhladvK7NoDJyeS7iMrA0A",
                moedas = 500,
                nivel = 950,
                xp = 250,
                itensAdquiridos = listOf(
                    ItemAdquirido(
                        id = 1,
                        nomeItem = "Item 1",
                        fotoItem = "!",
                        tipoItem = "Imagem",
                        precoItem = 20.0,
                        descricaoItem = "Item 1",
                        equipado = false
                    ),
                    ItemAdquirido(
                        id = 2,
                        nomeItem = "Item 2",
                        fotoItem = "?",
                        tipoItem = "Imagem2",
                        precoItem = 20.0,
                        descricaoItem = "Item 2",
                        equipado = false
                    )
                )
            ),
            Usuario(
                id = 1,
                fotoPerfil = "", nome = "Administrador",
                email = "admin@sptech.school",
                token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZXZAc3B0ZWNoLnNjaG9vbCIsImlhdCI6MTY5ODA4NDY1NCwiZXhwIjoxNzAxNjg0NjU0fQ._ByXuksiF9C2K2Xu5OrAhquC2SHNfiAO7uut0pGEXN8JKzY8bzGksmQJQ6ICZIJ3uhladvK7NoDJyeS7iMrA0A",
                moedas = 500,
                nivel = 950,
                xp = 250,
                itensAdquiridos = listOf(
                    ItemAdquirido(
                        id = 1,
                        nomeItem = "Item 1",
                        fotoItem = "!",
                        tipoItem = "Imagem",
                        precoItem = 20.0,
                        descricaoItem = "Item 1",
                        equipado = false
                    ),
                    ItemAdquirido(
                        id = 2,
                        nomeItem = "Item 2",
                        fotoItem = "?",
                        tipoItem = "Imagem2",
                        precoItem = 20.0,
                        descricaoItem = "Item 2",
                        equipado = false
                    )
                )
            ),

            )
    }
    MenuPadrao(
        texto = "Ranking",
        conteudo = ({
            ListaAmigos(listaAmigos)
        })
    )
}

