package com.example.employeeonboardingmanager.ui.employee

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.employeeonboardingmanager.R
import com.example.employeeonboardingmanager.model.Employee
import com.example.employeeonboardingmanager.viewmodel.EmployeeViewModel

class AddEmployeeActivity : AppCompatActivity() {

    private lateinit var viewModel: EmployeeViewModel

    private var isEditMode = false
    private var employeeIdToEdit: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)

        viewModel = ViewModelProvider(this)[EmployeeViewModel::class.java]

        val etEmployeeId = findViewById<EditText>(R.id.etEmployeeId)
        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPhoneNumber = findViewById<EditText>(R.id.etPhoneNumber)
        val etDepartment = findViewById<EditText>(R.id.etDepartment)
        val etDesignation = findViewById<EditText>(R.id.etDesignation)
        val etJoiningDate = findViewById<EditText>(R.id.etJoiningDate)
        val etReportingManager = findViewById<EditText>(R.id.etReportingManager)
        val spinner = findViewById<Spinner>(R.id.spOnboardingStatus)
        val btnSave = findViewById<Button>(R.id.btnSaveEmployee)

        val statusList = listOf(
            "Offer Accepted",
            "Joining Formalities",
            "In Progress",
            "Completed",
            "On Hold"
        )

        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            statusList
        )


        employeeIdToEdit = intent.getStringExtra("employeeId")

        if (employeeIdToEdit != null) {

            isEditMode = true

            etEmployeeId.isEnabled = false

            viewModel.getEmployeeById(
                employeeIdToEdit!!,

                onSuccess = { employee ->

                    etEmployeeId.setText(employee.employeeId)
                    etName.setText(employee.name)
                    etEmail.setText(employee.email)
                    etPhoneNumber.setText(employee.phoneNumber)
                    etDepartment.setText(employee.department)
                    etDesignation.setText(employee.designation)
                    etJoiningDate.setText(employee.joiningDate)
                    etReportingManager.setText(employee.reportingManager)

                    val position =
                        statusList.indexOf(
                            employee.onboardingStatus
                        )

                    if (position >= 0) {
                        spinner.setSelection(position)
                    }

                    btnSave.text = "Update Employee"
                },

                onFailure = {
                    Toast.makeText(
                        this,
                        it.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            )
        }

        btnSave.setOnClickListener {

            val employee = Employee(
                employeeId = etEmployeeId.text.toString().trim(),
                name = etName.text.toString().trim(),
                email = etEmail.text.toString().trim(),
                phoneNumber = etPhoneNumber.text.toString().trim(),
                department = etDepartment.text.toString().trim(),
                designation = etDesignation.text.toString().trim(),
                joiningDate = etJoiningDate.text.toString().trim(),
                reportingManager = etReportingManager.text.toString().trim(),
                onboardingStatus = spinner.selectedItem.toString(),
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )

            if (isEditMode) {



                viewModel.updateEmployee(
                    employee,

                    onSuccess = {

                        Toast.makeText(
                            this,
                            "Employee Updated Successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        finish()
                    },

                    onFailure = {

                        Toast.makeText(
                            this,
                            it.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                )

            } else {

                viewModel.addEmployee(
                    employee,

                    onSuccess = {

                        Toast.makeText(
                            this,
                            "Employee Saved Successfully",
                            Toast.LENGTH_SHORT
                        ).show()

                        finish()
                    },

                    onFailure = {

                        Toast.makeText(
                            this,
                            it.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                )
            }
        }
    }
}