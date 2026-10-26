package com.example.employeeonboardingmanager.ui.induction

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.employeeonboardingmanager.R
import com.example.employeeonboardingmanager.viewmodel.InductionViewModel

class InductionDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: InductionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_induction_details)

        viewModel =
            ViewModelProvider(this)[InductionViewModel::class.java]

        val activityId =
            intent.getStringExtra("activityId") ?: return

        val tvTitle =
            findViewById<TextView>(R.id.tvTitle)

        val tvDescription =
            findViewById<TextView>(R.id.tvDescription)

        val spStatus =
            findViewById<Spinner>(R.id.spStatus)

        val btnUpdateStatus =
            findViewById<Button>(R.id.btnUpdateStatus)

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

        spStatus.adapter = adapter

        viewModel.getActivityById(
            activityId,

            onSuccess = { activity ->

                tvTitle.text =
                    activity.title

                tvDescription.text =
                    activity.description

                val position =
                    statusList.indexOf(
                        activity.status
                    )

                if (position >= 0) {
                    spStatus.setSelection(position)
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
                spStatus.selectedItem.toString()

            viewModel.updateActivityStatus(
                activityId,
                selectedStatus,

                onSuccess = {

                    Toast.makeText(
                        this,
                        "Activity Updated",
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