package com.example.a2301681038_project.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2301681038_project.data.local.ServiceRecord
import com.example.a2301681038_project.data.repository.ServiceRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ServiceViewModel(private val repository: ServiceRepository) : ViewModel() {

    val allRecords: StateFlow<List<ServiceRecord>> = repository.allRecords
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insert(record: ServiceRecord) = viewModelScope.launch {
        repository.insert(record)
    }

    fun update(record: ServiceRecord) = viewModelScope.launch {
        repository.update(record)
    }

    fun delete(record: ServiceRecord) = viewModelScope.launch {
        repository.delete(record)
    }

    suspend fun getRecordById(id: Int): ServiceRecord? {
        return repository.getRecordById(id)
    }
}
