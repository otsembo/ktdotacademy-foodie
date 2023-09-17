package com.ktacademy.foodie.ui.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ktacademy.foodie.data.model.MealItem
import com.ktacademy.foodie.data.network.AppResource
import com.ktacademy.foodie.data.repository.FoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: FoodRepository
) : ViewModel() {

    private val _searchState: MutableStateFlow<AppResource<List<MealItem>?>?> =
        MutableStateFlow(null)
    val searchState: StateFlow<AppResource<List<MealItem>?>?> = _searchState

    fun searchMeal(searchTerm: String){
        viewModelScope.launch {
            try {
                val mealItems = repository.searchFood(searchTerm)
                _searchState.emit(AppResource.Success(body = mealItems, status = "Meals"))
            }catch (e: Exception){
                _searchState.emit(AppResource.Error(status = "An error occurred\n${e.localizedMessage}"))
            }
        }
    }

}