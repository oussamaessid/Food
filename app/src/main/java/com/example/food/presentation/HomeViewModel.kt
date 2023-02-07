package com.example.food.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfood.data.*
import com.example.food.data.model.CategoryDto
import com.example.food.di.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(): ViewModel() {

    private  var randomMealLiveData = MutableLiveData<MealDto>()
    private  var categoriesLiveData = MutableLiveData<List<CategoryDto>>()
    private  var popularItemsLiveData = MutableLiveData<List<MealsByCategoryDto>>()

    fun getRandomMeal(){
        RetrofitInstance.foodApi.getRandomMeal().enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null) {
                    randomMealLiveData.value = response.body()!!.meals[0]

                } else {
                    return
                }
            }
            override fun onFailure(call: Call<MealList>, t: Throwable) {

                Log.d("Search Fragment", t.message.toString())
            }
        })
    }

    fun getPopularItems(){
        RetrofitInstance.foodApi.getPopularItems("Seafood").enqueue(object : Callback<MealsByCategoryList> {
            override fun onResponse(call: Call<MealsByCategoryList>, response: Response<MealsByCategoryList>) {
                if (response.body() != null) {
                    popularItemsLiveData.value = response.body()!!.meals
                } else {
                    return
                }
            }
            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.d("Home Fragment", t.message.toString())            }

        })
    }

    fun getCategories(){
        RetrofitInstance.foodApi.getCategories().enqueue(object : Callback<CategoryList> {
            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                response.body()?.let { CategoryList ->
                    categoriesLiveData.postValue(CategoryList.categories)
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.d("HomeViewModel", t.message.toString())
            }

        })
    }

    fun observeRandomMealLiveData(): LiveData<MealDto> {
        return randomMealLiveData
    }

    fun observePopularItemsLiveData(): LiveData<List<MealsByCategoryDto>>{
        return popularItemsLiveData
    }

    fun observeCategoriesLiveData(): LiveData<List<CategoryDto>> {
        return categoriesLiveData
    }
}