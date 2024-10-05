import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.compose.LocalPlatformContext
import coil3.request.crossfade
import coil3.util.DebugLogger
import navigation.Navigation
import org.koin.compose.KoinContext

@Composable
fun App() {

    getAsyncImageLoader(LocalPlatformContext.current)
    val navController = rememberNavController()
    MaterialTheme {
        KoinContext {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Navigation(
                    navController = navController
                )
            }
        }
    }

}

fun getAsyncImageLoader(context: PlatformContext) =
    ImageLoader.Builder(context).crossfade(true).logger(DebugLogger()).build()