package com.example.employeeonboardingmanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeonboardingmanager.R
import com.example.employeeonboardingmanager.model.EmployeeDocument

class DocumentAdapter(
    private val documentList: List<EmployeeDocument>,
    private val onDocumentClick: (EmployeeDocument) -> Unit
) : RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder>() {

    class DocumentViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val tvDocumentType: TextView =
            itemView.findViewById(R.id.tvDocumentType)

        val tvDocumentStatus: TextView =
            itemView.findViewById(R.id.tvDocumentStatus)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DocumentViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_document,
                parent,
                false
            )

        return DocumentViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: DocumentViewHolder,
        position: Int
    ) {

        val document = documentList[position]

        holder.tvDocumentType.text =
            document.documentType

        holder.tvDocumentStatus.text =
            document.status

        holder.itemView.setOnClickListener {
            onDocumentClick(document)
        }
    }

    override fun getItemCount(): Int {
        return documentList.size
    }
}