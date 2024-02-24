package com.activity.activityrecord.repository

import com.activity.activityrecord.entity.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.customer.id = :customerId")
    fun findByCustomerId(customerId: String): List<Category>
}