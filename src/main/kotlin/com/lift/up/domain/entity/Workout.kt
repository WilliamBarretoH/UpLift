package com.lift.up.domain.entity

import com.lift.up.api.dto.ExerciseDto
import com.lift.up.api.dto.WorkoutDto
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "workout")
class Workout(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val name: String,

    @Column
    val dateCreation: Date,

    @ManyToMany
    val exercises: MutableList<Exercise>?,

    @Column
    val categories: String?,

) {
    fun toWorkoutDto() = WorkoutDto(
        id = this.id!!,
        name = this.name,
        dateCreation = this.dateCreation.toString(),
        exercises = this.exercises?.map { ExerciseDto(name = it.name, sets = it.sets, categoryName = it.category.name, exerciseId = it.id) },
        categories = this.categories!!

    )
}


