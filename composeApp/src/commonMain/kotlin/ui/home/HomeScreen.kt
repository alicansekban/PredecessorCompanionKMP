package ui.home
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = getKoin().get(),
    onHeroClicked: (String) -> Unit
){
    val homeScreenState by viewModel.homeViewState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getHeroes()
    }
    Column(modifier = Modifier.fillMaxSize()) {
        when (homeScreenState) {
            is HomeViewModel.HomeScreenState.Loading -> {
            }

            is HomeViewModel.HomeScreenState.Success -> {
                val heroes = (homeScreenState as HomeViewModel.HomeScreenState.Success).responseData
                HomeUIContent(heroes = heroes, onHeroClicked = onHeroClicked)
            }

            is HomeViewModel.HomeScreenState.Error -> {
                //show Error
            }
        }
    }
}

