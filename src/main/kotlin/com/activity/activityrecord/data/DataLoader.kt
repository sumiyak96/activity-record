package com.activity.activityrecord.data
import com.activity.activityrecord.entity.Category
import com.activity.activityrecord.entity.Customer
import com.activity.activityrecord.entity.SubCategory
import com.activity.activityrecord.CategoryRepository
import com.activity.activityrecord.controller.repository.CustomerRepository
import com.activity.activityrecord.controller.repository.SubCategoryRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataLoader(private val customerRepository: CustomerRepository,
                 private val categoryRepository: CategoryRepository,
                 private val subCategoryRepository: SubCategoryRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        val customer1 = createCustomer()
        createCategory(customer1)
    }

    fun createCustomer(): Customer {
        val entity1 = Customer("user1", "userName1")
        return customerRepository.save(entity1)
    }

    fun createCategory(customer1: Customer) {
        val category1 = Category(categoryName = "サッカー・フットサル",
                customer = customer1)
        val subCategory1 = SubCategory(subCategoryName = "市ヶ谷サッカー",
                category = category1)
        val subCategory2 = SubCategory(subCategoryName = "個サル",
                category = category1)
        categoryRepository.save(category1)
        subCategoryRepository.save(subCategory1)
        subCategoryRepository.save(subCategory2)

        val category2 = Category(categoryName = "サウナ",
                customer = customer1)
        val subCategory3 = SubCategory(subCategoryName = "Smart Stay Shizuku 大井町",
                category = category2)
        val subCategory4 = SubCategory(subCategoryName = "パラダイス三田",
                category = category2)
        val subCategory5 = SubCategory(subCategoryName = "その他",
                category = category2)
        categoryRepository.save(category2)
        subCategoryRepository.save(subCategory3)
        subCategoryRepository.save(subCategory4)
        subCategoryRepository.save(subCategory5)
    }
}
