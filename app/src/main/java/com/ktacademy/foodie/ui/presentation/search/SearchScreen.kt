package com.ktacademy.foodie.ui.presentation.search

import AppLoader
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentPasteSearch
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ktacademy.foodie.data.model.MealItem
import com.ktacademy.foodie.data.network.AppResource
import org.jetbrains.annotations.Async

@Composable
@ExperimentalMaterial3Api
fun SearchScreen(
    viewModel: SearchViewModel
) {

    val searchState: AppResource<List<MealItem>?>? by viewModel.searchState.collectAsState()
    val searchUiState: SearchUiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = searchUiState.searchTerm,
            onValueChange = { viewModel.searchAction(SearchActions.SearchTermChange(it)) },
            label = {
                Text(text = "Search Now")
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ContentPasteSearch,
                    contentDescription = null,
                    modifier = Modifier
                        .size(48.dp)
                        .clickable { viewModel.searchAction(SearchActions.SearchSubmit) }
                )
            })

        when(searchState){
            is AppResource.Error -> Text(text = searchState?.status?: "An error occurred")
            is AppResource.Loading -> AppLoader()
            else -> LazyColumn(
                modifier = Modifier.padding(vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {

                items(searchState?.data?: emptyList()){ mealItem ->
                    SearchResult(title = mealItem.name, image = mealItem.thumbnail)
                }

            }
        }



    }

}


@Composable
fun SearchResult(
    modifier: Modifier = Modifier,
    title: String,
    image: String,
) {

    Card(
        modifier = modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier.padding(16.dp)
        ) {

            AsyncImage(
                model = image,
                contentDescription = title,
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.Crop
            )

            Text(text = title, style = MaterialTheme.typography.bodyLarge)

        }

    }

}

