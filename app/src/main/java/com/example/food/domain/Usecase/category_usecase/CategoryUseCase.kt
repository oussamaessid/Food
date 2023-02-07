package com.app.eatmeet.domain.usecase.category_usecase


import com.bumptech.glide.load.engine.Resource
import com.example.easyfood.data.CategoryList
import com.example.food.domain.model.Category
import com.example.food.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class CategoryUseCase
 constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(): Flow<List<Category>> = repository.getCategories()
}