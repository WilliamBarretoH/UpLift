package com.lift.up.domain.entity

import javax.persistence.*

@Entity
@Table(name = "category")
class Category(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(
        name = "SQ_CATEGORY",
        initialValue = 1,
        allocationSize = 1,
        sequenceName = "SQ_CATEGORY_ID"
    )
    val id: Long,

    @Column
    val name: String,

)