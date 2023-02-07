package com.example.food.data.repository



import com.example.food.data.mapper.CategoryMapper
import com.example.food.data.source.remote.FoodApi
import com.example.food.domain.model.Category
import com.example.food.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow



class CategoryRepositoryImpl  constructor(
    private val api: FoodApi,
    private val categoryMapper: CategoryMapper,

) : CategoryRepository {
    override suspend fun getCategories(): Flow<List<Category>> {
        TODO("Not yet implemented")
    }

}
