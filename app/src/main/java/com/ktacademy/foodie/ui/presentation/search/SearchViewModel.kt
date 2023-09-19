package com.ktacademy.foodie.ui.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ktacademy.foodie.data.model.MealItem
import com.ktacademy.foodie.data.network.AppResource
import com.ktacademy.foodie.data.repository.FoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: FoodRepository
) : ViewModel() {

    private val _searchState: MutableStateFlow<AppResource<List<MealItem>?>?> =
        MutableStateFlow(null)
    val searchState: StateFlow<AppResource<List<MealItem>?>?> = _searchState

    val uiState = MutableStateFlow(SearchUiState())

    fun searchAction(actions: SearchActions){
        when(actions){
            SearchActions.SearchSubmit -> {
                searchMeal(searchTerm = uiState.value.searchTerm)
            }
            is SearchActions.SearchTermChange -> {
                uiState.update {
                    it.copy(searchTerm = actions.search)
                }
            }
        }
    }

    private fun searchMeal(searchTerm: String){
        viewModelScope.launch {
            _searchState.emit(AppResource.Loading())
            try {
                val mealItems = repository.searchFood(searchTerm)
                _searchState.emit(AppResource.Success(body = mealItems, status = "Meals"))
            }catch (e: Exception){
                _searchState.emit(AppResource.Error(status = "An error occurred\n${e.localizedMessage}"))
            }
        }
    }

}

sealed class SearchActions {
    data class SearchTermChange(val search: String): SearchActions()
    object SearchSubmit : SearchActions()
}

data class SearchUiState(
    val searchTerm: String = ""
)



