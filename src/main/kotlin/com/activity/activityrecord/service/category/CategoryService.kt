package com.activity.activityrecord.service.category

import com.activity.activityrecord.entity.Category
import com.activity.activityrecord.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

    fun getCategories(customerId: String): List<Category> {
        val res = categoryRepository.findByCustomerId(customerId)
        println(res)
        return res;
    }
}