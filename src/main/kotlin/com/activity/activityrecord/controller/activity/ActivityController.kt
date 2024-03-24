package com.activity.activityrecord.controller.activity

import com.activity.activityrecord.entity.Customer
import com.activity.activityrecord.service.activity.ActivityService
import jakarta.persistence.EntityManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/activity")
@CrossOrigin
class ActivityController(private val activityService: ActivityService,
                         private val entityManager: EntityManager,
                         private val customer: Customer = Customer("user1", "user1")) {

    @PostMapping("/register")
    fun createActivityRecord(@RequestBody request: ActivityRegisterRequest): ResponseEntity<ActivityRegisterResponse> {
        activityService.register(request)
        return ResponseEntity(ActivityRegisterResponse(), HttpStatus.OK)
    }

    @GetMapping("/activities")
    fun activities(@ModelAttribute request: ActivitiesRequest): ResponseEntity<ActivitiesResponse> {
        val activities = activityService.getActivities(request.customerId)
        return ResponseEntity(ActivitiesResponse.of(activities), HttpStatus.OK)
    }

    @GetMapping("/aggregate")
    fun aggregate(@ModelAttribute request: AggregateActivityRequest): ResponseEntity<AggregateActivityResponse> {
        val aggregateActivities = activityService.aggregateActivities(customer.customerId, request)
        return ResponseEntity(AggregateActivityResponse.of(request, aggregateActivities), HttpStatus.OK)
    }
}
