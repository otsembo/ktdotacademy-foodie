package com.ktacademy.foodie.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ktacademy.foodie.data.model.MealItem
import com.ktacademy.foodie.data.network.AppResource
import com.ktacademy.foodie.data.repository.FoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: FoodRepository
) : ViewModel() {

    init {
        initScreenDetails()
    }

    private val _homeState: MutableStateFlow<AppResource<MealItem>?> =
        MutableStateFlow(AppResource.Loading())
    val homeState: StateFlow<AppResource<MealItem>?> = _homeState

    private fun initScreenDetails(){
        viewModelScope.launch {
            try {
                val mealItem = repository.getRandomFood()
                _homeState.emit(AppResource.Success(body = mealItem, status = "Success"))
            }catch (e: Exception){
                _homeState.emit(AppResource.Error(status = "An error occurred"))
            }
        }
    }

}