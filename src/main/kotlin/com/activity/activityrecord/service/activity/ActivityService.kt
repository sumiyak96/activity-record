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
import java.time.Year
import java.time.YearMonth
import java.time.temporal.TemporalAdjusters

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
            AggregateUnit.DAY -> aggregateByDay(activities)
            AggregateUnit.WEEK -> aggregateByWeek(activities)
            AggregateUnit.MONTH -> aggregateByMonth(activities)
            AggregateUnit.YEAR -> aggregateByYear(activities)
        }
    }

    private fun aggregateByDay(activities: List<Activity>): Map<AggregateKey, AggregateActivity> {
        return activities.groupBy {
            AggregateKey(
                    basisPeriod = it.eventDate.toString(),
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
                            aggregateTerm = key.basisPeriod,
                            aggregateStartDate = value[0].eventDate,
                            aggregateEndDate = value[0].eventDate,
                            activityCount = value.size
                    )
                }
    }

    private fun aggregateByWeek(activities: List<Activity>): Map<AggregateKey, AggregateActivity> {
        return activities.groupBy {
            AggregateKey(
                    basisPeriod = it.eventDate.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY)).toString(),
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
                            aggregateTerm = key.basisPeriod,
                            aggregateStartDate = value[0].eventDate.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.MONDAY)),
                            aggregateEndDate = value[0].eventDate.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY)),
                            activityCount = value.size
                    )
                }
    }

    private fun aggregateByMonth(activities: List<Activity>): Map<AggregateKey, AggregateActivity> {
        return activities.groupBy {
            AggregateKey(
                    basisPeriod = YearMonth.from(it.eventDate).toString(),
                    categoryId = it.category.categoryId,
                    subCategoryId = it.subCategory?.subCategoryId
            )
        }
                .mapValues { (key, value) ->
                    val yearMonth = YearMonth.from(value[0].eventDate)
                    AggregateActivity(
                            categoryId = key.categoryId,
                            categoryName = value[0].category.categoryName,
                            subCategoryId = key.subCategoryId,
                            subCategoryName = value[0].subCategory?.subCategoryName,
                            aggregateTerm = key.basisPeriod,
                            aggregateStartDate = yearMonth.atDay(1),
                            aggregateEndDate = yearMonth.atDay(yearMonth.lengthOfMonth()),
                            activityCount = value.size
                    )
                }
    }

    private fun aggregateByYear(activities: List<Activity>): Map<AggregateKey, AggregateActivity> {
        return activities.groupBy {
            AggregateKey(
                    basisPeriod = Year.from(it.eventDate).toString(),
                    categoryId = it.category.categoryId,
                    subCategoryId = it.subCategory?.subCategoryId
            )
        }
                .mapValues { (key, value) ->
                    val year = Year.from(value[0].eventDate)
                    AggregateActivity(
                            categoryId = key.categoryId,
                            categoryName = value[0].category.categoryName,
                            subCategoryId = key.subCategoryId,
                            subCategoryName = value[0].subCategory?.subCategoryName,
                            aggregateTerm = key.basisPeriod,
                            aggregateStartDate = year.atDay(1),
                            aggregateEndDate = year.atDay(year.length()),
                            activityCount = value.size
                    )
                }
    }

}
