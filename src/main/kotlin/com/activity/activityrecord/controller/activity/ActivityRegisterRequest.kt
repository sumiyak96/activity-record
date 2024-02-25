package com.activity.activityrecord.controller.activity

import com.activity.activityrecord.entity.Activity
import com.activity.activityrecord.entity.Category
import com.activity.activityrecord.entity.SubCategory
import java.time.LocalDate
import java.util.*

data class ActivityRegisterRequest(
        var userId: String,
        var categoryId: Long,
        var subCategoryId: Long?,
        var eventDate: LocalDate,
        var memo: String
) {
    fun toEntity(category: Category, subCategory: Optional<SubCategory>?): Activity {
        return Activity(
                customerId = this.userId,
                category = category,
                subCategory = subCategory?.orElse(null),
                eventDate = this.eventDate,
                memo = this.memo
        )
    }
}