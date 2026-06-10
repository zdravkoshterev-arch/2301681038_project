package com.example.a2301681038_project

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testHistoryScreenDisplayed() {
        composeTestRule.onNodeWithText("Service History").assertIsDisplayed()
    }

    @Test
    fun testNavigateToAddRecord() {
        composeTestRule.onNodeWithContentDescription("Add Service").performClick()
        composeTestRule.onNodeWithText("Add Record").assertIsDisplayed()
    }
}
