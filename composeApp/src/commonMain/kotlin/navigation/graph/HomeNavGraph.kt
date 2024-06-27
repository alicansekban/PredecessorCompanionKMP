package navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import ui.detail.DetailScreen
import ui.home.HomeScreen
import utils.ScreenRoutes


fun NavGraphBuilder.homeNavGraph(
    navController: NavController,
) {
    val navigation: (route: String) -> Unit = { route ->
        if (route == ScreenRoutes.BACK_PRESSED) {
            navController.popBackStack()
        } else {
            navController.navigate(route)
        }
    }

    navigation(
        startDestination = ScreenRoutes.HOME_ROUTE,
        route = ScreenRoutes.HOME_HOST_ROUTE
    ) {
        composable(ScreenRoutes.HOME_ROUTE) {
            HomeScreen(
                onHeroClicked = {
                    val route = "detail/{heroId}".replace(oldValue = "{heroId}", newValue = it)
                    navigation(route)
                }
            )
        }
        composable(
            "detail/{heroId}",
            arguments = listOf(
                navArgument("heroId") {
                    type = NavType.StringType
                }
            )
        ) {
            val heroId = it.arguments?.getString("heroId")
            DetailScreen(
                heroId = heroId ?: ""
            )
        }

    }
}