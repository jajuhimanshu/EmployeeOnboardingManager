package com.example.employeeonboardingmanager.ui.employee

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.employeeonboardingmanager.R
import com.example.employeeonboardingmanager.ui.task.TaskManagementActivity
import com.example.employeeonboardingmanager.viewmodel.EmployeeViewModel
import com.example.employeeonboardingmanager.ui.document.DocumentManagementActivity
import com.example.employeeonboardingmanager.ui.employee.AddEmployeeActivity
import com.example.employeeonboardingmanager.ui.induction.InductionManagementActivity

class EmployeeDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)

        viewModel = ViewModelProvider(this)[EmployeeViewModel::class.java]

        val employeeId = intent.getStringExtra("employeeId") ?: return

        viewModel.getEmployeeById(
            employeeId,

            onSuccess = { employee ->

                findViewById<TextView>(R.id.tvEmployeeId).text =
                    "Employee ID: ${employee.employeeId}"

                findViewById<TextView>(R.id.tvName).text =
                    "Name: ${employee.name}"

                findViewById<TextView>(R.id.tvEmail).text =
                    "Email: ${employee.email}"

                findViewById<TextView>(R.id.tvPhone).text =
                    "Phone: ${employee.phoneNumber}"

                findViewById<TextView>(R.id.tvDepartment).text =
                    "Department: ${employee.department}"

                findViewById<TextView>(R.id.tvDesignation).text =
                    "Designation: ${employee.designation}"

                findViewById<TextView>(R.id.tvJoiningDate).text =
                    "Joining Date: ${employee.joiningDate}"

                findViewById<TextView>(R.id.tvManager).text =
                    "Reporting Manager: ${employee.reportingManager}"

                findViewById<TextView>(R.id.tvStatus).text =
                    "Status: ${employee.onboardingStatus}"
            },

            onFailure = {

            }
        )

        findViewById<Button>(R.id.btnManageTasks)
            .setOnClickListener {

                val intent = Intent(
                    this,
                    TaskManagementActivity::class.java
                )

                intent.putExtra(
                    "employeeId",
                    employeeId
                )

                startActivity(intent)
            }

        findViewById<Button>(R.id.btnManageDocuments)
            .setOnClickListener {

                val intent = Intent(
                    this,
                    DocumentManagementActivity::class.java
                )

                intent.putExtra(
                    "employeeId",
                    employeeId
                )

                startActivity(intent)
            }

        findViewById<Button>(R.id.btnEditEmployee)
            .setOnClickListener {

                val intent = Intent(
                    this,
                    AddEmployeeActivity::class.java
                )

                intent.putExtra(
                    "employeeId",
                    employeeId
                )

                startActivity(intent)
            }

        findViewById<Button>(R.id.btnInductionActivities)
            .setOnClickListener {

                val intent = Intent(
                    this,
                    InductionManagementActivity::class.java
                )

                intent.putExtra(
                    "employeeId",
                    employeeId
                )

                startActivity(intent)
            }
    }
}