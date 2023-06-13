package com.lift.up.domain.entity

import com.lift.up.api.dto.WorkoutDto
import java.util.Date
import javax.persistence.*

@Entity
@Table(name = "workout")
class Workout (

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @Column
    val name: String,

    @Column
    val dateCreation: Date,

    @ManyToMany
    val exercises: MutableList<Exercise>? = null,

    @Column
    val categories : String?
)
fun Workout.toWorkoutDto() = WorkoutDto(
        id = this.id!!,
        name = this.name,
        dateCreation = this.dateCreation.toString(),
        exercises = this.exercises?.map { it.name },
        categories = this.categories!!


)
