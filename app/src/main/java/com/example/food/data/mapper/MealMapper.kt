package com.example.food.data.mapper

import com.example.easyfood.data.Meal
import com.example.easyfood.data.MealDto
import com.example.food.data.model.CategoryDto
import com.example.food.domain.model.Category

class MealMapper constructor() :
    BaseMapper<MealDto, Meal> {
    override fun map(from: MealDto): Meal {
        TODO("Not yet implemented")
    }

    override fun mapInverse(from: Meal): MealDto {
        TODO("Not yet implemented")
    }

}