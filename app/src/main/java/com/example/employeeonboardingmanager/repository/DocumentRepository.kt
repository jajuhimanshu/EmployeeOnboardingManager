package com.example.employeeonboardingmanager.repository

import com.example.employeeonboardingmanager.model.EmployeeDocument
import com.google.firebase.firestore.FirebaseFirestore

class DocumentRepository {

    private val db = FirebaseFirestore.getInstance()
    private val documentCollection = db.collection("Documents")

    fun addDocument(
        document: EmployeeDocument,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        documentCollection
            .document(document.documentId)
            .set(document)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    fun getDocumentsByEmployeeId(
        employeeId: String,
        onSuccess: (List<EmployeeDocument>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        documentCollection
            .whereEqualTo("employeeId", employeeId)
            .get()
            .addOnSuccessListener { result ->

                val documentList = mutableListOf<EmployeeDocument>()

                for (document in result) {
                    val employeeDocument =
                        document.toObject(EmployeeDocument::class.java)

                    documentList.add(employeeDocument)
                }

                onSuccess(documentList)
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }


    fun getDocumentById(
        documentId: String,
        onSuccess: (EmployeeDocument) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        documentCollection
            .document(documentId)
            .get()
            .addOnSuccessListener {

                val document =
                    it.toObject(EmployeeDocument::class.java)

                if (document != null) {
                    onSuccess(document)
                }
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }


    fun updateDocumentStatus(
        documentId: String,
        status: String,
        verifiedAt: Long,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        documentCollection
            .document(documentId)
            .update(
                mapOf(
                    "status" to status,
                    "verifiedAt" to verifiedAt
                )
            )
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    fun getDocumentCountByStatus(
        status: String,
        onSuccess: (Int) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        documentCollection
            .whereEqualTo("status", status)
            .get()
            .addOnSuccessListener {
                onSuccess(it.size())
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }
}