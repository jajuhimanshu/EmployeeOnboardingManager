package com.example.employeeonboardingmanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeonboardingmanager.R
import com.example.employeeonboardingmanager.model.InductionActivity

class InductionAdapter(
    private val inductionList: List<InductionActivity>,
    private val onItemClick: (InductionActivity) -> Unit
) : RecyclerView.Adapter<InductionAdapter.InductionViewHolder>() {

    class InductionViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val tvTitle: TextView =
            itemView.findViewById(R.id.tvInductionTitle)

        val tvStatus: TextView =
            itemView.findViewById(R.id.tvInductionStatus)

        val tvDate: TextView =
            itemView.findViewById(R.id.tvInductionDate)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InductionViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_induction,
                parent,
                false
            )

        return InductionViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: InductionViewHolder,
        position: Int
    ) {

        val activity = inductionList[position]

        holder.tvTitle.text = activity.title
        holder.tvStatus.text = activity.status
        holder.tvDate.text = activity.scheduledDate

        holder.itemView.setOnClickListener {
            onItemClick(activity)
        }
    }

    override fun getItemCount(): Int {
        return inductionList.size
    }
}
