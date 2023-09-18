package com.ktacademy.foodie.ui.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ktacademy.foodie.data.model.MealItem
import com.ktacademy.foodie.data.network.AppResource
import com.ktacademy.foodie.data.repository.FoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel(
    private val repository: FoodRepository
) : ViewModel() {

    init {
        initScreenDetails()
    }

    private val _homeState: MutableStateFlow<AppResource<MealItem>> =
        MutableStateFlow(AppResource.Loading())
    val homeState: StateFlow<AppResource<MealItem>> = _homeState

    private fun initScreenDetails(){
        viewModelScope.launch {
            try {
                Log.d("TAG", "initScreenDetails:  start")
                val mealItem = repository.getRandomFood()
                Log.d("TAG", "initScreenDetails:  FOOD")
                _homeState.emit(AppResource.Success(body = mealItem, status = "Success"))
            }catch (e: Exception){
                _homeState.emit(AppResource.Error(status = "An error occurred"))
            }
            Log.d("INIT SCREEN", "initScreenDetails: ${homeState.value}")
        }
    }

}