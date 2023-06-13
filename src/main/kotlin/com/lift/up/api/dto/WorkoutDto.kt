package com.lift.up.api.dto

data class WorkoutDto(
        val id: Long,
        val name: String,
        val dateCreation: String,
        val exercises: List<String>?,
        val categories: String
)
