package com.example.a2301681038_project.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ServiceDao {
    @Query("SELECT * FROM service_records ORDER BY date DESC")
    fun getAllRecords(): Flow<List<ServiceRecord>>

    @Query("SELECT * FROM service_records WHERE id = :id")
    suspend fun getRecordById(id: Int): ServiceRecord?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: ServiceRecord)

    @Update
    suspend fun updateRecord(record: ServiceRecord)

    @Delete
    suspend fun deleteRecord(record: ServiceRecord)
}
