package ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.HeroesResponse

@Composable
fun HomeUIContent(
    modifier: Modifier = Modifier,
    heroes: List<HeroesResponse>,
    onHeroClicked: (String) -> Unit
) {

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(heroes) {
            Text(it.display_name ?: "", modifier.clickable {
                onHeroClicked(it.display_name ?: "")
            })
        }
    }

}