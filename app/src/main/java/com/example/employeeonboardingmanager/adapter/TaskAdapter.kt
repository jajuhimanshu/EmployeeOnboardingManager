package com.example.employeeonboardingmanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeonboardingmanager.R
import com.example.employeeonboardingmanager.model.OnboardingTask

class TaskAdapter(
    private val taskList: List<OnboardingTask>,
    private val onTaskClick: (OnboardingTask) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val tvTaskTitle: TextView =
            itemView.findViewById(R.id.tvTaskTitle)

        val tvTaskStatus: TextView =
            itemView.findViewById(R.id.tvTaskStatus)

        val tvDueDate: TextView =
            itemView.findViewById(R.id.tvDueDate)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_task,
                parent,
                false
            )

        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TaskViewHolder,
        position: Int
    ) {

        val task = taskList[position]

        holder.tvTaskTitle.text = task.title
        holder.tvTaskStatus.text = task.status
        holder.tvDueDate.text = "Due: ${task.dueDate}"

        holder.itemView.setOnClickListener {
            onTaskClick(task)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}