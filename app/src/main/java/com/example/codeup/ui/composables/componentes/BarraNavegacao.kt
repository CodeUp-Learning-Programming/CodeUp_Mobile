
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.codeup.R
import com.example.codeup.data.Materia
import com.example.codeup.data.Usuario
import com.example.codeup.ui.TelasNavBar
import com.example.codeup.ui.TelasNavBar.TELA_MENU_AMIGOS
import com.example.codeup.ui.TelasNavBar.TELA_MENU_APRENDA
import com.example.codeup.ui.TelasNavBar.TELA_MENU_LOJA
import com.example.codeup.ui.TelasNavBar.TELA_MENU_PERFIL
import com.example.codeup.ui.TelasNavBar.TELA_MENU_RANKING
import com.example.codeup.ui.composables.componentes.TextoBranco
import com.example.codeup.ui.composables.tela.TelaMenuAmigos
import com.example.codeup.ui.composables.tela.TelaMenuAprenda
import com.example.codeup.ui.composables.tela.TelaMenuLoja
import com.example.codeup.ui.composables.tela.TelaMenuPerfil
import com.example.codeup.ui.composables.tela.TelaMenuRanking

@Composable
fun BarraNavegacao(navController: NavHostController, usuario: Usuario, materia: Materia) {
    val items = listOf(
        NavItem(TELA_MENU_APRENDA, R.drawable.icon_aprenda_selecionado, R.drawable.icon_aprenda, R.string.text_aprenda),
        NavItem(TELA_MENU_RANKING, R.drawable.icon_medalha_selecionado, R.drawable.icon_medalha, R.string.text_ranking),
        NavItem(TELA_MENU_AMIGOS, R.drawable.icon_amigos_selecionado, R.drawable.icon_amigos, R.string.text_amigos),
        NavItem(TELA_MENU_LOJA, R.drawable.icon_loja_selecionado, R.drawable.icon_loja, R.string.text_loja),
        NavItem(TELA_MENU_PERFIL, R.drawable.icon_usuario_selecionado, R.drawable.icon_usuario, R.string.text_perfil)
    )

    Box(modifier = Modifier
        .fillMaxSize()
        , contentAlignment = Alignment.BottomCenter) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp)
            .background(Color.Black)) {
            NavHost(
                navController = navController,
                startDestination = TELA_MENU_APRENDA.name,
                enterTransition = {
                    slideIntoContainer(
                        AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )
                },

                ) {
                items.forEach { item ->
                    composable(item.route.name) {
                        when (item.route) {
                            TELA_MENU_APRENDA -> TelaMenuAprenda(usuario = usuario, materia = materia)
                            TELA_MENU_RANKING -> TelaMenuRanking(usuario = usuario)
                            TELA_MENU_AMIGOS -> TelaMenuAmigos(usuario = usuario)
                            TELA_MENU_LOJA -> TelaMenuLoja(usuario = usuario)
                            TELA_MENU_PERFIL -> TelaMenuPerfil(usuario = usuario)
                        }
                    }
                }
            }
        }

        NavigationBar(items, navController.currentBackStackEntryAsState().value?.destination?.route, navController)
    }
}

@Composable
fun NavigationBar(items: List<NavItem>, currentRoute: String?, navController: NavHostController) {
    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .height(80.dp)
            .padding(top = 10.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                val isSelected = currentRoute == item.route.name
                NavigationIcon(item, isSelected, navController, currentRoute)
            }
        }
    }
}

@Composable
fun NavigationIcon(item: NavItem, isSelected: Boolean, navController: NavHostController, currentRoute: String?) {
    Column(
        modifier = Modifier
            .clickable(enabled = !isSelected) { // Desabilita o clique se já está na tela selecionada
                if (currentRoute != item.route.name) {
                    navController.navigate(item.route.name) {
                        // Configura a navegação para não recarregar a tela se já estiver no topo da pilha
                        launchSingleTop = true
                        // Restaura o estado quando reutilizar a tela
                        restoreState = true
                    }
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = if (isSelected) painterResource(id = item.iconSelected) else painterResource(id = item.iconUnselected),
            contentDescription = stringResource(item.contentDesc),
        )
        TextoBranco(texto = stringResource(item.contentDesc), tamanhoFonte = 12)
    }
}

data class NavItem(val route: TelasNavBar, val iconSelected: Int, val iconUnselected: Int, val contentDesc: Int)
