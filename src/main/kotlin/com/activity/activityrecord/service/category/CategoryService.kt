package com.activity.activityrecord.service.category

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

    fun saveSubCategory(subCategory: SubCategory) {
        subCategoryRepository.save(subCategory)
    }

}