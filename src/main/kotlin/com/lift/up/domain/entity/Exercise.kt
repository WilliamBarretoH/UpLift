package com.lift.up.domain.entity

import javax.persistence.*

@Entity
@Table(name = "exercise")
class Exercise(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,

    @Column
    val name: String,

    @Column
    val sets: Int? = null,

    @Column
    val reps: Float? = null
)