package com.example.employeeonboardingmanager.ui.document

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.employeeonboardingmanager.R
import com.example.employeeonboardingmanager.model.EmployeeDocument
import com.example.employeeonboardingmanager.viewmodel.DocumentViewModel
import java.util.UUID

class AddDocumentActivity : AppCompatActivity() {

    private lateinit var viewModel: DocumentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_document)

        viewModel = ViewModelProvider(this)[DocumentViewModel::class.java]

        val employeeId = intent.getStringExtra("employeeId") ?: return

        val etDocumentType =
            findViewById<EditText>(R.id.etDocumentType)

        val etReviewComments =
            findViewById<EditText>(R.id.etReviewComments)

        val spDocumentStatus =
            findViewById<Spinner>(R.id.spDocumentStatus)

        val btnSaveDocument =
            findViewById<Button>(R.id.btnSaveDocument)

        val statusList = listOf(
            "Pending",
            "Verified",
            "Rejected",
            "submitted"
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

        btnSaveDocument.setOnClickListener {

            val documentType =
                etDocumentType.text.toString().trim()

            val reviewComments =
                etReviewComments.text.toString().trim()

            if (documentType.isEmpty()) {
                etDocumentType.error = "Enter Document Type"
                return@setOnClickListener
            }

            val status =
                spDocumentStatus.selectedItem.toString()

            val verifiedAt =
                if (status == "Verified") {
                    System.currentTimeMillis()
                } else {
                    0L
                }

            val document = EmployeeDocument(
                documentId = UUID.randomUUID().toString(),
                employeeId = employeeId,
                documentType = documentType,
                status = status,
                reviewComments = reviewComments,
                submittedAt = System.currentTimeMillis(),
                verifiedAt = verifiedAt
            )

            viewModel.addDocument(
                document,

                onSuccess = {

                    Toast.makeText(
                        this,
                        "Document Saved Successfully",
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