package com.lift.up.service

import com.lift.up.api.dto.CategoryDto
import com.lift.up.domain.entity.toCategoryDto
import com.lift.up.domain.repository.CategoryRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class CategoryService(
        val categoryRepository: CategoryRepository
) {

    fun listCategories(): List<CategoryDto> {
        val categories = categoryRepository.findAll()
        return categories.map { it.toCategoryDto() }
    }
}