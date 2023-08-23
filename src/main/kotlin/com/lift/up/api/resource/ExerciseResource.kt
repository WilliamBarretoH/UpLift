package com.lift.up.api.resource

import com.lift.up.api.dto.ExerciseDto
import com.lift.up.domain.entity.Exercise
import com.lift.up.domain.repository.ExerciseRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/exercise")
class ExerciseResource(
    private val exerciseRepository: ExerciseRepository
    ) {

    @Operation(summary = "Retorna a lista de exercicios da API", description = "Retorna lista de exercicios")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Sucesso"),
        ]
    )
    @GetMapping
    fun listAll() : ResponseEntity<List<ExerciseDto>> {
        val exercises = exerciseRepository.findAll()
        return ResponseEntity.ok().body(exercises.map { it.toExerciseDto() })
    }
    @GetMapping("/{id}")
    fun findById(@PathVariable(value = "id") id: Long) : ResponseEntity<Exercise> =
        ResponseEntity.ok().body(exerciseRepository.findById(id).get())

}