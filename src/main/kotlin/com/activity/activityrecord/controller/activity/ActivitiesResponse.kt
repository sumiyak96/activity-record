package com.activity.activityrecord.controller.activity

import java.time.LocalDate

data class ActivitiesResponse(
        val activities: List<Activity>
) {

    data class Activity(
            val customerId: String,
            val categoryId: Long,
            val categoryName: String,
            val subCategoryId: Long?,
            val subCategoryName: String?,
            val eventDate: LocalDate,
            val memo: String?
    )

    companion object {
        fun of(activities: List<com.activity.activityrecord.entity.Activity>): ActivitiesResponse {
            val res = activities.map { activity ->
                Activity(
                        customerId = activity.customerId,
                        categoryId = activity.category.categoryId,
                        categoryName = activity.category.categoryName,
                        subCategoryId = activity.subCategory?.subCategoryId,
                        subCategoryName = activity.subCategory?.subCategoryName,
                        eventDate = activity.eventDate,
                        memo = activity.memo
                )
            }
            return ActivitiesResponse(res)
        }
    }
}