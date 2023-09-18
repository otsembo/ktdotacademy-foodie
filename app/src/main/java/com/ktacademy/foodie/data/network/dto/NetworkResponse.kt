package com.ktacademy.foodie.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkResponse(
    @SerialName("meals")
    val meals: List<Meal>?
)

