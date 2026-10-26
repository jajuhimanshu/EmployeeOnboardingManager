package com.example.employeeonboardingmanager.ui.task

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.employeeonboardingmanager.R
import com.example.employeeonboardingmanager.viewmodel.TaskViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TaskDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_details)

        viewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        val taskId = intent.getStringExtra("taskId") ?: return

        val tvTaskTitle = findViewById<TextView>(R.id.tvTaskTitle)
        val tvTaskDescription = findViewById<TextView>(R.id.tvTaskDescription)
        val spUpdateStatus = findViewById<Spinner>(R.id.spUpdateStatus)
        val btnUpdateStatus = findViewById<Button>(R.id.btnUpdateStatus)

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

        spUpdateStatus.adapter = adapter

        viewModel.getTaskById(
            taskId,

            onSuccess = { task ->

                tvTaskTitle.text = task.title
                tvTaskDescription.text = task.description

                val position =
                    statusList.indexOf(task.status)

                if (position >= 0) {
                    spUpdateStatus.setSelection(position)
                }
            },

            onFailure = {

                Toast.makeText(
                    this,
                    it.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        )

        btnUpdateStatus.setOnClickListener {

            val selectedStatus =
                spUpdateStatus.selectedItem.toString()

            val completionDate =
                if (selectedStatus == "Completed") {
                    SimpleDateFormat(
                        "dd/MM/yyyy",
                        Locale.getDefault()
                    ).format(Date())
                } else {
                    ""
                }

            viewModel.updateTaskStatus(
                taskId,
                selectedStatus,
                completionDate,

                onSuccess = {

                    Toast.makeText(
                        this,
                        "Status Updated",
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