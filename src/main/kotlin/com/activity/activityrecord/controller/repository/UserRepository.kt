package com.activity.activityrecord.controller.repository

import com.activity.activityrecord.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, String> {
}