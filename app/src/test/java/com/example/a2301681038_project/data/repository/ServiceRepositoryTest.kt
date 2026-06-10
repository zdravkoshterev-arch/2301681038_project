package com.example.a2301681038_project.data.repository

import com.example.a2301681038_project.data.local.ServiceDao
import com.example.a2301681038_project.data.local.ServiceRecord
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ServiceRepositoryTest {

    private lateinit var repository: ServiceRepository
    private val serviceDao: ServiceDao = mockk()

    @Before
    fun setup() {
        every { serviceDao.getAllRecords() } returns flowOf(emptyList())
        repository = ServiceRepository(serviceDao)
    }

    @Test
    fun `getRecordById should call dao getRecordById`() = runTest {
        val record = ServiceRecord(1, "Oil Change", 10000, 50.0, 123456789L, "Notes")
        coEvery { serviceDao.getRecordById(1) } returns record

        val result = repository.getRecordById(1)

        assertEquals(record, result)
        coVerify { serviceDao.getRecordById(1) }
    }

    @Test
    fun `insert should call dao insertRecord`() = runTest {
        val record = ServiceRecord(0, "Tire Rotation", 15000, 30.0, 123456789L, "Notes")
        coEvery { serviceDao.insertRecord(record) } returns Unit

        repository.insert(record)

        coVerify { serviceDao.insertRecord(record) }
    }
}
