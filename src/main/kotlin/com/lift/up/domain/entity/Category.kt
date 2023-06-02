package com.lift.up.domain.entity

import com.lift.up.api.dto.CategoryDto
import com.lift.up.api.dto.ExerciseCategoryListDto
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

    @OneToMany(mappedBy = "category")
    val exercises : List<Exercise>,

)
fun Category.toCategoryDto() = CategoryDto(
        categoryId = this.id,
        name = this.name,
        exercises = this.exercises.map {
            ExerciseCategoryListDto(it.id, it.name)
        }
)
