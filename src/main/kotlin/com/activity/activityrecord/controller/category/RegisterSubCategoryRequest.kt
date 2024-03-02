package com.activity.activityrecord.controller.category

import com.activity.activityrecord.entity.Activity
import com.activity.activityrecord.entity.Category
import com.activity.activityrecord.entity.Customer
import com.activity.activityrecord.entity.SubCategory
import java.time.LocalDate
import java.util.*

data class RegisterSubCategoryRequest(
        var categoryId: Long,
        var subCategoryName: String,
) {
    fun toEntity(category: Category): SubCategory {
        return SubCategory(
                subCategoryName = subCategoryName,
                category = category
        )
    }
}