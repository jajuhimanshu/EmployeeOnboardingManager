package com.example.employeeonboardingmanager.viewmodel

import androidx.lifecycle.ViewModel
import com.example.employeeonboardingmanager.model.EmployeeDocument
import com.example.employeeonboardingmanager.repository.DocumentRepository

class DocumentViewModel : ViewModel() {

    private val repository = DocumentRepository()

    fun addDocument(
        document: EmployeeDocument,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        repository.addDocument(
            document,
            onSuccess,
            onFailure
        )
    }
    fun getDocumentsByEmployeeId(
        employeeId: String,
        onSuccess: (List<EmployeeDocument>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        repository.getDocumentsByEmployeeId(
            employeeId,
            onSuccess,
            onFailure
        )
    }


    fun getDocumentById(
        documentId: String,
        onSuccess: (EmployeeDocument) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        repository.getDocumentById(
            documentId,
            onSuccess,
            onFailure
        )
    }

    fun updateDocumentStatus(
        documentId: String,
        status: String,
        verifiedAt: Long,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        repository.updateDocumentStatus(
            documentId,
            status,
            verifiedAt,
            onSuccess,
            onFailure
        )
    }

    fun getDocumentCountByStatus(
        status: String,
        onSuccess: (Int) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        repository.getDocumentCountByStatus(
            status,
            onSuccess,
            onFailure
        )
    }
}