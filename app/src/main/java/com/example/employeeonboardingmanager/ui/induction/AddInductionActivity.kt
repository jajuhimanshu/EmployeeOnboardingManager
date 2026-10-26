package com.example.employeeonboardingmanager.ui.induction

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.employeeonboardingmanager.R
import com.example.employeeonboardingmanager.model.InductionActivity
import com.example.employeeonboardingmanager.viewmodel.InductionViewModel
import java.util.UUID

class AddInductionActivity : AppCompatActivity() {

    private lateinit var viewModel: InductionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_induction)

        viewModel =
            ViewModelProvider(this)[InductionViewModel::class.java]

        val employeeId =
            intent.getStringExtra("employeeId") ?: return

        Toast.makeText(
            this,
            "Employee ID = $employeeId",
            Toast.LENGTH_LONG
        ).show()

        val etActivityTitle =
            findViewById<EditText>(R.id.etActivityTitle)

        val etDescription =
            findViewById<EditText>(R.id.etDescription)

        val etScheduledDate =
            findViewById<EditText>(R.id.etScheduledDate)

        val etStartTime =
            findViewById<EditText>(R.id.etStartTime)

        val etDuration =
            findViewById<EditText>(R.id.etDuration)

        val spActivityStatus =
            findViewById<Spinner>(R.id.spActivityStatus)

        val btnSaveActivity =
            findViewById<Button>(R.id.btnSaveActivity)

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

        spActivityStatus.adapter = adapter

        btnSaveActivity.setOnClickListener {

            val title =
                etActivityTitle.text.toString().trim()

            val description =
                etDescription.text.toString().trim()

            val scheduledDate =
                etScheduledDate.text.toString().trim()

            val startTime =
                etStartTime.text.toString().trim()

            val duration =
                etDuration.text.toString().trim()

            val status =
                spActivityStatus.selectedItem.toString()

            if (title.isEmpty()) {
                etActivityTitle.error =
                    "Enter Activity Title"
                return@setOnClickListener
            }

            val activity = InductionActivity(
                activityId = UUID.randomUUID().toString(),
                title = title,
                description = description,
                scheduledDate = scheduledDate,
                startTime = startTime,
                duration = duration,
                employeeIds = listOf(employeeId),
                status = status
            )

            viewModel.addInductionActivity(
                activity,

                onSuccess = {

                    Toast.makeText(
                        this,
                        "Induction Activity Saved",
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