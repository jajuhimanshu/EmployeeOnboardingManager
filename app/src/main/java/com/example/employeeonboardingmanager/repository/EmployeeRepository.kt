package com.example.employeeonboardingmanager.repository

import com.example.employeeonboardingmanager.model.Employee
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.iterator
import kotlin.io.iterator
import kotlin.text.get
import kotlin.text.iterator

class EmployeeRepository {

    private val db = FirebaseFirestore.getInstance()
    private val employeeCollection = db.collection("Employees")

    fun addEmployee(
        employee: Employee,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        employeeCollection
            .document(employee.employeeId)
            .set(employee)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }


    fun getEmployees(
        onSuccess: (List<Employee>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        employeeCollection
            .get()
            .addOnSuccessListener { result ->

                val employeeList = mutableListOf<Employee>()

                for (document in result) {
                    val employee =
                        document.toObject(Employee::class.java)

                    employeeList.add(employee)
                }

                onSuccess(employeeList)
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }


    fun getEmployeeById(
        employeeId: String,
        onSuccess: (Employee) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        employeeCollection
            .document(employeeId)
            .get()
            .addOnSuccessListener {

                val employee =
                    it.toObject(Employee::class.java)

                if (employee != null) {
                    onSuccess(employee)
                }
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }
    fun updateEmployee(
        employee: Employee,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        employeeCollection
            .document(employee.employeeId)
            .set(employee)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    fun getEmployeeCount(
        onSuccess: (Int) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        employeeCollection
            .get()
            .addOnSuccessListener {
                onSuccess(it.size())
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

}

