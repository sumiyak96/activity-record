package com.activity.activityrecord.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Customer(
        @Id
        val customerId: String,

        var customername: String
)
