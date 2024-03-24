package com.activity.activityrecord.controller.category

import com.activity.activityrecord.entity.Category
import com.activity.activityrecord.entity.Customer

data class RegisterCategoryRequest(
        var categoryName: String = "",
)
{
    fun toEntity(customer: Customer): Category {
        return Category(
                categoryName = categoryName,
                customer = customer
        )
    }
}