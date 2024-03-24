package com.activity.activityrecord.controller.category

import com.activity.activityrecord.entity.Category
import com.activity.activityrecord.entity.Customer

data class UpdateCategoryRequest(
        var categoryId: Long,
        var categoryName: String,
) {
    fun toEntity(customer: Customer): Category {
        return Category(
                categoryId = categoryId,
                categoryName = categoryName,
                customer = customer
        )
    }
}