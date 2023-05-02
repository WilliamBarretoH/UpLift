package com.lift.up.api.resource

import com.lift.up.api.dto.WorkoutDto
import com.lift.up.domain.entity.Exercise
import com.lift.up.domain.entity.Workout
import com.lift.up.domain.repository.ExerciseRepository
import com.lift.up.service.WorkoutService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/exercise")
class ExerciseResource(
    private val exerciseRepository: ExerciseRepository
    ) {

    @GetMapping
    fun listAll() : ResponseEntity<List<Exercise>> =
        ResponseEntity.ok().body(exerciseRepository.findAll())

    @GetMapping("/{id}")
    fun findById(@PathVariable(value = "id") id: Long) : ResponseEntity<Exercise> =
        ResponseEntity.ok().body(exerciseRepository.findById(id).get())

}