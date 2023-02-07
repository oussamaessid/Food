package com.example.food.data.mapper

import com.example.food.data.model.CategoryDto
import com.example.food.domain.model.Category

class CategoryMapper  constructor() :
    BaseMapper<CategoryDto, Category> {
    override fun map(from: CategoryDto): Category {
        return Category(
            idCategory = from.idCategory,
            strCategory = from.strCategory,
            strCategoryDescription = from.strCategoryDescription,
            strCategoryThumb = from.strCategoryThumb
        )
    }

    override fun mapInverse(from: Category): CategoryDto {
        return CategoryDto(
            idCategory = from.idCategory,
            strCategory = from.strCategory,
            strCategoryDescription = from.strCategoryDescription,
            strCategoryThumb = from.strCategoryThumb
        )
    }
}