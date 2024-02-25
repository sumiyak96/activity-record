package com.activity.activityrecord.repository

import com.activity.activityrecord.entity.Activity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ActivityRepository : JpaRepository<Activity, Long> {

    @Query("SELECT a FROM Activity a WHERE a.customerId = :customerId")
    fun findByCustomerId(customerId: String): List<Activity>
}