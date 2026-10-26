package com.example.employeeonboardingmanager.repository

import com.example.employeeonboardingmanager.model.OnboardingTask
import com.google.firebase.firestore.FirebaseFirestore

class TaskRepository {

    private val db = FirebaseFirestore.getInstance()
    private val taskCollection = db.collection("Tasks")

    fun addTask(
        task: OnboardingTask,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        taskCollection
            .document(task.taskId)
            .set(task)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    fun getTasksByEmployeeId(
        employeeId: String,
        onSuccess: (List<OnboardingTask>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        taskCollection
            .whereEqualTo("employeeId", employeeId)
            .get()
            .addOnSuccessListener { result ->

                val taskList = mutableListOf<OnboardingTask>()

                for (document in result) {
                    val task =
                        document.toObject(OnboardingTask::class.java)

                    taskList.add(task)
                }

                onSuccess(taskList)
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    fun getTaskById(
        taskId: String,
        onSuccess: (OnboardingTask) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        taskCollection
            .document(taskId)
            .get()
            .addOnSuccessListener {

                val task =
                    it.toObject(OnboardingTask::class.java)

                if (task != null) {
                    onSuccess(task)
                }
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    fun updateTaskStatus(
        taskId: String,
        status: String,
        completionDate: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        taskCollection
            .document(taskId)
            .update(
                mapOf(
                    "status" to status,
                    "completionDate" to completionDate
                )
            )
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    fun getTaskCountByStatus(
        status: String,
        onSuccess: (Int) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        taskCollection
            .whereEqualTo("status", status)
            .get()
            .addOnSuccessListener {
                onSuccess(it.size())
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

}