package com.lift.up.api.dto

import java.util.Date

data class WorkoutDto(
        val id: Long,
        val name: String,
        val dateCreation: String,
        val exercises: List<String>?
)
