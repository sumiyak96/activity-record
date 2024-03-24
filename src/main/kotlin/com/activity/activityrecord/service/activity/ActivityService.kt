package com.activity.activityrecord.service.activity

import com.activity.activityrecord.AggregateUnit
import com.activity.activityrecord.controller.activity.ActivityRegisterRequest
import com.activity.activityrecord.controller.activity.AggregateActivityRequest
import com.activity.activityrecord.entity.Activity
import com.activity.activityrecord.repository.ActivityRepository
import com.activity.activityrecord.repository.CategoryRepository
import com.activity.activityrecord.repository.SubCategoryRepository
import com.activity.activityrecord.service.activity.dto.AggregateActivity
import com.activity.activityrecord.service.activity.dto.AggregateKey
import org.springframework.stereotype.Service
import java.time.YearMonth

@Service
class ActivityService(private val activityRepository: ActivityRepository,
                      private val categoryRepository: CategoryRepository,
                      private val subCategoryRepository: SubCategoryRepository) {
    fun register(request: ActivityRegisterRequest) {
        val category = categoryRepository.findById(request.categoryId)
                .orElseThrow()
        val subCategory = request.subCategoryId?.let { subCategoryRepository.findById(it) }
        activityRepository.save(request.toEntity(category, subCategory))
    }

    fun getActivities(customerId: String): List<Activity> {
        return activityRepository.findByCustomerId(customerId)
    }

    fun aggregateActivities(customerId: String, request: AggregateActivityRequest): Map<AggregateKey, AggregateActivity> {
        val activities = activityRepository.findByCustomerIdAndCategoryIdAndSubCategoryIdAndEventDateBetween(
                customerId, request.categoryId, request.subCategoryId, request.fromDate, request.toDate)
        return when(request.aggregateUnit) {
            AggregateUnit.DAY -> aggregateByMonth(activities)
            AggregateUnit.WEEK -> aggregateByMonth(activities)
            AggregateUnit.MONTH -> aggregateByMonth(activities)
            AggregateUnit.YEAR -> aggregateByMonth(activities)
        }
    }

    private fun aggregateByMonth(activities: List<Activity>): Map<AggregateKey, AggregateActivity> {
        return activities.groupBy {
            AggregateKey(
                    yearMonth = YearMonth.from(it.eventDate),
                    categoryId = it.category.categoryId,
                    subCategoryId = it.subCategory?.subCategoryId
            )
        }
                .mapValues { (key, value) ->
                    AggregateActivity(
                            categoryId = key.categoryId,
                            categoryName = value[0].category.categoryName,
                            subCategoryId = key.subCategoryId,
                            subCategoryName = value[0].subCategory?.subCategoryName,
                            aggregateTerm = key.yearMonth.toString(),
                            aggregateStartDate = key.yearMonth.atDay(1),
                            aggregateEndDate = key.yearMonth.atDay(key.yearMonth.lengthOfMonth()),
                            activityCount = value.size
                    )
                }
    }

}
