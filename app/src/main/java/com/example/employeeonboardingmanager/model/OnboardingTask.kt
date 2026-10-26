package com.example.employeeonboardingmanager.model

data class OnboardingTask(
    val taskId: String = "",
    val employeeId: String = "",
    val title: String = "",
    val description: String = "",
    val dueDate: String = "",
    val status: String = "",
    val completionDate: String = ""
)