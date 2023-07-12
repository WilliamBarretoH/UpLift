package com.lift.up.domain.entity

import com.lift.up.domain.enums.KindWork
import javax.persistence.*

@Entity
@Table(name = "set_work")
class SetWork(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    var kindWork: KindWork,

    @Column
    var reps: Int
)