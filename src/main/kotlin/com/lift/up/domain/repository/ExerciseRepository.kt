package com.lift.up.domain.repository

import com.lift.up.domain.entity.Exercise
import org.springframework.data.jpa.repository.JpaRepository

interface ExerciseRepository : JpaRepository<Exercise, Long> {
}