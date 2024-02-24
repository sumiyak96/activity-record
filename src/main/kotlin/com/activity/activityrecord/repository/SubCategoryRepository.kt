package com.activity.activityrecord.repository

import com.activity.activityrecord.entity.SubCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubCategoryRepository : JpaRepository<SubCategory, Long> {
}