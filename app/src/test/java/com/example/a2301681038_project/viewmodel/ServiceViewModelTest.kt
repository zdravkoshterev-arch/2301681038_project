package com.example.a2301681038_project.viewmodel

import com.example.a2301681038_project.data.local.ServiceRecord
import com.example.a2301681038_project.data.repository.ServiceRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ServiceViewModelTest {

    private lateinit var viewModel: ServiceViewModel
    private val repository: ServiceRepository = mockk()
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        coEvery { repository.allRecords } returns flowOf(emptyList())
        viewModel = ServiceViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `insert should call repository insert`() = runTest {
        val record = ServiceRecord(0, "Brake Fluid", 20000, 80.0, 123456789L, "Notes")
        coEvery { repository.insert(record) } returns Unit

        viewModel.insert(record)

        coVerify { repository.insert(record) }
    }

    @Test
    fun `getRecordById should call repository getRecordById`() = runTest {
        val record = ServiceRecord(1, "Brake Fluid", 20000, 80.0, 123456789L, "Notes")
        coEvery { repository.getRecordById(1) } returns record

        val result = viewModel.getRecordById(1)

        assertEquals(record, result)
        coVerify { repository.getRecordById(1) }
    }
}
