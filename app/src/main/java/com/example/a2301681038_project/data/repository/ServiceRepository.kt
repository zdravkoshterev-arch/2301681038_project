package com.example.a2301681038_project.data.repository

import com.example.a2301681038_project.data.local.ServiceDao
import com.example.a2301681038_project.data.local.ServiceRecord
import kotlinx.coroutines.flow.Flow

class ServiceRepository(private val serviceDao: ServiceDao) {
    val allRecords: Flow<List<ServiceRecord>> = serviceDao.getAllRecords()

    suspend fun getRecordById(id: Int): ServiceRecord? {
        return serviceDao.getRecordById(id)
    }

    suspend fun insert(record: ServiceRecord) {
        serviceDao.insertRecord(record)
    }

    suspend fun update(record: ServiceRecord) {
        serviceDao.updateRecord(record)
    }

    suspend fun delete(record: ServiceRecord) {
        serviceDao.deleteRecord(record)
    }
}
