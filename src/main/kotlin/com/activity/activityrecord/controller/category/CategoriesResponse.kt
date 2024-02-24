package com.activity.activityrecord.controller.category

data class CategoriesResponse(
        val categories: List<Category>
) {
    companion object {
        fun of(categoryEntities: List<com.activity.activityrecord.entity.Category>): CategoriesResponse {
            val categories = categoryEntities.map { entity ->
                Category(
                        categoryId = entity.categoryId,
                        categoryName = entity.categoryName,
                        subCategories = entity.subCategories.map { subCategory ->
                            Category.SubCategory(
                                    subCategoryId = subCategory.subCategoryId,
                                    subCategoryName = subCategory.subCategoryName
                            )
                        }
                )
            }
            return CategoriesResponse(categories)
        }
    }

    data class Category(
            val categoryId: Long,
            val categoryName: String,
            val subCategories: List<SubCategory>
    ) {

        data class SubCategory(
                val subCategoryId: Long,
                val subCategoryName: String,
        )
    }
}