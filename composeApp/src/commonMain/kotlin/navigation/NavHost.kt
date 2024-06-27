package navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import navigation.graph.buildNavGraph
import navigation.graph.homeNavGraph
import navigation.graph.leaderBoardNavGraph
import navigation.graph.moreNavGraph
import navigation.graph.savedNavGraph
import ui.detail.DetailScreen
import ui.home.HomeScreen
import utils.ScreenRoutes

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = ScreenRoutes.HOME_HOST_ROUTE
        ) {
            homeNavGraph(
                navController = navController,
            )
            leaderBoardNavGraph(
                navController = navController,
            )
            savedNavGraph(
                navController = navController,
            )
            buildNavGraph(
                navController = navController,
            )
            moreNavGraph(
                navController = navController,
            )
        }
    }

}


@Composable
fun BottomBar(
    navController: NavController,
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    val navigation: (String) -> Unit = { route ->
        if (route == ScreenRoutes.BACK_PRESSED) {
            navController.popBackStack()
        } else {
            navController.navigate(route) {
                popUpTo(navController.graph.findStartDestination().route ?: "") {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }
        }
    }
    val items = listOf(
        BottomNavigationItem(
            title = "Heroes",
            selectedIcon = Icons.Filled.Home,
            unSelectedIcon = Icons.Outlined.Home,
            route = ScreenRoutes.HOME_ROUTE
        ),
        BottomNavigationItem(
            title = "Leaderboard",
            selectedIcon = Icons.Filled.Search,
            unSelectedIcon = Icons.Outlined.Search,
            route = ScreenRoutes.LEADER_BOARD_HOST_ROUTE
        ),
        BottomNavigationItem(
            title = "Saved",
            selectedIcon = Icons.Filled.Lock,
            unSelectedIcon = Icons.Outlined.Lock,
            route = ScreenRoutes.SAVED_HOST_ROUTE
        ),
        BottomNavigationItem(
            title = "Builds",
            selectedIcon = Icons.Filled.Build,
            unSelectedIcon = Icons.Outlined.Build,
            route = ScreenRoutes.BUILD_HOST_ROUTE
        ),
        BottomNavigationItem(
            title = "More",
            selectedIcon = Icons.Filled.MoreVert,
            unSelectedIcon = Icons.Outlined.MoreVert,
            route = ScreenRoutes.MORE_HOST_ROUTE
        )
    )
    val selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        NavigationBar {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                    onClick = {
                        navigation(item.route)
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 10.sp,
                        )
                    },
                    icon = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unSelectedIcon,
                            contentDescription = item.title,
                        )
                    },
                    interactionSource = NoRippleInteractionSource,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onBackground,
                        unselectedTextColor = MaterialTheme.colorScheme.onBackground,
                        indicatorColor = MaterialTheme.colorScheme.background
                    )
                )
            }

        }
    }
}

private object NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction) = true
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val route: String,
)