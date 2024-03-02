package com.activity.activityrecord.controller.activity

import com.activity.activityrecord.service.activity.ActivityService
import jakarta.persistence.EntityManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/activity")
@CrossOrigin
class ActivityController(private val activityService: ActivityService,
                         private val entityManager: EntityManager) {

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
}
