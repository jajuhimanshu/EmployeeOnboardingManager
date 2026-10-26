package com.example.employeeonboardingmanager.viewmodel

import androidx.lifecycle.ViewModel
import com.example.employeeonboardingmanager.model.OnboardingTask
import com.example.employeeonboardingmanager.repository.TaskRepository

class TaskViewModel : ViewModel() {

    private val repository = TaskRepository()

    fun addTask(
        task: OnboardingTask,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        repository.addTask(
            task,
            onSuccess,
            onFailure
        )
    }

    fun getTasksByEmployeeId(
        employeeId: String,
        onSuccess: (List<OnboardingTask>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        repository.getTasksByEmployeeId(
            employeeId,
            onSuccess,
            onFailure
        )
    }

    fun getTaskById(
        taskId: String,
        onSuccess: (OnboardingTask) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        repository.getTaskById(
            taskId,
            onSuccess,
            onFailure
        )
    }


    fun updateTaskStatus(
        taskId: String,
        status: String,
        completionDate: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        repository.updateTaskStatus(
            taskId,
            status,
            completionDate,
            onSuccess,
            onFailure
        )
    }

    fun getTaskCountByStatus(
        status: String,
        onSuccess: (Int) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        repository.getTaskCountByStatus(
            status,
            onSuccess,
            onFailure
        )
    }

}