package com.lift.up.api.dto

import com.lift.up.domain.entity.SetWork


data class ExerciseDto(
        val exerciseId: Long?,
        val name: String,
        val categoryName : String?,
)
