package com.lift.up.api.dto

import com.lift.up.domain.entity.SetWork

data class WorkoutDto(
        val id: Long,
        val name: String,
        val dateCreation: String,
        val exercises: List<String>?,
        val categories: String,
        val sets: List<SetWork>?
)
