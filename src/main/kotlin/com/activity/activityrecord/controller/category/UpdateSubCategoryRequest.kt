package com.activity.activityrecord.controller.category

import com.activity.activityrecord.entity.Activity
import com.activity.activityrecord.entity.Category
import com.activity.activityrecord.entity.Customer
import com.activity.activityrecord.entity.SubCategory
import java.time.LocalDate
import java.util.*

data class UpdateSubCategoryRequest(
        var subCategoryId: Long,
        var subCategoryName: String,
)
