package navigation.graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import ui.leaderboard.LeaderBoardScreen
import utils.ScreenRoutes


fun NavGraphBuilder.leaderBoardNavGraph(
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
        startDestination = ScreenRoutes.LEADER_BOARD_ROUTE,
        route = ScreenRoutes.LEADER_BOARD_HOST_ROUTE
    ) {
        composable(
            route = ScreenRoutes.LEADER_BOARD_ROUTE
        ) {
          LeaderBoardScreen()
        }
    }
}