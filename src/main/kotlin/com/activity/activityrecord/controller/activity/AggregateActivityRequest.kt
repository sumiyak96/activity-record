package com.activity.activityrecord.controller.activity

import com.activity.activityrecord.AggregateUnit
import java.time.LocalDate

data class AggregateActivityRequest(
        val categoryId: Long,
        val subCategoryId: Long?,
        val aggregateUnit: AggregateUnit,
        val fromDate: LocalDate?,
        val toDate: LocalDate?
)
