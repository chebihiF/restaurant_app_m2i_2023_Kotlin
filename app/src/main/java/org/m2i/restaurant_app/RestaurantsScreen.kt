package org.m2i.restaurant_app

import android.support.v4.os.IResultReceiver.Default
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.m2i.restaurant_app.ui.theme.Restaurant_appTheme

@Composable
fun RestaurantsScreen(){

   val viewModel: RestaurantViewModel = viewModel()

    LazyColumn(contentPadding = PaddingValues(
        vertical = 8.dp,
        horizontal = 8.dp
    )){
        items(viewModel.state.value){restaurant ->
            RestaurantItem(restaurant){id ->
                viewModel.toggleFavorite(id)
            }
        }
    }
}


@Composable
fun RestaurantItem(item: Restaurant, onClick: (id: Int) -> Unit ) {

    var icon = if (item.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            RestaurantIcon(Icons.Filled.Place, Modifier.weight(0.15f))
            RestaurantDetails(item.title, item.description, Modifier.weight(0.85f))
            RestaurantIcon(icon,Modifier.weight(0.15f)){
                onClick(item.id)
            }
        }
    }
}

@Composable
fun RestaurantDetails(title: String, description: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        Text(text = description)
    }
}

@Composable
fun RestaurantIcon(icon: ImageVector, modifier: Modifier, onClick: () -> Unit = {}) {
    Image(imageVector = icon, contentDescription = "Restaurant Icon", modifier= modifier
        .padding(8.dp)
        .clickable { onClick() })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    Restaurant_appTheme{
        RestaurantsScreen()
    }
}

