import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import di.appModule
import navigation.Navigation
import org.koin.compose.KoinApplication
import ui.detail.DetailScreen
import ui.home.HomeScreen

@Composable
fun App() {
    KoinApplication(application = {
        modules(appModule())
    }) {

        val navController = rememberNavController()
        MaterialTheme {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Navigation(
                    navController = navController
                )
            }
        }
    }
}