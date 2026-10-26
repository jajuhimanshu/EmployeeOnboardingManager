package com.example.employeeonboardingmanager.ui.employee

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeonboardingmanager.R
import com.example.employeeonboardingmanager.adapter.EmployeeAdapter
import com.example.employeeonboardingmanager.viewmodel.EmployeeViewModel
import androidx.appcompat.widget.SearchView
import com.example.employeeonboardingmanager.model.Employee

class EmployeeListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: EmployeeViewModel

    private var employeeList = mutableListOf<Employee>()
    private lateinit var adapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_list)

        recyclerView = findViewById(R.id.rvEmployees)

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        viewModel =
            ViewModelProvider(this)[EmployeeViewModel::class.java]

        adapter = EmployeeAdapter(
            mutableListOf()
        ) { employee ->

            val intent = Intent(
                this,
                EmployeeDetailsActivity::class.java
            )

            intent.putExtra(
                "employeeId",
                employee.employeeId
            )

            startActivity(intent)
        }

        recyclerView.adapter = adapter

        loadEmployees()

        val searchView =
            findViewById<SearchView>(R.id.searchView)

        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(
                    query: String?
                ): Boolean {
                    return false
                }

                override fun onQueryTextChange(
                    newText: String?
                ): Boolean {

                    filterEmployees(
                        newText ?: ""
                    )

                    return true
                }
            }
        )

        findViewById<Button>(R.id.btnAddEmployee)
            .setOnClickListener {

                startActivity(
                    Intent(
                        this,
                        AddEmployeeActivity::class.java
                    )
                )
            }
    }

    override fun onResume() {
        super.onResume()
        loadEmployees()
    }

    private fun loadEmployees() {

        viewModel.getEmployees(

            onSuccess = { employeeList ->

                this.employeeList.clear()
                this.employeeList.addAll(employeeList)

                adapter.updateData(employeeList)
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

    private fun filterEmployees(
        query: String
    ) {

        val filteredList =
            employeeList.filter {

                it.name.contains(
                    query,
                    ignoreCase = true
                ) ||

                        it.employeeId.contains(
                            query,
                            ignoreCase = true
                        ) ||

                        it.department.contains(
                            query,
                            ignoreCase = true
                        )
            }

        adapter.updateData(filteredList)
    }
}