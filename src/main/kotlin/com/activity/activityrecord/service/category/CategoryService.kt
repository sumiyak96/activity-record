package com.activity.activityrecord.service.category

import com.activity.activityrecord.controller.category.UpdateCategoryRequest
import com.activity.activityrecord.controller.category.UpdateSubCategoryRequest
import com.activity.activityrecord.entity.Category
import com.activity.activityrecord.entity.SubCategory
import com.activity.activityrecord.repository.CategoryRepository
import com.activity.activityrecord.repository.SubCategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository,
        private val subCategoryRepository: SubCategoryRepository) {


    fun getCategory(categoryId: Long): Category {
        return categoryRepository.findById(categoryId).orElseThrow()
    }

    fun getCategories(customerId: String): List<Category> {
        return categoryRepository.findByCustomerId(customerId)
    }

    fun saveCategory(category: Category) {
        categoryRepository.save(category)
    }

    fun updateCategory(request: UpdateCategoryRequest) {
        val entity = categoryRepository.findById(request.categoryId).orElseThrow()
        entity.categoryName = request.categoryName
        categoryRepository.save(entity)
    }

    fun saveSubCategory(subCategory: SubCategory) {
        subCategoryRepository.save(subCategory)
    }

    fun updateSubCategory(request: UpdateSubCategoryRequest) {
        val entity = subCategoryRepository.findById(request.subCategoryId).orElseThrow()
        entity.subCategoryName = request.subCategoryName
        subCategoryRepository.save(entity)
    }
}