package com.example.employeeonboardingmanager.model

data class InductionActivity(
    val activityId: String = "",
    val title: String = "",
    val description: String = "",
    val scheduledDate: String = "",
    val startTime: String = "",
    val duration: String = "",
    val employeeIds: List<String> = emptyList(),
    val status: String = ""
)