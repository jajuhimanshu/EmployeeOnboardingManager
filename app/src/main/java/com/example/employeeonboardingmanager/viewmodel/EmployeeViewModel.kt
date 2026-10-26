package com.example.employeeonboardingmanager.viewmodel

import androidx.lifecycle.ViewModel
import com.example.employeeonboardingmanager.model.Employee
import com.example.employeeonboardingmanager.repository.EmployeeRepository

class EmployeeViewModel : ViewModel() {

    private val repository = EmployeeRepository()

    fun addEmployee(
        employee: Employee,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        repository.addEmployee(
            employee,
            onSuccess,
            onFailure
        )
    }

    fun getEmployees(
        onSuccess: (List<Employee>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        repository.getEmployees(
            onSuccess,
            onFailure
        )
    }

    fun getEmployeeById(
        employeeId: String,
        onSuccess: (Employee) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        repository.getEmployeeById(
            employeeId,
            onSuccess,
            onFailure
        )
    }
    fun updateEmployee(
        employee: Employee,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        repository.updateEmployee(
            employee,
            onSuccess,
            onFailure
        )
    }

    fun getEmployeeCount(
        onSuccess: (Int) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        repository.getEmployeeCount(
            onSuccess,
            onFailure
        )
    }
}

