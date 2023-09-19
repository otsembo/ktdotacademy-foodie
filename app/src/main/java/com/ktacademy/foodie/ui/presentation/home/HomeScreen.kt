package com.ktacademy.foodie.ui.presentation.home

import AppLoader
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ktacademy.foodie.data.model.MealItem
import com.ktacademy.foodie.data.network.AppResource


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel,
) {

    // UI States
    val homeState: AppResource<MealItem>? by homeViewModel.homeState.collectAsState()

    when(homeState){
        is AppResource.Error -> Text(text = "Something went wrong")
        is AppResource.Loading -> AppLoader()
        is AppResource.Success -> HomeDataView(mealItem = homeState?.data!!, modifier = modifier)
        null -> {}
    }




}

@Composable
fun HomeDataView(
    mealItem: MealItem,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {

        AsyncImage(
            model = mealItem.thumbnail,
            contentDescription = null,
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )

        Text(text = mealItem.name, style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.size(24.dp))

        Text(text = mealItem.instructions, style = MaterialTheme.typography.bodyMedium)

    }
}