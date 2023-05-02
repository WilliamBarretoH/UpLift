package com.lift.up.service

import com.lift.up.api.dto.WorkoutDto
import com.lift.up.domain.entity.Workout
import com.lift.up.domain.repository.ExerciseRepository
import com.lift.up.domain.repository.WorkoutRepository
import org.springframework.stereotype.Service
import java.util.Date
import java.util.stream.Collectors

@Service
class WorkoutService(
    private val workoutRepository: WorkoutRepository,
    private val exerciseRepository: ExerciseRepository
) {

    fun listAll() = workoutRepository.findAll().toList()

    fun createWorkout(workoutDto: WorkoutDto) {
        val exercises = workoutDto.exercisesDto.stream().map {
            exerciseRepository.findById(it.exerciseId).orElseThrow()
        }.collect(Collectors.toList())
        val workout = Workout(
            name = workoutDto.name,
            dateCreation = Date(),
            exercises = exercises)
        workoutRepository.save(workout)
    }
}