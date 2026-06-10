package com.example.a2301681038_project.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "service_records")
data class ServiceRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val serviceName: String,
    val mileage: Int,
    val cost: Double,
    val date: Long, // Store as timestamp
    val notes: String
)
