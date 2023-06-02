package com.lift.up.domain.entity

import com.lift.up.api.dto.ExerciseDto
import javax.persistence.*

@Entity
@Table(name = "exercise")
class Exercise(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(
        name = "SQ_EXERCISE",
        initialValue = 1,
        allocationSize = 1,
        sequenceName = "SQ_EXERCISE_ID"
    )
    val id: Long,

    @Column
    val name: String,

    @ManyToOne
    @JoinColumn(name = "category_id")
    val category: Category,

    @Column
    val sets: Int? = null,

    @Column
    val reps: Float? = null
)
    fun Exercise.toExerciseDto() = ExerciseDto(
            exerciseId = this.id,
            name = this.name,
            categoryName = this.category.name
    )
