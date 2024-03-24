package com.activity.activityrecord.data
import com.activity.activityrecord.entity.Activity
import com.activity.activityrecord.entity.Category
import com.activity.activityrecord.entity.Customer
import com.activity.activityrecord.entity.SubCategory
import com.activity.activityrecord.repository.ActivityRepository
import com.activity.activityrecord.repository.CategoryRepository
import com.activity.activityrecord.repository.CustomerRepository
import com.activity.activityrecord.repository.SubCategoryRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DataLoader(private val customerRepository: CustomerRepository,
                 private val categoryRepository: CategoryRepository,
                 private val subCategoryRepository: SubCategoryRepository,
                 private val activityRepository: ActivityRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        val customer1 = createCustomer()
        createCategory(customer1)
        createActivity(customer1, 1, 1, 365, 1)
        createActivity(customer1, 1, 2, 200, 2)
        createActivity(customer1, 2, 3, 50, 5)
        createActivity(customer1, 2, 4, 25, 8)
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

    fun createActivity(customer: Customer, categoryId: Long, subCategoryId: Long, days: Int, interval: Long) {
        val category = categoryRepository.findById(categoryId).get()
        val subCategory = subCategoryRepository.findById(subCategoryId).get()
        var basisDate = LocalDate.now()
        for(i in 1..days) {
            val activity = Activity(
                    customerId = customer.customerId,
                    category = category,
                    subCategory = subCategory,
                    eventDate = basisDate
            )
            basisDate = basisDate.minusDays(interval)
            activityRepository.save(activity)
        }
    }
}
