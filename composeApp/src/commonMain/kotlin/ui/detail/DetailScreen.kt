package ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.compose.getKoin

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    heroId : String,
    viewModel: DetailViewModel = getKoin().get()
) {

    LaunchedEffect(heroId) {
        viewModel.getHeroDetail(heroName = heroId)
    }
    val state by viewModel.homeViewState.collectAsState()
    Column(modifier = modifier.fillMaxSize()) {
        when (state) {
            is DetailViewModel.DetailScreenState.Error -> {
                Text(text = (state as DetailViewModel.DetailScreenState.Error).errorMessage)

            }
            DetailViewModel.DetailScreenState.Loading -> {}
            is DetailViewModel.DetailScreenState.Success -> {

                Text(text = (state as DetailViewModel.DetailScreenState.Success).responseData.display_name ?: "")
            }
        }
    }
}