package com.activity.activityrecord.entity
import jakarta.persistence.*

@Entity
class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val categoryId: Long = 0,

        var categoryName: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "customerId", nullable = false)
        var customer: Customer,

        @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL], orphanRemoval = true)
        var subCategories: List<SubCategory> = mutableListOf()
)
