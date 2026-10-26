package com.example.employeeonboardingmanager.ui.task

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.employeeonboardingmanager.R
import com.example.employeeonboardingmanager.model.OnboardingTask
import com.example.employeeonboardingmanager.viewmodel.TaskViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

class AddTaskActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        viewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        val employeeId = intent.getStringExtra("employeeId")

        if (employeeId == null) {
            Toast.makeText(
                this,
                "Employee ID not found",
                Toast.LENGTH_LONG
            ).show()
            finish()
            return
        }

        Toast.makeText(
            this,
            "Employee ID: $employeeId",
            Toast.LENGTH_SHORT
        ).show()

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val etDueDate = findViewById<EditText>(R.id.etDueDate)
        val spTaskStatus = findViewById<Spinner>(R.id.spTaskStatus)
        val btnSaveTask = findViewById<Button>(R.id.btnSaveTask)

        val statusList = listOf(
            "Pending",
            "In Progress",
            "Completed"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            statusList
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spTaskStatus.adapter = adapter

        btnSaveTask.setOnClickListener {

            val title = etTitle.text.toString().trim()
            val description = etDescription.text.toString().trim()
            val dueDate = etDueDate.text.toString().trim()
            val status = spTaskStatus.selectedItem.toString()

            if (title.isEmpty()) {
                etTitle.error = "Enter Task Title"
                return@setOnClickListener
            }

            if (description.isEmpty()) {
                etDescription.error = "Enter Description"
                return@setOnClickListener
            }

            if (dueDate.isEmpty()) {
                etDueDate.error = "Enter Due Date"
                return@setOnClickListener
            }

            val completionDate =
                if (status == "Completed") {
                    SimpleDateFormat(
                        "dd/MM/yyyy",
                        Locale.getDefault()
                    ).format(Date())
                } else {
                    ""
                }

            val task = OnboardingTask(
                taskId = UUID.randomUUID().toString(),
                employeeId = employeeId,
                title = title,
                description = description,
                dueDate = dueDate,
                status = status,
                completionDate = completionDate
            )

            viewModel.addTask(
                task,
                onSuccess = {

                    Toast.makeText(
                        this,
                        "Task Saved Successfully",
                        Toast.LENGTH_LONG
                    ).show()

                    finish()
                },
                onFailure = { exception ->

                    Toast.makeText(
                        this,
                        exception.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            )
        }
    }
}
