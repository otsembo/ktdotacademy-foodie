package com.ktacademy.foodie.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class NetworkResponse(
    val meals: List<Meal>?
)

