package com.activity.activityrecord.service.activity.dto

import java.time.YearMonth

data class AggregateKey(
        val basisPeriod: String,
        val categoryId: Long,
        val subCategoryId: Long?
)