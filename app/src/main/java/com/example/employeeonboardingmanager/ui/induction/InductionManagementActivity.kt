package com.example.employeeonboardingmanager.ui.induction

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeonboardingmanager.R
import com.example.employeeonboardingmanager.adapter.InductionAdapter
import com.example.employeeonboardingmanager.viewmodel.InductionViewModel

class InductionManagementActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: InductionViewModel
    private lateinit var employeeId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_induction_management)

        employeeId =
            intent.getStringExtra("employeeId") ?: return

        Toast.makeText(
            this,
            "Current Employee ID = $employeeId",
            Toast.LENGTH_LONG
        ).show()

        recyclerView =
            findViewById(R.id.rvInductions)

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        viewModel =
            ViewModelProvider(this)[InductionViewModel::class.java]

        loadActivities()

        findViewById<Button>(R.id.btnAddInduction)
            .setOnClickListener {

                val intent = Intent(
                    this,
                    AddInductionActivity::class.java
                )

                intent.putExtra(
                    "employeeId",
                    employeeId
                )

                startActivity(intent)
            }
    }

    override fun onResume() {
        super.onResume()
        loadActivities()
    }

    private fun loadActivities() {

        viewModel.getActivitiesByEmployeeId(
            employeeId,

            onSuccess = { activities ->

                InductionAdapter(activities) { activity ->

                    val intent = Intent(
                        this,
                        InductionDetailsActivity::class.java
                    )

                    intent.putExtra(
                        "activityId",
                        activity.activityId
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
