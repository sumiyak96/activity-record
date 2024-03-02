package com.activity.activityrecord.controller.category

import com.activity.activityrecord.entity.Customer
import com.activity.activityrecord.service.category.CategoryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("/api")
@CrossOrigin
class CategoryController(private val categoryService: CategoryService,
        private val customer: Customer = Customer("user1", "user1")) {

    @GetMapping("/category/categories")
    fun categories(@ModelAttribute request: CategoriesRequest): ResponseEntity<CategoriesResponse> {
        val res = CategoriesResponse.of(categoryService.getCategories(request.customerId))
        return ResponseEntity(res, HttpStatus.OK)
    }

    @PostMapping("/category/register")
    fun registerCategory(@RequestBody request: RegisterCategoryRequest): ResponseEntity<Void> {
        categoryService.saveCategory(request.toEntity(customer))
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/category/update")
    fun updateCategory(@RequestBody request: UpdateCategoryRequest): ResponseEntity<Void> {
        categoryService.saveCategory(request.toEntity(customer))
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/subCategory/register")
    fun registerSubCategory(@RequestBody request: RegisterSubCategoryRequest): ResponseEntity<Void> {
        val category = categoryService.getCategory(request.categoryId)
        categoryService.saveSubCategory(request.toEntity(category))
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/subCategory/update")
    fun updateSubCategory(@RequestBody request: UpdateSubCategoryRequest): ResponseEntity<Void> {
        val category = categoryService.getCategory(request.categoryId)
        categoryService.saveSubCategory(request.toEntity(category))
        return ResponseEntity(HttpStatus.OK)
    }

}
