package com.example.employeeonboardingmanager.ui.task

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeonboardingmanager.R
import com.example.employeeonboardingmanager.adapter.TaskAdapter
import com.example.employeeonboardingmanager.viewmodel.TaskViewModel

class TaskManagementActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: TaskViewModel
    private lateinit var employeeId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_management)

        employeeId =
            intent.getStringExtra("employeeId") ?: return

        recyclerView = findViewById(R.id.rvTasks)

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        viewModel =
            ViewModelProvider(this)[TaskViewModel::class.java]

        loadTasks()

        findViewById<Button>(R.id.btnAddTask)
            .setOnClickListener {

                val taskIntent = Intent(
                    this,
                    AddTaskActivity::class.java
                )

                taskIntent.putExtra(
                    "employeeId",
                    employeeId
                )

                startActivity(taskIntent)
            }
    }

    override fun onResume() {
        super.onResume()
        loadTasks()
    }

    private fun loadTasks() {

        viewModel.getTasksByEmployeeId(
            employeeId,

            onSuccess = { taskList ->

                recyclerView.adapter =
                    TaskAdapter(taskList) { task ->

                        val intent = Intent(
                            this,
                            TaskDetailsActivity::class.java
                        )

                        intent.putExtra(
                            "taskId",
                            task.taskId
                        )

                        startActivity(intent)
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
    }
}