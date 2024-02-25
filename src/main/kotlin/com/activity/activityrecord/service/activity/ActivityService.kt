package com.activity.activityrecord.service.activity

import com.activity.activityrecord.controller.activity.ActivityRegisterRequest
import com.activity.activityrecord.entity.Activity
import com.activity.activityrecord.repository.ActivityRepository
import com.activity.activityrecord.repository.CategoryRepository
import com.activity.activityrecord.repository.SubCategoryRepository
import org.springframework.stereotype.Service

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
}
