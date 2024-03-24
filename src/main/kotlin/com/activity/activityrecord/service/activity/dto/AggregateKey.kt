package com.activity.activityrecord.service.activity.dto

import java.time.YearMonth

data class AggregateKey(
        val yearMonth: YearMonth,
        val categoryId: Long,
        val subCategoryId: Long?
)