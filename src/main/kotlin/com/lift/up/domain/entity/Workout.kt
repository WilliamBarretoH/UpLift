package com.lift.up.domain.entity

import lombok.Data
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

    @Column
    val exercises: List<Exercise>? = null
)
