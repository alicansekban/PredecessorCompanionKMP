package ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import data.HeroesResponse
import utils.Constants

@Composable
fun HomeUIContent(
    modifier: Modifier = Modifier,
    heroes: List<HeroesResponse>,
    onHeroClicked: (String) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier.fillMaxSize()) {
        items(heroes) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable {
                onHeroClicked.invoke(it.display_name ?: "")
            }) {

                AsyncImage(
                    modifier = Modifier.size(50.dp).clip(RoundedCornerShape(5.dp)),
                    model = Constants.BASE_URL + it.image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Text(it.display_name ?: "")

            }


        }
    }

}