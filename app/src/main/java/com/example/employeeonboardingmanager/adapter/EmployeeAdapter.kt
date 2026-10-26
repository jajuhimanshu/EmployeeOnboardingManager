package com.example.employeeonboardingmanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeonboardingmanager.R
import com.example.employeeonboardingmanager.model.Employee

class EmployeeAdapter(
    private var employeeList: List<Employee>,
    private val onItemClick: (Employee) -> Unit
) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    class EmployeeViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val tvName: TextView =
            itemView.findViewById(R.id.tvName)

        val tvDepartment: TextView =
            itemView.findViewById(R.id.tvDepartment)

        val tvStatus: TextView =
            itemView.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmployeeViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_employee,
                parent,
                false
            )

        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: EmployeeViewHolder,
        position: Int
    ) {
        val employee = employeeList[position]

        holder.tvName.text = employee.name
        holder.tvDepartment.text = employee.department
        holder.tvStatus.text = employee.onboardingStatus

        holder.itemView.setOnClickListener {
            onItemClick(employee)
        }
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    fun updateData(
        newList: List<Employee>
    ) {
        employeeList = newList
        notifyDataSetChanged()
    }
}