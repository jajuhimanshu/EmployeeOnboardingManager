package com.example.employeeonboardingmanager.viewmodel

import androidx.lifecycle.ViewModel
import com.example.employeeonboardingmanager.model.InductionActivity
import com.example.employeeonboardingmanager.repository.InductionRepository

class InductionViewModel : ViewModel() {

    private val repository = InductionRepository()

    fun addInductionActivity(
        activity: InductionActivity,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        repository.addInductionActivity(
            activity,
            onSuccess,
            onFailure
        )
    }

    fun getActivitiesByEmployeeId(
        employeeId: String,
        onSuccess: (List<InductionActivity>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        repository.getActivitiesByEmployeeId(
            employeeId,
            onSuccess,
            onFailure
        )
    }

    fun getActivityById(
        activityId: String,
        onSuccess: (InductionActivity) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        repository.getActivityById(
            activityId,
            onSuccess,
            onFailure
        )
    }


    fun updateActivityStatus(
        activityId: String,
        status: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        repository.updateActivityStatus(
            activityId,
            status,
            onSuccess,
            onFailure
        )
    }
}