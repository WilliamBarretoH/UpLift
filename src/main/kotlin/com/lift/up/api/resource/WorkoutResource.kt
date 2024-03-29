package com.lift.up.api.resource

import com.lift.up.api.dto.WorkoutCreateDto
import com.lift.up.api.dto.WorkoutDto
import com.lift.up.domain.entity.Workout
import com.lift.up.service.WorkoutService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/workout")
class WorkoutResource(
    private val workoutService: WorkoutService
    ) {

    @GetMapping
    fun listAll() : ResponseEntity<List<WorkoutDto>> =
        ResponseEntity.ok().body(workoutService.listAll())

    @PostMapping
    fun createWorkout(@RequestBody workout: WorkoutCreateDto){
        workoutService.createWorkout(workout)
        ResponseEntity.status(HttpStatus.CREATED)
    }
}