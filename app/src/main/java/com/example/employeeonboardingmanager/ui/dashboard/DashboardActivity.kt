package com.example.employeeonboardingmanager.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.employeeonboardingmanager.R
import com.example.employeeonboardingmanager.ui.employee.EmployeeListActivity
import com.example.employeeonboardingmanager.viewmodel.EmployeeViewModel
import com.example.employeeonboardingmanager.viewmodel.TaskViewModel
import com.example.employeeonboardingmanager.viewmodel.DocumentViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.example.employeeonboardingmanager.ui.auth.LoginActivity


class DashboardActivity : AppCompatActivity() {

    private lateinit var employeeViewModel: EmployeeViewModel
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var documentViewModel: DocumentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        employeeViewModel =
            ViewModelProvider(this)[EmployeeViewModel::class.java]

        taskViewModel =
            ViewModelProvider(this)[TaskViewModel::class.java]

        documentViewModel =
            ViewModelProvider(this)[DocumentViewModel::class.java]

        val tvTotalEmployees =
            findViewById<TextView>(R.id.tvTotalEmployees)

        val tvPendingTasks =
            findViewById<TextView>(R.id.tvPendingTasks)

        val tvCompletedTasks =
            findViewById<TextView>(R.id.tvCompletedTasks)

        val tvPendingDocuments =
            findViewById<TextView>(R.id.tvPendingDocuments)

        val tvVerifiedDocuments =
            findViewById<TextView>(R.id.tvVerifiedDocuments)


        employeeViewModel.getEmployeeCount(
            onSuccess = {
                tvTotalEmployees.text =
                    "Total Employees: $it"
            },
            onFailure = {}
        )

        taskViewModel.getTaskCountByStatus(
            "Pending",
            onSuccess = {
                tvPendingTasks.text =
                    "Pending Tasks: $it"
            },
            onFailure = {}
        )

        taskViewModel.getTaskCountByStatus(
            "Completed",
            onSuccess = {
                tvCompletedTasks.text =
                    "Completed Tasks: $it"
            },
            onFailure = {}
        )

        documentViewModel.getDocumentCountByStatus(
            "Pending",
            onSuccess = {
                tvPendingDocuments.text =
                    "Pending Documents: $it"
            },
            onFailure = {}
        )

        documentViewModel.getDocumentCountByStatus(
            "Verified",
            onSuccess = {
                tvVerifiedDocuments.text =
                    "Verified Documents: $it"
            },
            onFailure = {}
        )

        findViewById<Button>(R.id.btnEmployees)
            .setOnClickListener {

                startActivity(
                    Intent(
                        this,
                        EmployeeListActivity::class.java
                    )
                )
            }

        findViewById<Button>(R.id.btnLogout)
            .setOnClickListener {

                FirebaseAuth.getInstance().signOut()

                startActivity(
                    Intent(
                        this,
                        LoginActivity::class.java
                    )
                )

                finish()
            }
    }


}