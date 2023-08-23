package com.lift.up.domain.entity

import com.lift.up.api.dto.ExerciseDto
import javax.persistence.*

@Entity
@Table(name = "exercise")
class Exercise(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column
    val name: String,

    @ManyToOne
    @JoinColumn(name = "category_id")
    val category: Category,

    @OneToMany
    val sets: List<SetWork> = emptyList(),
) {
    fun toExerciseDto() = ExerciseDto(
        exerciseId = this.id,
        name = this.name,
        categoryName = this.category.name,
        sets = this.sets
    )
}


