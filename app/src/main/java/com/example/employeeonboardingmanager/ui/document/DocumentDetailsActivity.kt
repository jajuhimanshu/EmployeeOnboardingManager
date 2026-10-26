package com.example.employeeonboardingmanager.ui.document

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.employeeonboardingmanager.R
import com.example.employeeonboardingmanager.viewmodel.DocumentViewModel

class DocumentDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: DocumentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document_details)

        viewModel =
            ViewModelProvider(this)[DocumentViewModel::class.java]

        val documentId =
            intent.getStringExtra("documentId") ?: return

        val tvDocumentType =
            findViewById<TextView>(R.id.tvDocumentType)

        val tvReviewComments =
            findViewById<TextView>(R.id.tvReviewComments)

        val spDocumentStatus =
            findViewById<Spinner>(R.id.spDocumentStatus)

        val btnUpdateDocument =
            findViewById<Button>(R.id.btnUpdateDocument)

        val statusList = listOf(
            "Pending",
            "Verified",
            "Rejected",
            "Submitted"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            statusList
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spDocumentStatus.adapter = adapter

        viewModel.getDocumentById(
            documentId,

            onSuccess = { document ->

                tvDocumentType.text =
                    document.documentType

                tvReviewComments.text =
                    document.reviewComments

                val position =
                    statusList.indexOf(document.status)

                if (position >= 0) {
                    spDocumentStatus.setSelection(position)
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

        btnUpdateDocument.setOnClickListener {

            val status =
                spDocumentStatus.selectedItem.toString()

            val verifiedAt =
                if (status == "Verified") {
                    System.currentTimeMillis()
                } else {
                    0L
                }

            viewModel.updateDocumentStatus(
                documentId,
                status,
                verifiedAt,

                onSuccess = {

                    Toast.makeText(
                        this,
                        "Document Updated",
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