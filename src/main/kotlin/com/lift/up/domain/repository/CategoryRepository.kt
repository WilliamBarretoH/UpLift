package com.lift.up.domain.repository

import com.lift.up.domain.entity.Category
import com.lift.up.domain.entity.Exercise
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Long> {
}