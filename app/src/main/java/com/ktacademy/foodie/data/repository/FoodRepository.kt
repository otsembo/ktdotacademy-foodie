package com.ktacademy.foodie.data.repository

import android.util.Log
import com.ktacademy.foodie.data.model.MealItem
import com.ktacademy.foodie.data.network.MealApi
import com.ktacademy.foodie.data.network.MealApiBuilder


class FoodRepository(
    private val mealAPI: MealApiBuilder = MealApi.mealApi
) {

    suspend fun searchFood(searchTerm: String): List<MealItem>? {
        return mealAPI
            .searchMeal(searchTerm).meals?.map { it.toMealItem() }
    }

    suspend fun getRandomFood(): MealItem {
        return mealAPI
            .fetchRandomMeal().meals?.first()?.toMealItem()!!
    }
}