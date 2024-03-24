package com.activity.activityrecord.controller.activity

import com.activity.activityrecord.AggregateUnit
import com.activity.activityrecord.service.activity.dto.AggregateKey
import java.time.LocalDate

data class AggregateActivityResponse(
        val aggregateUnit: AggregateUnit,
        val aggregateActivities: List<AggregateActivity>
) {
    companion object {
        fun of(request: AggregateActivityRequest,
               aggregateActivities: Map<AggregateKey, com.activity.activityrecord.service.activity.dto.AggregateActivity>)
                : AggregateActivityResponse {
            return AggregateActivityResponse(
                    aggregateUnit = request.aggregateUnit,
                    aggregateActivities = aggregateActivities.entries
                            .map { AggregateActivity.of(it) }
                            .sortedBy { a -> a.aggregateTerm }
                            .toList()
            )
        }
    }
}

data class AggregateActivity(
        val categoryId: Long,
        val categoryName: String,
        val subCategoryId: Long?,
        val subCategoryName: String?,
        val aggregateTerm: String,
        val aggregateStartDate: LocalDate,
        val aggregateEndDate: LocalDate,
        val activityCount: Int
) {
    companion object {
        fun of(aggregateActivity: Map.Entry<AggregateKey, com.activity.activityrecord.service.activity.dto.AggregateActivity>): AggregateActivity {
            return AggregateActivity(
                    categoryId = aggregateActivity.key.categoryId,
                    categoryName = aggregateActivity.value.categoryName,
                    subCategoryId = aggregateActivity.key.subCategoryId,
                    subCategoryName = aggregateActivity.value.subCategoryName,
                    aggregateTerm = aggregateActivity.value.aggregateTerm,
                    aggregateStartDate = aggregateActivity.value.aggregateStartDate,
                    aggregateEndDate = aggregateActivity.value.aggregateEndDate,
                    activityCount = aggregateActivity.value.activityCount
            )
        }
    }
}
