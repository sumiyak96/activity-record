package com.activity.activityrecord.entity
import jakarta.persistence.*

@Entity
class SubCategory(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val subCategoryId: Long = 0,

        var subCategoryName: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "categoryId", nullable = false)
        var category: Category
)
