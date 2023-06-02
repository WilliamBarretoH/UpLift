package com.lift.up.api.resource

import com.lift.up.api.dto.CategoryDto
import com.lift.up.domain.entity.*
import com.lift.up.domain.repository.CategoryRepository
import com.lift.up.service.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/category")
class CategoryResource(
    private val categoryService: CategoryService
    ) {

    @GetMapping
    fun listAll() : ResponseEntity<List<CategoryDto>> = ResponseEntity.ok().body(categoryService.listCategories())

}