package com.lift.up.api.dto


data class ExerciseDto(
        val exerciseId: Long? = null,
        val name: String,
        val categoryName : String?
)
