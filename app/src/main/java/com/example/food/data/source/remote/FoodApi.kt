package com.example.food.data.source.remote


import com.example.easyfood.data.*
import com.example.food.domain.model.Category
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface FoodApi {

    @GET ("random.php")
    fun getRandomMeal(): Call<MealList>

    @GET("lookup.php?")
    fun getMealDetail(@Query("i") id:String):Call<MealList>

    @GET("filter.php?")
    fun getPopularItems(@Query("c") category:String):Call<MealsByCategoryList>

    @GET("categories.php")
    fun getCategories(): Call<CategoryList>
}