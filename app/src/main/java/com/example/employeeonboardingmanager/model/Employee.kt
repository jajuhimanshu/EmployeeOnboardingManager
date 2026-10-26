package com.example.employeeonboardingmanager.model

data class Employee(
    val employeeId: String = "",
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val department: String = "",
    val designation: String = "",
    val joiningDate: String = "",
    val reportingManager: String = "",
    val onboardingStatus: String = "",
    val createdAt: Long = 0L,
    val updatedAt: Long = 0L
)