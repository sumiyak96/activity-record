package com.activity.activityrecord.controller.category

import com.activity.activityrecord.service.activity.CategoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("/api/category")
@CrossOrigin
class CategoryController(private val categoryService: CategoryService) {

    @GetMapping("/categories")
    fun categories(@ModelAttribute request: CategoriesRequest): ResponseEntity<CategoriesResponse> {

        val res = CategoriesResponse.of(categoryService.getCategories(request.customerId))
        return ResponseEntity(res, HttpStatus.OK)
    }
}
