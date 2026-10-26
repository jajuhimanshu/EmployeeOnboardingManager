package com.example.employeeonboardingmanager.repository

import com.example.employeeonboardingmanager.model.InductionActivity
import com.google.firebase.firestore.FirebaseFirestore

class InductionRepository {

    private val db = FirebaseFirestore.getInstance()

    private val inductionCollection =
        db.collection("InductionActivities")

    fun addInductionActivity(
        activity: InductionActivity,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        inductionCollection
            .document(activity.activityId)
            .set(activity)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    fun getActivitiesByEmployeeId(
        employeeId: String,
        onSuccess: (List<InductionActivity>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {

        inductionCollection
            .get()
            .addOnSuccessListener { result ->

                val activityList =
                    mutableListOf<InductionActivity>()

                for (document in result) {

                    val activity =
                        document.toObject(
                            InductionActivity::class.java
                        )

                    // Debug check
                    if (
                        activity.employeeIds.any {
                            it.trim() == employeeId.trim()
                        }
                    ) {
                        activityList.add(activity)
                    }
                }

                onSuccess(activityList)
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    fun getActivityById(
        activityId: String,
        onSuccess: (InductionActivity) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        inductionCollection
            .document(activityId)
            .get()
            .addOnSuccessListener {

                val activity =
                    it.toObject(
                        InductionActivity::class.java
                    )

                if (activity != null) {
                    onSuccess(activity)
                }
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    fun updateActivityStatus(
        activityId: String,
        status: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {

        inductionCollection
            .document(activityId)
            .update(
                "status",
                status
            )
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }
}
