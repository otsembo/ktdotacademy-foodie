package com.ktacademy.foodie.data.network

import com.ktacademy.foodie.data.network.dto.NetworkResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

object MealApi {
    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
    private val retrofit: Retrofit  = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient()
                .newBuilder()
                .callTimeout(timeout = 5000, unit = TimeUnit.MILLISECONDS)
                .build())
        .build()

    val mealApi: MealApiBuilder = retrofit.create(MealApiBuilder::class.java)
}

interface MealApiBuilder {
    @GET("/random.php")
    suspend fun fetchRandomMeal(): NetworkResponse

    @GET("/search.php")
    suspend fun searchMeal(@Query("s") query: String): NetworkResponse
}