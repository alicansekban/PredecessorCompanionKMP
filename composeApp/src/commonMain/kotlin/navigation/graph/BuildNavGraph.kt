package navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import ui.builds.BuildsScreen
import utils.ScreenRoutes


fun NavGraphBuilder.buildNavGraph(
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
        startDestination = ScreenRoutes.BUILD_ROUTE,
        route = ScreenRoutes.BUILD_HOST_ROUTE
    ) {
        composable(
            route = ScreenRoutes.BUILD_ROUTE
        ) {
            BuildsScreen()
        }

    }
}