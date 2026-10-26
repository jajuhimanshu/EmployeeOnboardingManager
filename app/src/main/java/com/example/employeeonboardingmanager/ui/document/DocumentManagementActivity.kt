package com.example.employeeonboardingmanager.ui.document

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.employeeonboardingmanager.R
import com.example.employeeonboardingmanager.adapter.DocumentAdapter
import com.example.employeeonboardingmanager.viewmodel.DocumentViewModel

class DocumentManagementActivity : AppCompatActivity() {

    private lateinit var viewModel: DocumentViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var employeeId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document_management)

        employeeId =
            intent.getStringExtra("employeeId") ?: return

        recyclerView = findViewById(R.id.rvDocuments)

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        viewModel =
            ViewModelProvider(this)[DocumentViewModel::class.java]

        loadDocuments()

        findViewById<Button>(R.id.btnAddDocument)
            .setOnClickListener {

                val documentIntent = Intent(
                    this,
                    AddDocumentActivity::class.java
                )

                documentIntent.putExtra(
                    "employeeId",
                    employeeId
                )

                startActivity(documentIntent)
            }
    }

    override fun onResume() {
        super.onResume()
        loadDocuments()
    }

    private fun loadDocuments() {

        viewModel.getDocumentsByEmployeeId(
            employeeId,

            onSuccess = { documentList ->

                recyclerView.adapter =
                    DocumentAdapter(documentList) { document ->

                        val intent = Intent(
                            this,
                            DocumentDetailsActivity::class.java
                        )

                        intent.putExtra(
                            "documentId",
                            document.documentId
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
