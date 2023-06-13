package com.lift.up.service

import com.lift.up.api.dto.WorkoutCreateDto
import com.lift.up.api.dto.WorkoutDto
import com.lift.up.domain.entity.Workout
import com.lift.up.domain.entity.toWorkoutDto
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

    fun listAll(): List<WorkoutDto> {
        val workouts = workoutRepository.findAll()
        return workouts.map { it.toWorkoutDto() }
    }

    fun createWorkout(workoutCreateDto: WorkoutCreateDto) {
        val exercises = workoutCreateDto.exercises.stream().map {
            exerciseRepository.findById(it).orElseThrow()
        }.collect(Collectors.toList())
        val workout = Workout(
            name = workoutCreateDto.name,
            dateCreation = Date(),
            exercises = exercises,
                categories = workoutCreateDto.categories)
        workoutRepository.save(workout)
    }
}