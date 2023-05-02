package com.lift.up.domain.entity

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
    val category: Category,

    @Column
    val sets: Int? = null,

    @Column
    val reps: Float? = null
)