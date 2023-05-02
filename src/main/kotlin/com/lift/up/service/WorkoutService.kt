package com.lift.up.service

import com.lift.up.domain.repository.WorkoutRepository
import org.springframework.stereotype.Service

@Service
class WorkoutService(
    private val workoutRepository: WorkoutRepository
) {

    fun listAll() = workoutRepository.findAll().toList()
}