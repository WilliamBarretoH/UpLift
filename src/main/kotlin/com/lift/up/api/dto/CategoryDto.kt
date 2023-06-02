package com.lift.up.api.dto


data class CategoryDto(
        val categoryId: Long? = null,
        val name: String,
        val exercises : List<ExerciseCategoryListDto>
)
