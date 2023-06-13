package com.lift.up.api.dto

data class WorkoutCreateDto(
    val name: String,
    val exercises: List<Long>,
    val categories: String
)
