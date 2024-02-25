package com.activity.activityrecord.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Activity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val activityId: Long = 0,

        @Column(name = "customer_id")
        val customerId: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "category_id", referencedColumnName = "categoryId")
        val category: Category,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "sub_category_id", referencedColumnName = "subCategoryId")
        val subCategory: SubCategory? = null,

        @Column(name = "event_date")
        val eventDate: LocalDate,

        @Column
        val memo: String? = null
)
