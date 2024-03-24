package com.activity.activityrecord.service.activity.dto

import java.time.LocalDate

class AggregateActivity(
    val categoryId: Long,
    val categoryName: String,
    val subCategoryId: Long?,
    val subCategoryName: String?,
    val aggregateTerm: String,
    val aggregateStartDate: LocalDate,
    val aggregateEndDate: LocalDate,
    val activityCount: Int
)