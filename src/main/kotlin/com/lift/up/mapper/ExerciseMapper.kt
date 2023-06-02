package com.lift.up.mapper

import com.lift.up.api.dto.ExerciseDto
import com.lift.up.domain.entity.Exercise

class ExerciseMapper : Mapper<Exercise, ExerciseDto> {
    override fun to(obj: Exercise): ExerciseDto {
        TODO("Not yet implemented")

    }

    override fun from(obj: ExerciseDto): Exercise {
        TODO("Not yet implemented")
    }
}