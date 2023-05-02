package com.lift.up.api.dto

data class WorkoutDto(
    val name: String,
    val exercisesDto: MutableList<ExerciseDto>
)
