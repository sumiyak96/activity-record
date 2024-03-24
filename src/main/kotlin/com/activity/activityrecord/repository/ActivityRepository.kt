package com.activity.activityrecord.repository

import com.activity.activityrecord.entity.Activity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface ActivityRepository : JpaRepository<Activity, Long> {

    @Query("SELECT a FROM Activity a WHERE a.customerId = :customerId")
    fun findByCustomerId(customerId: String): List<Activity>

    @Query("SELECT a FROM Activity a " +
            "WHERE a.customerId = :customerId " +
            "AND a.category.categoryId = :categoryId " +
            "AND (:subCategoryId IS NULL OR a.subCategory.subCategoryId = :subCategoryId) " +
            "AND (:fromDate IS NULL OR a.eventDate >= :fromDate) " +
            "AND (:toDate IS NULL OR a.eventDate <= :toDate)")
    fun findByCustomerIdAndCategoryIdAndSubCategoryIdAndEventDateBetween(
            customerId: String,
            categoryId: Long,
            subCategoryId: Long?,
            fromDate: LocalDate?,
            toDate: LocalDate?
    ): List<Activity>
}