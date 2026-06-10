package com.example.a2301681038_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.a2301681038_project.data.local.AppDatabase
import com.example.a2301681038_project.data.repository.ServiceRepository
import com.example.a2301681038_project.ui.screens.AddEditScreen
import com.example.a2301681038_project.ui.screens.HistoryScreen
import com.example.a2301681038_project.ui.theme._2301681038_projectTheme
import com.example.a2301681038_project.viewmodel.ServiceViewModel
import com.example.a2301681038_project.viewmodel.ServiceViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(this)
        val repository = ServiceRepository(database.serviceDao())
        val factory = ServiceViewModelFactory(repository)

        setContent {
            _2301681038_projectTheme {
                CarMaintenanceApp(factory)
            }
        }
    }
}

@Composable
fun CarMaintenanceApp(factory: ServiceViewModelFactory) {
    val navController = rememberNavController()
    val viewModel: ServiceViewModel = viewModel(factory = factory)

    NavHost(navController = navController, startDestination = "history") {
        composable("history") {
            HistoryScreen(
                viewModel = viewModel,
                onAddClick = { navController.navigate("add_edit/-1") },
                onRecordClick = { id -> navController.navigate("add_edit/$id") }
            )
        }
        composable(
            route = "add_edit/{recordId}",
            arguments = listOf(navArgument("recordId") { type = NavType.IntType })
        ) { backStackEntry ->
            val recordId = backStackEntry.arguments?.getInt("recordId")
            AddEditScreen(
                viewModel = viewModel,
                recordId = recordId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
