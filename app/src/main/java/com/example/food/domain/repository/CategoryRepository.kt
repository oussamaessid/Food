package com.example.food.domain.repository


import com.bumptech.glide.load.engine.Resource
import com.example.easyfood.data.CategoryList
import com.example.food.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories(): Flow<List<Category>>
}