package com.example.employeeonboardingmanager.model

data class EmployeeDocument(
    val documentId: String = "",
    val employeeId: String = "",
    val documentType: String = "",
    val status: String = "",
    val reviewComments: String = "",
    val submittedAt: Long = 0L,
    val verifiedAt: Long = 0L
)